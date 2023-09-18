package invaders.entities.factoryMethod;

import invaders.GameObject;
import invaders.entities.EntityView;
import invaders.entities.strategy.ProjectileStrategy;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.rendering.Renderable;

public abstract class Projectile implements Moveable, Renderable, GameObject, Collider, Damagable {

    public abstract void setSpeed(double speed);

    public abstract double getSpeed();

}