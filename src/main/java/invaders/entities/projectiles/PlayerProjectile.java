package invaders.entities.projectiles;

import invaders.entities.EntityViewImpl;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import javafx.scene.image.Image;

import java.io.File;

public class PlayerProjectile extends Projectile {

    private final Vector2D position;
    private Image image;
    private final Animator anim = null;

    private final double width = 25;
    private final double height = 10;

    public PlayerProjectile(Vector2D position) {
        this.position = position;
        this.image = new Image(new File("src/main/resources/shot.png").toURI().toString(), width, height, true, true);
    }

    @Override
    public void up() {
        this.position.setY(this.position.getY() + 1);
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

    public void remove() {
        this.image = new Image(new File("src/main/resources/explosion.png").toURI().toString(), width, height, true, true);
//        super.markForDelete();
    }
}
