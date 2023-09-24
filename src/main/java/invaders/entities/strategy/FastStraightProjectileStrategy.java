package invaders.entities.strategy;

import invaders.entities.factoryMethod.Projectile;

/**
 * Sets the speed of the projectile as per this class
 */
public class FastStraightProjectileStrategy implements ProjectileStrategy{

    @Override
    public void setSpeed(Projectile proj) {
        proj.setSpeed(4);
    }

}
