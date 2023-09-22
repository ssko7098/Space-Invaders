package invaders.entities.factoryMethod;

import invaders.entities.strategy.ProjectileStrategy;
import invaders.logic.Damagable;
import invaders.physics.Vector2D;
import javafx.scene.image.Image;

import java.io.File;

public class AlienProjectile extends Projectile {

    public AlienProjectile(Vector2D position) {
        super(position);
        super.image = new Image(new File("src/main/resources/bomb.png").toURI().toString(), width, height, false, true);
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
        super.position.setY(super.position.getY() + speed);
    }

    @Override
    public void left() {
        return;
    }

    @Override
    public void right() {
        return;
    }
}
