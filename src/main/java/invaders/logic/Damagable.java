package invaders.logic;

import invaders.rendering.Renderable;

public interface Damagable extends Renderable {

	public void takeDamage();

	public int getHealth();

	public boolean isAlive();

}
