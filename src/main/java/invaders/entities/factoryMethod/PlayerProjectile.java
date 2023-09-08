package invaders.entities.factoryMethod;

import invaders.entities.EntityView;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class PlayerProjectile extends Projectile implements EntityView {

    private final Vector2D position;
    private Image image;
    private final Animator anim = null;

    private final double width = 30;
    private final double height = 15;

    private boolean delete = false;
    private ImageView node;

    public PlayerProjectile(Vector2D position) {
        this.position = position;
        this.image = new Image(new File("src/main/resources/shot.png").toURI().toString(), width, height, true, true);
        node = new ImageView(this.getImage());
        node.setViewOrder(getViewOrder(this.getLayer()));
    }

    private static double getViewOrder(Renderable.Layer layer) {
        switch (layer) {
            case BACKGROUND: return 100.0;
            case FOREGROUND: return 50.0;
            case EFFECT: return 25.0;
            default: throw new IllegalStateException("Javac doesn't understand how enums work so now I have to exist");
        }
    }

    @Override
    public void up() {
        this.position.setY(this.position.getY() - 4);
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

    public void explode() {
        this.image = new Image(new File("src/main/resources/explosion.png").toURI().toString(), width, height+10, true, true);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        this.up();
    }

    @Override
    public void update(double xViewportOffset, double yViewportOffset) {
        if (!node.getImage().equals(this.getImage())) {
            node.setImage(this.getImage());
        }
        node.setX(position.getX() - xViewportOffset);
        node.setY(position.getY() - yViewportOffset);
        node.setFitHeight(this.getHeight());
        node.setFitWidth(this.getWidth());
        node.setPreserveRatio(true);
        delete = false;
    }

    @Override
    public boolean matchesEntity(Renderable entity) {
        return this.equals(entity);
    }

    @Override
    public void markForDelete() {
        delete = true;
    }

    @Override
    public Node getNode() {
        return this.node;
    }

    @Override
    public boolean isMarkedForDelete() {
        return delete;
    }
}
