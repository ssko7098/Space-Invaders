package invaders.entities.builderPattern;

import invaders.logic.Damagable;

public class BunkerDirector {

    public Damagable buildBunker(BunkerBuilderInterface builder, int index) {
        builder.addSize(index)
                .addPosition(index);

        return builder.build();
    }

}
