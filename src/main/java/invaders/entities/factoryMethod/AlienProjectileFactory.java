package invaders.entities.factoryMethod;

import invaders.rendering.Renderable;

public class AlienProjectileFactory implements ProjectileFactory{

    @Override
    public Projectile make(Renderable entity) {
        return new AlienProjectile(entity.getPosition());
    }
}
