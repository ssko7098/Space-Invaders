package invaders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import invaders.physics.Vector2D;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigReader {

	private final String path = "src/main/resources/config.json";

	/**
	 * You will probably not want to use a static method/class for this.
	 * 
	 * This is just an example of how to access different parts of the json
	 * 
	 * @param path The path of the json file to read
	 */
	public static void parse(String path) {

		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));

			// convert Object to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// reading the Game section:
			JSONObject jsonGame = (JSONObject) jsonObject.get("Game");

			// reading a coordinate from the nested section within the game
			// note that the game x and y are of type Long (i.e. they are integers)
			Long gameX = (Long) ((JSONObject) jsonGame.get("size")).get("x");
			Long gameY = (Long) ((JSONObject) jsonGame.get("size")).get("y");

			// TODO: delete me, this is just a demonstration:
			System.out.println("Game details: x: " + gameX);
			System.out.println("Game details: y: " + gameY);

			// reading the "Enemies" array:
			JSONArray jsonEnemies = (JSONArray) jsonObject.get("Enemies");

			// reading from the array:
			for (Object obj : jsonEnemies) {
				JSONObject jsonEnemy = (JSONObject) obj;
				
				// the enemy position is a double
				Double positionX = Double.parseDouble( ((JSONObject) jsonEnemy.get("position")).get("x").toString());
				// TODO: Double positionY =

				String projectileStrategy = jsonEnemy.get("projectile").toString();

				// TODO: delete me, this is just a demonstration:
				System.out.println("Enemy x: " + positionX + ", projectile: " + projectileStrategy);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Vector2D getPlayer() {
		JSONParser parser = new JSONParser();

		try {
			Object object = parser.parse(new FileReader(this.path));

			JSONObject jsonObject = (JSONObject) object;

			JSONObject jsonGame = (JSONObject) jsonObject.get("Player");

			Long x = (Long) ((JSONObject) jsonGame.get("position")).get("x");
			Long y = (Long) ((JSONObject) jsonGame.get("position")).get("y");

			return new Vector2D(x, y);

		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
    }

	public Long[] getGameSize() {
		Long[] size = new Long[2];

		JSONParser parser = new JSONParser();

		try {
			Object object = parser.parse(new FileReader(this.path));

			JSONObject jsonObject = (JSONObject) object;

			JSONObject jsonGame = (JSONObject) jsonObject.get("Game");

			Long x = (Long) ((JSONObject) jsonGame.get("size")).get("x");
			Long y = (Long) ((JSONObject) jsonGame.get("size")).get("y");

			size[0] = x;
			size[1] = y;

			return size;

		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Your main method will probably be in another file!
	 * 
	 * @param args First argument is the path to the config file
	 */
	public static void main(String[] args) {
		// if a command line argument is provided, that should be used as the path
		// if not, you can hard-code a default. e.g. "src/main/resources/config.json"
		// this makes it easier to test your program with different config files
		String configPath;
		if (args.length > 0) {
			configPath = args[0];
		} else {
			configPath = "src/main/resources/config.json";
		}
		// parse the file:
		ConfigReader.parse(configPath);
	}

}
