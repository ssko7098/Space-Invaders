package invaders.entities.builderPattern;

import invaders.rendering.Renderable;

public class Director {

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
