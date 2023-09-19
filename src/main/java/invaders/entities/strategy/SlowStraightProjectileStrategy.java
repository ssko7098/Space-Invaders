package invaders.entities.strategy;

import invaders.entities.factoryMethod.Projectile;

public class SlowStraightProjectileStrategy implements ProjectileStrategy{
    @Override
    public void setSpeed(Projectile proj) {
        proj.setSpeed(1);
    }
}
