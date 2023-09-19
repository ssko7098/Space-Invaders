package invaders;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {

	private final String path = "src/main/resources/config.json";
	private JSONObject object;

	public ConfigReader() {
		try{
			JSONParser parser = new JSONParser();
			Object object = parser.parse(new FileReader(this.path));
			this.object = (JSONObject) object;

		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public JSONObject getPlayerObject() {
		return (JSONObject) object.get("Player");
    }

	public Long[] getGameSize() {
		Long[] size = new Long[2];

		JSONObject jsonGame = (JSONObject) object.get("Game");

		Long x = (Long) ((JSONObject) jsonGame.get("size")).get("x");
		Long y = (Long) ((JSONObject) jsonGame.get("size")).get("y");

		size[0] = x;
		size[1] = y;

		return size;
	}

	public int getNumOfBunkers() {
		JSONArray bunkers = (JSONArray) object.get("Bunkers");

		return bunkers.size();
	}

	public int getNumOfAliens() {
		JSONArray enemies = (JSONArray) object.get("Enemies");

		return enemies.size();
	}
}
