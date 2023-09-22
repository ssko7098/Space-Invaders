package invaders.entities;

import invaders.entities.factoryMethod.Projectile;
import invaders.rendering.Renderable;

public interface Shootable extends Renderable {

    boolean isProjectileExists();

    Projectile getProjectile();

    void removeProjectile();

    void shoot();

}
