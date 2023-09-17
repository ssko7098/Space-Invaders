package invaders.entities.factoryMethod;

import invaders.entities.strategy.ProjectileStrategy;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import javafx.scene.image.Image;

import java.io.File;

public class AlienProjectile extends Projectile{

    private final Vector2D position;
    private Image image;
    private final Animator anim = null;

    private final double width = 3;
    private final double height = 9;
    private double speed;

    private ProjectileStrategy strategy;

    public AlienProjectile(Vector2D position) {
        this.position = position;
        this.image = new Image(new File("src/main/resources/bomb.png").toURI().toString(), width, height, true, true);
    }

    @Override
    public void start() {

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
        return Layer.BACKGROUND;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getSpeed() {
        return speed;
    }


    public void setStrategy(ProjectileStrategy strat) {
        this.strategy = strat;
    }
}
