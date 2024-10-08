package invaders.entities.builderPattern;

import invaders.entities.states.BunkerState;
import invaders.entities.states.*;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Vector2D;
import javafx.scene.image.Image;

import java.io.File;

public class Bunker implements Collider, Damagable {

    private final Vector2D position;
    private final double height;
    private final double width;
    private Image image;
    private BunkerState state;
    private int lives = 3;

    public Bunker(Vector2D position, Vector2D size) {
        this.position = position;
        this.width = size.getX();
        this.height = size.getY();
        this.image = new Image(new File("src/main/resources/bunker.png").toURI().toString(), width, height, false, true);
        this.state = new GreenState(this);
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

    public void changeState(BunkerState state) {
        this.state = state;
    }

    @Override
    public void takeDamage() {
        this.state.takeDamage();
    }

    public void setHealth(int life) {
        this.lives = life;
    }

    @Override
    public int getHealth() {
        return lives;
    }

    @Override
    public boolean isAlive() {
        return lives > 0;
    }

    public void setImage(Image image) {
        this.image.cancel();
        this.image = image;
    }
}
