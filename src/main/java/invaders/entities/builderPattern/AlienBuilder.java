package invaders.entities.builderPattern;

import invaders.ConfigReader;
import invaders.physics.Vector2D;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class reads all the relevant alien information from the config file
 * and is responsible for building the Alien object
 */
public class AlienBuilder implements AlienBuilderInterface {

    private ConfigReader config = new ConfigReader();
    private JSONArray jsonEnemies;
    private Vector2D position;
    private String projectileStrategy;

    public AlienBuilder() {
        this.jsonEnemies = config.getAlienObjects();
    }

    @Override
    public AlienBuilderInterface addPosition(int index) {
        JSONObject enemies = (JSONObject) jsonEnemies.get(index);
        JSONObject position = (JSONObject) enemies.get("position");

        double x = Double.parseDouble(position.get("x").toString());
        double y = Double.parseDouble(position.get("y").toString());

        this.position = new Vector2D(x, y);
        return this;
    }

    @Override
    public AlienBuilderInterface addProjectileStrategy(int index) {
        JSONObject enemies = (JSONObject) jsonEnemies.get(index);

        this.projectileStrategy = enemies.get("projectile").toString();
        return this;
    }

    public Alien build() {
        return new Alien(position, projectileStrategy);
    }
}
