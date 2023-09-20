package invaders.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.AlienHorde;
import invaders.entities.Player;
import invaders.entities.Shootable;
import invaders.entities.builderPattern.*;
import invaders.logic.Damagable;
import invaders.physics.BoxCollider;
import invaders.physics.Collider;
import invaders.rendering.Renderable;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private List<Collider> collidables;
	private List<Damagable> damagables;
	private Player player;
	private AlienHorde alienHorde = new AlienHorde();
	private Random random = new Random();

	private boolean left;
	private boolean right;

	private int laserCount = 0;
	private int bombCount = 0;

	private int width;
	private int height;

	private int count = 0;

	public GameEngine(String config){
		// read the config here
		ConfigReader configReader = new ConfigReader();
		width = Integer.parseInt(configReader.getGameSize()[0].toString());
		height = Integer.parseInt(configReader.getGameSize()[1].toString());

		gameobjects = new ArrayList<>();
		renderables = new ArrayList<>();
		collidables = new ArrayList<>();
		damagables = new ArrayList<>();

		Director director = new Director();
		BunkerEnemyBuilder bunkerBuilder = new BunkerBuilder();
		BunkerEnemyBuilder alienBuilder = new AlienBuilder();

		for(int i=0; i<configReader.getNumOfBunkers(); i++) {
			Renderable bunker = director.buildBunker(bunkerBuilder, i);

			renderables.add(bunker);
			collidables.add( (Collider) bunker);
			damagables.add( (Damagable) bunker);
		}

		for(int x=0; x< configReader.getNumOfAliens(); x++) {
			Renderable alien = director.buildAlien(alienBuilder, x);

			renderables.add(alien);
			gameobjects.add( (GameObject) alien);

			alienHorde.addAlien(alien);

			collidables.add( (Collider) alien);
			damagables.add( (Damagable) alien);
		}

		player = new Player();
		renderables.add(player);
		collidables.add(player);
		damagables.add(player);
	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		movePlayer();

		//TODO fix bug where the alien's projectile hits edge of screen after alien is killed.

		if(player.isProjectileExists() && laserCount == 0) {
			renderables.add(player.getProjectile());
			gameobjects.add(player.getProjectile());
			collidables.add(player.getProjectile());
			damagables.add(player.getProjectile());
			laserCount = 1;
		}

		for(int j=0; j<alienHorde.getAlienHorde().size(); j++) {
			Shootable alien = alienHorde.getAlienHorde().get(j);

			if(bombCount < 3 && j == random.nextInt(alienHorde.getAlienHorde().size()) && !alien.isProjectileExists()) {
				if(count > random.nextInt(50, 5000)) {
					alien.shoot();
					renderables.add(alien.getProjectile());
					gameobjects.add(alien.getProjectile());
					collidables.add(alien.getProjectile());
					damagables.add(alien.getProjectile());
					bombCount += 1;
					count = 0;
				}
			}

			if(alien.isProjectileExists() && (alien.getProjectile().getPosition().getY() + alien.getProjectile().getHeight() >= height - 1)) {
				gameobjects.remove(alien.getProjectile());
				damagables.remove(alien.getProjectile());
				collidables.remove(alien.getProjectile());
				renderables.remove(alien.getProjectile());
				alien.removeProjectile();
				bombCount -= 1;
			}
		}

		count ++;

		for(Renderable alien: alienHorde.getAlienHorde()) {
			if(alien.getPosition().getY() >= player.getPosition().getY() ||
					(alien.getPosition().getX() == player.getPosition().getX() &&
					alien.getPosition().getY() == player.getPosition().getY())) {
				player.dieInstantly();
			}
		}

		for(int i=0; i<collidables.size(); i++) {
			for(int x=0; x<collidables.size(); x++) {

				Collider col = collidables.get(i);
				Collider colB = collidables.get(x);

				if(!col.equals(colB)) {
					BoxCollider boxCollider = new BoxCollider(col.getWidth(), col.getHeight(), col.getPosition());
					if(boxCollider.isColliding(colB)) {

						for(Shootable alien: alienHorde.getAlienHorde()) {
							if(alien.isProjectileExists() && alien.getProjectile() == col
									&& player.isProjectileExists() && player.getProjectile() == colB) {
								gameobjects.remove((GameObject) col);
								gameobjects.remove((GameObject) colB);
								renderables.remove( (Renderable) col);
								renderables.remove( (Renderable) colB);
								collidables.remove(col);
								collidables.remove(colB);
								damagables.remove((Damagable) col);
								damagables.remove((Damagable) colB);
								player.removeProjectile();
								alien.removeProjectile();
								bombCount --;
								laserCount = 0;
							}
						}

						for(int k=0; k< damagables.size(); k++) {
							Damagable dam = damagables.get(k);

							boolean collides = true;
							for(int y=0; y<alienHorde.getAlienHorde().size(); y++) {
								Renderable alien = alienHorde.getAlienHorde().get(y);

								if(alien == dam && (alien == col || alien == colB)) {
                                    collides = player.isProjectileExists() && (player.getProjectile() == col || player.getProjectile() == colB);

									if(collides) {
										alienHorde.removeAlien(alien);
									}
								}
							}

							if((dam.equals(col) || dam.equals(colB)) && collides) {
								boolean bombCollidesWithAlien = false;

								for(Shootable alien: alienHorde.getAlienHorde()) {
									for(Shootable alienB: alienHorde.getAlienHorde()) {
										if((alien.isProjectileExists() && alien.getProjectile() == dam && alienB == col) ||
											(alienB.isProjectileExists() && alienB.getProjectile() == dam && alien == colB)) {
											bombCollidesWithAlien = true;
										}
									}
								}

								if(!bombCollidesWithAlien) {
									dam.takeDamage();
								}

								if(player.isProjectileExists() && dam == player.getProjectile()) {
									player.removeProjectile();
									laserCount = 0;
								}

								for(Shootable alien: alienHorde.getAlienHorde()) {
									if(alien.isProjectileExists() && dam == alien.getProjectile() && !bombCollidesWithAlien) {
										alien.removeProjectile();
										bombCount -= 1;
									}
								}
							}

							if(!dam.isAlive()) {
								renderables.remove( (Renderable) dam);
								collidables.remove( (Collider) dam);
								damagables.remove(dam);

								if(dam instanceof GameObject) {
									gameobjects.remove(dam);
								}
							}
						}
					}
				}
			}
		}

		alienHorde.update();

		for(GameObject go: gameobjects){
			go.update();
		}

		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables){
			if(!ro.getLayer().equals(Renderable.Layer.FOREGROUND)){
				continue;
			}
			if(ro.getPosition().getX() + ro.getWidth() >= width) {
				ro.getPosition().setX(width - 1 -ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if(ro.getPosition().getY() + ro.getHeight() >= height) {
				ro.getPosition().setY(height - 1 -ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);
			}
		}

//		for(Renderable test: renderables) {
//			boolean found = false;
//			for(Shootable alien: alienHorde.getAlienHorde()) {
//				if(alien.isProjectileExists() && alien.getProjectile() == test) {
//					found = true;
//				}
//			}
//
//			if(!found && test instanceof Shootable && player.isProjectileExists() && test != player.getProjectile()) {
//				if(test.getHeight() >= height - 1) {
//					gameobjects.remove((GameObject) test);
//					damagables.remove((Damagable) test);
//					collidables.remove((Collider) test);
//					renderables.remove(test);
//					bombCount -= 1;
//				}
//			}
//		}
	}

	public List<Renderable> getRenderables(){
		return renderables;
	}


	public void leftReleased() {
		this.left = false;
	}

	public void rightReleased(){
		this.right = false;
	}

	public void leftPressed() {
		this.left = true;
	}
	public void rightPressed(){
		this.right = true;
	}

	public boolean shootPressed(){
		if(!player.isProjectileExists()) {
			player.shoot();
			return true;
		}

		return false;
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}

		if(player.isProjectileExists() && player.getProjectile().getPosition().getY() <= 1) {
			renderables.remove(player.getProjectile());
			gameobjects.remove(player.getProjectile());
			collidables.remove(player.getProjectile());

			player.removeProjectile();
			laserCount = 0;
		}
	}

	public Player getPlayer() {
		return player;
	}

	public boolean noMoreAliens() {
		return alienHorde.getAlienHorde().isEmpty();
	}
}
