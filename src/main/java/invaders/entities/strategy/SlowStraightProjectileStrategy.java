package invaders.entities.strategy;

import invaders.entities.factoryMethod.Projectile;

public class SlowStraightProjectileStrategy implements ProjectileStrategy{

    /**
     * Sets the speed of the projectile as per this class
     * @param proj: The Projectile object to set the speed of
     */
    @Override
    public void setSpeed(Projectile proj) {
        proj.setSpeed(1.5);
    }
}
