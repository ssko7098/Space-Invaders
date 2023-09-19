package invaders.entities.factoryMethod;

import invaders.entities.strategy.ProjectileStrategy;
import invaders.entities.strategy.SlowStraightProjectileStrategy;
import invaders.physics.Vector2D;
import javafx.scene.image.Image;

import java.io.File;

public class PlayerProjectile extends Projectile {

    private final Vector2D position;
    private Image image;

    private final double width = 3;
    private final double height = 10;
    private double speed;
    private int lives = 1;

    private ProjectileStrategy strategy;

    public PlayerProjectile(Vector2D position) {
        this.position = position;
        this.image = new Image(new File("src/main/resources/shot.png").toURI().toString(), width, height, true, true);
    }

    @Override
    public void up() {
        this.position.setY(this.position.getY() - speed);
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        return;
    }

    @Override
    public void right() {
        return;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        this.up();
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setStrategy(ProjectileStrategy strat) {
        this.strategy = strat;
    }

    @Override
    public void takeDamage() {
        lives --;
    }

    @Override
    public int getHealth() {
        return lives;
    }

    @Override
    public boolean isAlive() {
        return lives > 0;
    }
}
