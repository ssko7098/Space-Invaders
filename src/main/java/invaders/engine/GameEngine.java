package invaders.engine;

import java.util.ArrayList;
import java.util.List;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.Player;
import invaders.entities.builderPattern.BunkerBuilder;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private Player player;
	private ConfigReader config = new ConfigReader();

	private boolean left;
	private boolean right;

	private int laserCount = 0;

	public GameEngine(String config){
		// read the config here
		gameobjects = new ArrayList<GameObject>();
		renderables = new ArrayList<Renderable>();

		Vector2D playerStart = this.config.getPlayer();

		for(int i=0; i<3; i++) {
			Renderable bunker = new BunkerBuilder()
					.addPosition(i)
					.addSize(i)
					.build();

			renderables.add(bunker);
		}

		player = new Player(playerStart, config);
		renderables.add(player);
	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		movePlayer();

		if(player.isLaserExists() && laserCount == 0) {
			renderables.add(player.getLaser());
			gameobjects.add(player.getLaser());
			laserCount = 1;
		}

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

			player.getLaser().markForDelete();
			renderables.remove(player.getLaser());
			gameobjects.remove(player.getLaser());

			player.removeLaser();
		}
	}
}
