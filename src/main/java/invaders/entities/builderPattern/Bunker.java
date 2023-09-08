package invaders.entities.builderPattern;

import invaders.physics.Collider;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

import java.io.File;

public class Bunker implements Renderable, Collider {

    private final Vector2D position;
    private final double height;
    private final double width;
    private final Image image;

    public Bunker(Vector2D position, Vector2D size) {
        this.position = position;
        this.width = size.getX();
        this.height = size.getY();
        this.image = new Image(new File("src/main/resources/bunker.png").toURI().toString(), width, height, true, true);
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
}
