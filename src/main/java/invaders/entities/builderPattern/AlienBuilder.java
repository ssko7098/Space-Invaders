package invaders.entities.builderPattern;

import invaders.physics.Vector2D;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class AlienBuilder implements BunkerEnemyBuilder{

    private final String path = "src/main/resources/config.json";
    private JSONArray jsonEnemies;
    private Vector2D position;
    private String projectileStrategy;

    public AlienBuilder() {
        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray jsonEnemies = (JSONArray) jsonObject.get("Enemies");
            this.jsonEnemies = jsonEnemies;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BunkerEnemyBuilder addPosition(int index) {
        JSONObject enemies = (JSONObject) jsonEnemies.get(index);
        JSONObject position = (JSONObject) enemies.get("position");

        double x = Double.parseDouble(position.get("x").toString());
        double y = Double.parseDouble(position.get("y").toString());

        this.position = new Vector2D(x, y);
        return this;
    }

    @Override
    public BunkerEnemyBuilder addSize(int index) {
        return null;
    }

    @Override
    public BunkerEnemyBuilder addProjectileStrategy(int index) {
        JSONObject enemies = (JSONObject) jsonEnemies.get(index);

        this.projectileStrategy = enemies.get("projectile").toString();
        return this;
    }

    public Alien build() {
        return new Alien(position);
    }
}