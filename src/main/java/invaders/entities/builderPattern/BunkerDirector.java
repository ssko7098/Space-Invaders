package invaders.entities.builderPattern;

import invaders.logic.Damagable;

/**
 * Part of the Builder pattern.
 * Establishes a method for creating specific Bunker objects
 */
public class BunkerDirector {

    private BunkerBuilderInterface builder;

    public BunkerDirector(BunkerBuilderInterface builder) {
        this.builder = builder;
    }

    public Damagable build(int index) {
        builder.addSize(index)
                .addPosition(index);

        return builder.build();
    }

}
