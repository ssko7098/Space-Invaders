package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;

public class RedState implements BunkerState{

    private Bunker bunker;

    public RedState(Bunker bunker) {
        this.bunker = bunker;
    }
    @Override
    public void loseLife() {
        return;
    }
}
