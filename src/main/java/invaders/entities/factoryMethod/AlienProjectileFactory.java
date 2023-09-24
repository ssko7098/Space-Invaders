package invaders.entities.factoryMethod;

import invaders.entities.Entity;
import invaders.physics.Vector2D;

/**
 * This class is responsible for creating new Alien Projectile objects
 */
public class AlienProjectileFactory implements ProjectileFactory{

    @Override
    public Projectile make(Entity entity) {
        Vector2D position = new Vector2D(entity.getPosition().getX() + entity.getWidth()/2,
                entity.getPosition().getY() + entity.getHeight()/2);

        return new AlienProjectile(position);
    }
}
