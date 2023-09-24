package invaders;

import javafx.application.Application;
import javafx.stage.Stage;
import invaders.engine.GameEngine;
import invaders.engine.GameWindow;
import java.util.Map;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Map<String, String> params = getParameters().getNamed();

        ConfigReader config = new ConfigReader();

        int width = Integer.parseInt(config.getGameSize()[0].toString());
        int height = Integer.parseInt(config.getGameSize()[1].toString());

        GameEngine model = new GameEngine();
        GameWindow window = new GameWindow(model, width, height);
        window.run();

        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }
}
