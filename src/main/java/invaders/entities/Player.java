package invaders.entities;

import invaders.entities.projectiles.PlayerProjectile;
import invaders.entities.projectiles.PlayerProjectileFactory;
import invaders.entities.projectiles.Projectile;
import invaders.entities.projectiles.ProjectileFactory;
import invaders.logic.Damagable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Player implements Moveable, Damagable, Renderable {

    private final Vector2D position;
    private final Animator anim = null;
    private double health = 100;

    private final double width = 25;
    private final double height = 25;
    private final Image image;

    private ArrayList<Projectile> laser = new ArrayList<>();
    private boolean laserExists = false;

    public Player(Vector2D position){
        this.image = new Image(new File("src/main/resources/Laser_Cannon.png").toURI().toString(), width, height, true, true);
        this.position = position;
    }

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        this.position.setX(this.position.getX() - 1);
    }

    @Override
    public void right() {
        this.position.setX(this.position.getX() + 1);
    }

    public void shoot(){
        if(this.laser.isEmpty()) {
            ProjectileFactory pFactory = new PlayerProjectileFactory();
            Projectile projectile = pFactory.make(this);
            this.laser.add(projectile);
            this.laserExists = true;
        }
    }

    public boolean isLaserExists() {
        return this.laserExists;
    }

    public Projectile getLaser() {
        return this.laser.get(0);
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

}
