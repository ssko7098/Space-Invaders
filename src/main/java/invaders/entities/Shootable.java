package invaders.entities;

import invaders.entities.factoryMethod.Projectile;

public interface Shootable {

    boolean isProjectileExists();

    Projectile getProjectile();

    void removeProjectile();

    void shoot();

}
