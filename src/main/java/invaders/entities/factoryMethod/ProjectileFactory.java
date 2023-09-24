package invaders.entities.factoryMethod;

import invaders.entities.Entity;

/**
 * This class is responsible for creating new Projectile objects
 * using the factory method
 */
public interface ProjectileFactory {

    public Projectile make(Entity entity);
}
