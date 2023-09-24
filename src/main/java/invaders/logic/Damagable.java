package invaders.logic;

import invaders.rendering.Renderable;

/**
 * This interface is implemented by all objects
 * which are capable of losing a life
 */
public interface Damagable extends Renderable {

	public void takeDamage();

	public int getHealth();

	public boolean isAlive();

}
