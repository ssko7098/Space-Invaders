package invaders.entities.factoryMethod;

import invaders.GameObject;
import invaders.entities.EntityView;
import invaders.entities.strategy.ProjectileStrategy;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

public abstract class Projectile implements Moveable, GameObject, Collider, Damagable {

    protected final Vector2D position;
    protected Image image;

    protected final double width = 3;
    protected final double height = 10;
    protected double speed;
    protected int lives = 1;
    protected ProjectileStrategy strategy;

    public Projectile(Vector2D position) {
        this.position = position;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    @Override
    public void takeDamage() {
        this.lives -= 1;
    }

    @Override
    public int getHealth() {
        return lives;
    }

    @Override
    public boolean isAlive() {
        return lives > 0;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setStrategy(ProjectileStrategy strat) {
        this.strategy = strat;
    }

}