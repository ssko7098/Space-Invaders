package invaders.entities;

import invaders.ConfigReader;
import invaders.entities.factoryMethod.PlayerProjectileFactory;
import invaders.entities.factoryMethod.Projectile;
import invaders.entities.factoryMethod.ProjectileFactory;
import invaders.entities.strategy.FastStraightProjectileStrategy;
import invaders.entities.strategy.ProjectileStrategy;
import invaders.entities.strategy.SlowStraightProjectileStrategy;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player implements Moveable, Damagable, Renderable, Collider, Shootable {

    private final Vector2D position;
    private int lives;
    private final double speed;

    private final double width = 30;
    private final double height = 30;
    private final String colour;
    private final Image image;

    private ArrayList<Projectile> laser = new ArrayList<>();
    private ProjectileStrategy strategy;

    public Player(){
        ConfigReader config = new ConfigReader();
        JSONObject player = config.getPlayerObject();

        Long x = (Long) ((JSONObject) player.get("position")).get("x");
        Long y = (Long) ((JSONObject) player.get("position")).get("y");

        this.position = new Vector2D(x, y);
        this.colour = player.get("colour").toString();
        this.image = new Image(new File("src/main/resources/" + colour.toLowerCase() + "Player.png").toURI().toString(), width, height, true, true);
        this.lives = Integer.parseInt(player.get("lives").toString());
        this.speed = Double.parseDouble(player.get("speed").toString());
//        this.strategy = new SlowStraightProjectileStrategy();
        this.strategy = new FastStraightProjectileStrategy();
    }

    @Override
    public void takeDamage() {
        this.lives -= 1;
    }

    public void dieInstantly() {
        this.lives = 0;
    }

    @Override
    public int getHealth() {
        return this.lives;
    }

    @Override
    public boolean isAlive() {
        return this.lives > 0;
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        this.position.setX(this.position.getX() - speed);
    }

    @Override
    public void right() {
        this.position.setX(this.position.getX() + speed);
    }

    @Override
    public void shoot() {
        if (this.laser.isEmpty()) {
            ProjectileFactory pFactory = new PlayerProjectileFactory();
            Projectile projectile = pFactory.make(this);

            // Setting the speed as per the strategy
            projectile.setStrategy(strategy);
            strategy.setSpeed(projectile);
            this.laser.add(projectile);
        }
    }

    @Override
    public boolean isProjectileExists() {
        return !this.laser.isEmpty();
    }

    @Override
    public Projectile getProjectile() {
        return this.laser.get(0);
    }

    @Override
    public void removeProjectile() {
        this.laser.clear();
    }

    @Override
    public Image getImage() {
        return this.image;
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
