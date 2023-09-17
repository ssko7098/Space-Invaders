package invaders.engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.AlienHorde;
import invaders.entities.Player;
import invaders.entities.Shootable;
import invaders.entities.builderPattern.AlienBuilder;
import invaders.entities.builderPattern.BunkerBuilder;
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
	private ConfigReader config = new ConfigReader();

	private boolean left;
	private boolean right;

	private int laserCount = 0;
	private AlienHorde alienHorde = new AlienHorde();
	private int bombCount = 0;

	private int width;
	private int height;

	public GameEngine(String config){
		// read the config here
		width = Integer.parseInt(this.config.getGameSize()[0].toString());
		height = Integer.parseInt(this.config.getGameSize()[1].toString());

		gameobjects = new ArrayList<>();
		renderables = new ArrayList<>();
		collidables = new ArrayList<>();
		damagables = new ArrayList<>();

		for(int i=0; i<3; i++) {
			Renderable bunker = new BunkerBuilder()
					.addPosition(i)
					.addSize(i)
					.build();

			renderables.add(bunker);
			collidables.add( (Collider) bunker);
			damagables.add( (Damagable) bunker);
		}

		for(int x=0; x<18; x++) {
			Renderable alien = new AlienBuilder()
					.addPosition(x)
					.addProjectileStrategy(x)
					.build();

			renderables.add(alien);
			gameobjects.add( (GameObject) alien);

			alienHorde.addAlien(alien);

			collidables.add( (Collider) alien);
			damagables.add( (Damagable) alien);
		}

		player = new Player(config);
		renderables.add(player);
		collidables.add(player);
		damagables.add(player);
	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		movePlayer();

		if(player.isProjectileExists() && laserCount == 0) {
			renderables.add(player.getProjectile());
			gameobjects.add(player.getProjectile());
			collidables.add(player.getProjectile());
			laserCount = 1;
		}

//		for(Shootable alien : alienHorde.getAlienHorde()) {
//			if(bombCount < 3 && alien.isProjectileExists()) {
//				renderables.add(alien.getProjectile());
//				gameobjects.add(alien.getProjectile());
//				collidables.add(alien.getProjectile());
//				bombCount ++;
//				break;
//			}
//
//			if(alien.isProjectileExists() && alien.getProjectile().getPosition().getY() >= 399) {
//				alien.removeProjectile();
//				renderables.remove(alien.getProjectile());
//				gameobjects.remove(alien.getProjectile());
//				collidables.remove(alien.getProjectile());
//				bombCount --;
//				break;
//			}
//		}

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
						//TODO Handle Collisions
						if(player.isProjectileExists() && (player.getProjectile() == col || player.getProjectile() == colB)) {
							if(col == player.getProjectile()) {
								renderables.remove(col);
								collidables.remove(col);
							}
							else if(colB == player.getProjectile()) {
								renderables.remove(colB);
								collidables.remove(colB);
							}

							player.removeProjectile();
							laserCount = 0;
						}

						for(Damagable dam: damagables) {

							if(dam.equals(col) || dam.equals(colB)) {
								dam.takeDamage();
							}

							for(int y=0; y<alienHorde.getAlienHorde().size(); y++) {
								Renderable alien = alienHorde.getAlienHorde().get(y);

								if(alien == dam && (alien == col || alien == colB)) {
									alienHorde.removeAlien(alien);
								}
							}

							if(!dam.isAlive()) {
								renderables.remove( (Renderable) dam);
								collidables.remove( (Collider) dam);
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
}
