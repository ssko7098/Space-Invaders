package invaders.entities.builderPattern;

import invaders.entities.Entity;
import invaders.rendering.Renderable;

public class Director {

    /**
     * This class provides a blueprint for building the different concrete products.
     * Each object being built requires different 'components'
     */
    public Renderable buildAlien(BunkerEnemyBuilder builder, int index) {
        builder.addPosition(index)
                .addProjectileStrategy(index);

        return builder.build();
    }

    public Renderable buildBunker(BunkerEnemyBuilder builder, int index) {
        builder.addPosition(index)
                .addSize(index);

        return builder.build();
    }

}
