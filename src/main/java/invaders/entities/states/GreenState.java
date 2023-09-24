package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;
import javafx.scene.image.Image;

import java.io.File;

/**
 * This class is responsible for causing the state of the bunker to
 * change to the colour yellow
 */
public class GreenState implements BunkerState{

    private Bunker bunker;

    public GreenState(Bunker bunker) {
        this.bunker = bunker;
    }

    @Override
    public void takeDamage() {
        bunker.changeState(new YellowState(bunker));
        bunker.setImage(new Image(new File("src/main/resources/yellowBunker.png").toURI().toString(),
                bunker.getWidth(), bunker.getHeight(), false, true));
        bunker.setHealth(bunker.getHealth() - 1);
    }

}
