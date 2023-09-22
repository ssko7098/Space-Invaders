package invaders.entities.builderPattern;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.Direction;
import invaders.entities.Entity;
import invaders.entities.Shootable;
import invaders.entities.factoryMethod.*;
import invaders.entities.strategy.FastStraightProjectileStrategy;
import invaders.entities.strategy.ProjectileStrategy;
import invaders.entities.strategy.SlowStraightProjectileStrategy;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Alien extends Entity implements Renderable, Collider, Moveable, Damagable, GameObject, Shootable {

    private Direction direction = Direction.RIGHT;
    private double speed = 0.2;

    public Alien(Vector2D position, String strategy) {
        super(position);
        super.image = new Image(new File("src/main/resources/enemy.png").toURI().toString(), width, height, true, true);
        super.lives = 1;
        super.height = 22;
        super.width = 31;

        if(strategy.equals("slow_straight")) {
            super.strategy = new SlowStraightProjectileStrategy();
        }
        else if(strategy.equals("fast_straight")) {
            super.strategy = new FastStraightProjectileStrategy();
        }
    }

    @Override
    public void takeDamage() {
        lives -= 1;
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        this.getPosition().setY(getPosition().getY() + speed);
    }

    @Override
    public void left() {
        this.getPosition().setX(getPosition().getX() - speed);
    }

    @Override
    public void right() {
        this.getPosition().setX(getPosition().getX() + speed);
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void shoot() {
        if (this.projectiles.isEmpty()) {
            ProjectileFactory pFactory = new AlienProjectileFactory();
            AlienProjectile projectile = (AlienProjectile) pFactory.make(this);

            // Setting the speed as per the strategy
            projectile.setStrategy(super.strategy);
            super.strategy.setSpeed(projectile);

            super.projectiles.add(projectile);
        }
    }

    @Override
    public void update() {
        if(direction.equals(Direction.RIGHT)) {
            this.right();
        }
        else if(direction.equals(Direction.LEFT)) {
            this.left();
        }
        else if(direction.equals(Direction.DOWN)) {
            this.down();
        }
    }
}