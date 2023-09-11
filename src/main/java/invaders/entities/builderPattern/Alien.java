package invaders.entities.builderPattern;

import invaders.GameObject;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

import java.io.File;

public class Alien implements Renderable, Collider, Moveable, Damagable, GameObject {

    private final Vector2D position;
    private final double height = 22;
    private final double width = 31;
    private final Image image;

    public Alien(Vector2D position) {
        this.position = position;
        this.image = new Image(new File("src/main/resources/enemy.png").toURI().toString(), width, height, true, true);
    }

    @Override
    public void takeDamage() {

    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        this.getPosition().setY(getPosition().getY() + 1);
    }

    @Override
    public void left() {
        this.getPosition().setX(getPosition().getX() - 1);
    }

    @Override
    public void right() {
        this.getPosition().setX(getPosition().getX() + 1);
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
    public void start() {

    }

    @Override
    public void update() {
        //TODO
    }
}
