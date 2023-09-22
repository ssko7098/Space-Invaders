package invaders.entities.factoryMethod;

import invaders.entities.strategy.ProjectileStrategy;
import invaders.entities.strategy.SlowStraightProjectileStrategy;
import invaders.logic.Damagable;
import invaders.physics.Vector2D;
import javafx.scene.image.Image;

import java.io.File;

public class PlayerProjectile extends Projectile {

    public PlayerProjectile(Vector2D position) {
        super(position);
        super.image = new Image(new File("src/main/resources/shot.png").toURI().toString(), width, height, false, true);
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
    public void update() {
        this.up();
    }
}
