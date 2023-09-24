package invaders.entities;

import invaders.entities.factoryMethod.Projectile;
import invaders.rendering.Renderable;

/**
 * This interface is implemented by all objects
 * which can shoot a projectile
 */
public interface Shootable extends Renderable {

    boolean isProjectileExists();

    Projectile getProjectile();

    void removeProjectile();

    void shoot();

}
