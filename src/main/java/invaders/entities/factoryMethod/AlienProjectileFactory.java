package invaders.entities.factoryMethod;

import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

public class AlienProjectileFactory implements ProjectileFactory{

    /**
     * This class is responsible for creating new Alien Projectile objects
     */
    @Override
    public Projectile make(Renderable entity) {
        Vector2D position = new Vector2D(entity.getPosition().getX() + entity.getWidth()/2,
                entity.getPosition().getY() + entity.getHeight()/2);

        return new AlienProjectile(position);
    }
}
