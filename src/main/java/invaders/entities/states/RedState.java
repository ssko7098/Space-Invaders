package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;

/**
 * This class is responsible for destroying the bunker object
 */
public class RedState implements BunkerState{

    private Bunker bunker;

    public RedState(Bunker bunker) {
        this.bunker = bunker;
    }

    @Override
    public void takeDamage() {
        bunker.setHealth(bunker.getHealth() - 1);
    }
}
