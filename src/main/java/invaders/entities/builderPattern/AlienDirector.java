package invaders.entities.builderPattern;

import invaders.entities.Entity;

/**
 * Part of the Builder pattern.
 * Establishes a method for creating specific Alien objects
 */
public class AlienDirector {

    private AlienBuilderInterface builder;
    public AlienDirector(AlienBuilderInterface builder) {
        this.builder = builder;
    }

    public Entity build(int index) {
        builder.addProjectileStrategy(index)
                .addPosition(index);

        return builder.build();
    }
}
