package invaders.entities;

import invaders.ConfigReader;
import invaders.entities.factoryMethod.PlayerProjectileFactory;
import invaders.entities.factoryMethod.Projectile;
import invaders.entities.factoryMethod.ProjectileFactory;
import invaders.entities.strategy.SlowStraightProjectileStrategy;
import invaders.physics.Vector2D;
import javafx.scene.image.Image;
import org.json.simple.JSONObject;
import java.io.File;

public class Player extends Entity {

    private final String colour;
    private final ConfigReader config = new ConfigReader();

    public Player(Vector2D position){
        super(position);

        JSONObject player = config.getPlayerObject();

        this.colour = player.get("colour").toString();
        super.image = new Image(new File("src/main/resources/" + colour.toLowerCase() + "Player.png").toURI().toString(), width, height, true, true);
        super.lives = Integer.parseInt(player.get("lives").toString());
        super.speed = Double.parseDouble(player.get("speed").toString());
        super.strategy = new SlowStraightProjectileStrategy();
    }

    @Override
    public void takeDamage() {
        this.lives -= 1;

        JSONObject player = config.getPlayerObject();
        Long x = (Long) ((JSONObject) player.get("position")).get("x");
        Long y = (Long) ((JSONObject) player.get("position")).get("y");
        this.position.setX(x);
        this.position.setY(y);
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
        if (this.projectiles.isEmpty()) {
            ProjectileFactory pFactory = new PlayerProjectileFactory();
            Projectile projectile = pFactory.make(this);

            // Setting the speed as per the strategy
            projectile.setStrategy(super.strategy);
            super.projectiles.add(projectile);
        }
    }

    @Override
    public void update() {
        return;
    }
}
