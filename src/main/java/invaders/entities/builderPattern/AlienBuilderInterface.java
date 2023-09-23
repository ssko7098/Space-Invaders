package invaders.entities.builderPattern;

import invaders.entities.Entity;

public interface AlienBuilderInterface {

    public AlienBuilderInterface addProjectileStrategy(int index);

    public AlienBuilderInterface addPosition(int index);

    public Entity build();

}
