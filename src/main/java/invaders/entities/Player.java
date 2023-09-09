package invaders.entities;

import invaders.entities.factoryMethod.PlayerProjectileFactory;
import invaders.entities.factoryMethod.Projectile;
import invaders.entities.factoryMethod.ProjectileFactory;
import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;

import javafx.scene.image.Image;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player implements Moveable, Damagable, Renderable, Collider {

    private final Vector2D position;
    private final Animator anim = null;
    private double lives;
    private double speed;

    private final double width = 30;
    private final double height = 30;
    private final Image image;

    private ArrayList<Projectile> laser = new ArrayList<>();

    public Player(String path){

        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) object;

            JSONObject jsonGame = (JSONObject) jsonObject.get("Player");

            Long x = (Long) ((JSONObject) jsonGame.get("position")).get("x");
            Long y = (Long) ((JSONObject) jsonGame.get("position")).get("y");

            this.position = new Vector2D(x, y);
            this.image = new Image(new File("src/main/resources/player.png").toURI().toString(), width, height, true, true);
            this.lives = Double.parseDouble(jsonGame.get("lives").toString());
            this.speed = Double.parseDouble(jsonGame.get("speed").toString());

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void takeDamage() {
        this.lives -= 1;
    }

    @Override
    public double getHealth() {
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

    public void shoot() {
        if (this.laser.isEmpty()) {
            ProjectileFactory pFactory = new PlayerProjectileFactory();
            Projectile projectile = pFactory.make(this);
            this.laser.add(projectile);
        }
    }

    public boolean isLaserExists() {
        return !this.laser.isEmpty();
    }

    public Projectile getLaser() {
        return this.laser.get(0);
    }

    public void removeLaser() {
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
