package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;
import javafx.scene.image.Image;

import java.io.File;

public class GreenState implements BunkerState{

    private Bunker bunker;

    public GreenState(Bunker bunker) {
        this.bunker = bunker;
    }

    /**
     * This class is responsible for causing the state of the bunker to
     * change to the colour yellow
     */
    @Override
    public void takeDamage() {
        bunker.changeState(new YellowState(bunker));
        bunker.setImage(new Image(new File("src/main/resources/yellowBunker.png").toURI().toString(),
                bunker.getWidth(), bunker.getHeight(), true, true));
        bunker.setHealth(bunker.getHealth() - 1);
    }

}
