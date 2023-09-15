package invaders.engine;

import java.util.ArrayList;
import java.util.List;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.AlienHorde;
import invaders.entities.Player;
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

	public GameEngine(String config){
		// read the config here
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

		if(player.isLaserExists() && laserCount == 0) {
			renderables.add(player.getLaser());
			gameobjects.add(player.getLaser());
			collidables.add(player.getLaser());
			laserCount = 1;
		}

		for(int i=0; i<collidables.size(); i++) {
			for(int x=0; x<collidables.size(); x++) {

				Collider col = collidables.get(i);
				Collider colB = collidables.get(x);

				if(!col.equals(colB)) {
					BoxCollider test = new BoxCollider(col.getWidth(), col.getHeight(), col.getPosition());

					if(test.isColliding(colB)) {
						//TODO Handle Collisions
						if(player.isLaserExists() && col == player.getLaser()) {
							renderables.remove(col);
							player.removeLaser();
							collidables.remove(col);
							laserCount = 0;
						}
						else if(player.isLaserExists() && colB == player.getLaser()) {
							renderables.remove(colB);
							player.removeLaser();
							collidables.remove(colB);
							laserCount = 0;
						}


						for(Damagable dam: damagables) {
							if(dam.equals(col)) {
								dam.takeDamage();
							}
							else if(dam.equals(colB)) {
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
			if(ro.getPosition().getX() + ro.getWidth() >= 640) {
				ro.getPosition().setX(639-ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if(ro.getPosition().getY() + ro.getHeight() >= 400) {
				ro.getPosition().setY(399-ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);

				if(player.isLaserExists() && ro == player.getLaser()) {
					renderables.remove(ro);
					player.removeLaser();
					laserCount = 0;
					break;
				}
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
		player.shoot();
		return true;
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}

		if(player.isLaserExists() && player.getLaser().getPosition().getY() <= 1) {
			renderables.remove(player.getLaser());
			gameobjects.remove(player.getLaser());

			player.removeLaser();
		}
	}
}
