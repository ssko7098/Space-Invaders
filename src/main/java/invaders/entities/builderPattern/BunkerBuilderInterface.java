package invaders.entities.builderPattern;

import invaders.logic.Damagable;

public interface BunkerBuilderInterface {
    public BunkerBuilderInterface addSize(int index);

    public BunkerBuilderInterface addPosition(int index);

    public Damagable build();
}
