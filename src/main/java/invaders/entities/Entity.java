package invaders.entities;


import invaders.GameObject;
import invaders.entities.factoryMethod.Projectile;
import invaders.entities.strategy.ProjectileStrategy;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Entity implements Renderable, Damagable, Shootable, Moveable, Collider, GameObject {

    protected final Vector2D position;
    protected int lives;
    protected double speed;

    protected double width = 30;
    protected double height = 30;
    protected Image image;

    protected ArrayList<Projectile> projectiles = new ArrayList<>();
    protected ProjectileStrategy strategy;

    public Entity(Vector2D position) {
        this.position = position;
    }

    @Override
    public abstract void takeDamage();

    @Override
    public abstract void shoot();

    @Override
    public int getHealth() {
        return this.lives;
    }

    @Override
    public boolean isAlive() {
        return this.lives > 0;
    }

    @Override
    public boolean isProjectileExists() {
        return !this.projectiles.isEmpty();
    }

    @Override
    public Projectile getProjectile() {
        return this.projectiles.get(0);
    }

    @Override
    public void removeProjectile() {
        this.projectiles.clear();
    }

    @Override
    public Image getImage() {
        return this.image;
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

    public void setLives(int health) {
        this.lives = health;
    }

}
