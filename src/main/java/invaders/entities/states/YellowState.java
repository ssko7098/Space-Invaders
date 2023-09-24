package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;
import javafx.scene.image.Image;

import java.io.File;

/**
 * This class is responsible for causing the state of the bunker to
 * change to the colour red
 */
public class YellowState implements BunkerState{

    private Bunker bunker;

    public YellowState(Bunker bunker) {
        this.bunker = bunker;
    }

    @Override
    public void takeDamage() {
        bunker.changeState(new RedState(bunker));
        bunker.setImage(new Image(new File("src/main/resources/redBunker.png").toURI().toString(),
                bunker.getWidth(), bunker.getHeight(), false, true));
        bunker.setHealth(bunker.getHealth() - 1);
    }

}
