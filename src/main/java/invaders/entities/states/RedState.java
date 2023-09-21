package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;

public class RedState implements BunkerState{

    private Bunker bunker;

    public RedState(Bunker bunker) {
        this.bunker = bunker;
    }

    /**
     * This class is responsible for destroying the bunker object
     */
    @Override
    public void takeDamage() {
        bunker.setHealth(bunker.getHealth() - 1);
    }
}
