package invaders.entities.builderPattern;

import invaders.physics.Vector2D;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class BunkerBuilder implements BunkerBuilderInterface {

    private final String path = "src/main/resources/config.json";
    private JSONArray jsonBunkers;
    private Vector2D position;
    private Vector2D size;

    /**
     * This class reads all the relevant alien information from the config file
     * and is responsible for building the Bunker object
     */
    public BunkerBuilder() {
        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray jsonBunkers = (JSONArray) jsonObject.get("Bunkers");
            this.jsonBunkers = jsonBunkers;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BunkerBuilderInterface addPosition(int index) {
        JSONObject bunkers = (JSONObject) jsonBunkers.get(index);
        JSONObject position = (JSONObject) bunkers.get("position");

        double x = Double.parseDouble(position.get("x").toString());
        double y = Double.parseDouble(position.get("y").toString());

        this.position = new Vector2D(x, y);
        return this;
    }

    @Override
    public BunkerBuilderInterface addSize(int index) {
        JSONObject bunkers = (JSONObject) jsonBunkers.get(index);
        JSONObject position = (JSONObject) bunkers.get("size");

        double x = Double.parseDouble(position.get("x").toString());
        double y = Double.parseDouble(position.get("y").toString());

        this.size = new Vector2D(x, y);
        return this;
    }

    public Bunker build() {
        return new Bunker(position, size);
    }
}
