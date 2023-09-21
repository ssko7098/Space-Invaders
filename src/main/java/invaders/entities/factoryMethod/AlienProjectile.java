package invaders.entities.factoryMethod;

import invaders.entities.strategy.ProjectileStrategy;
import invaders.logic.Damagable;
import invaders.physics.Vector2D;
import javafx.scene.image.Image;

import java.io.File;

public class AlienProjectile extends Projectile implements Damagable {

    private final Vector2D position;
    private Image image;

    private final double width = 3;
    private final double height = 10;
    private double speed;
    private int lives = 1;

    private ProjectileStrategy strategy;

    public AlienProjectile(Vector2D position) {
        this.position = position;
        this.image = new Image(new File("src/main/resources/bomb.png").toURI().toString(), width, height, true, true);
    }

    @Override
    public void update() {
        this.down();
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        this.position.setY(this.position.getY() + speed);
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
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void setStrategy(ProjectileStrategy strat) {
        this.strategy = strat;
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
}
