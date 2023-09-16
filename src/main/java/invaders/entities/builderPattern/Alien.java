package invaders.entities.builderPattern;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.Direction;
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

public class Alien implements Renderable, Collider, Moveable, Damagable, GameObject {

    private final Vector2D position;
    private final double height = 22;
    private final double width = 31;
    private Image image;
    private int lives = 1;
    private ConfigReader config = new ConfigReader();
    private Direction direction = Direction.RIGHT;
    private double speed = 0.2;

    private ArrayList<Projectile> shots = new ArrayList<>();
    private ProjectileStrategy strategy;

    public Alien(Vector2D position, String strategy) {
        this.position = position;
        this.image = new Image(new File("src/main/resources/enemy.png").toURI().toString(), width, height, true, true);

        if(strategy.equals("slow_straight")) {
            this.strategy = new SlowStraightProjectileStrategy();
        }
        else if(strategy.equals("fast_straight")) {
            this.strategy = new FastStraightProjectileStrategy();
        }
    }

    @Override
    public void takeDamage() {
        lives -= 1;
//        this.image.cancel();
//        this.image = new Image(new File("src/main/resources/explosion.png").toURI().toString(), 12, 12, true, true);
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return lives > 0;
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

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public ProjectileStrategy getStrategy() {
        return strategy;
    }

    public void shoot() {
        if (this.shots.isEmpty()) {
            ProjectileFactory pFactory = new AlienProjectileFactory();
            AlienProjectile projectile = (AlienProjectile) pFactory.make(this);

            // Setting the speed as per the strategy
            projectile.setStrategy(strategy);
            strategy.setSpeed(projectile);

            this.shots.add(projectile);
        }
    }

    public boolean isBombExists() {
        return !shots.isEmpty();
    }

    public Projectile getBomb() {
        return shots.get(0);
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