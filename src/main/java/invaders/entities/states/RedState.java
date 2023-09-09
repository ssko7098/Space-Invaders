package invaders.entities.states;

import invaders.entities.builderPattern.Bunker;

public class RedState implements BunkerState{

    @Override
    public void loseLife(Bunker bunker) {
        bunker.markForDelete();
    }

}
