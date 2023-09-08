package invaders.entities.builderPattern;

import invaders.physics.Vector2D;

public interface BunkerEnemyBuilder {

    public BunkerEnemyBuilder addPosition(int index);

    public BunkerEnemyBuilder addSize(int index);

    public BunkerEnemyBuilder addProjectileStrategy(int index);

}
