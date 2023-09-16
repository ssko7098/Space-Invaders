package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;
import javafx.scene.image.Image;

import java.io.File;

public class YellowState implements BunkerState{

    @Override
    public void changeState(Bunker bunker) {
        bunker.changeState(new RedState());
        bunker.setImage(new Image(new File("src/main/resources/redBunker.png").toURI().toString(),
                bunker.getWidth(), bunker.getHeight(), true, true));
    }

}
