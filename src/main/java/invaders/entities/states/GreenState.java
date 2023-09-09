package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;
import javafx.scene.image.Image;

import java.io.File;

public class GreenState implements BunkerState{

    @Override
    public void loseLife(Bunker bunker) {
        bunker.changeState(new YellowState());
        bunker.setImage(new Image(new File("src/main/resources/yellowBunker.png").toURI().toString(),
                bunker.getWidth(), bunker.getHeight(), true, true));
    }

}
