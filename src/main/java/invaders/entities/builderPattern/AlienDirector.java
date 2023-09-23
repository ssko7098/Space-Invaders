package invaders.entities.builderPattern;

import invaders.entities.Entity;

public class AlienDirector {

    public Entity buildAlien(AlienBuilderInterface builder, int index) {
        builder.addProjectileStrategy(index)
                .addPosition(index);

        return builder.build();
    }
}
