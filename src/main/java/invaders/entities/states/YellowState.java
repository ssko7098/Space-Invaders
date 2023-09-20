package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;
import javafx.scene.image.Image;

import java.io.File;

public class YellowState implements BunkerState{

    private Bunker bunker;

    public YellowState(Bunker bunker) {
        this.bunker = bunker;
    }

    @Override
    public void loseLife() {
        bunker.changeState(new RedState(bunker));
        bunker.setImage(new Image(new File("src/main/resources/redBunker.png").toURI().toString(),
                bunker.getWidth(), bunker.getHeight(), true, true));
    }

}
