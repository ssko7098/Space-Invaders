package invaders.entities.builderPattern;

import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

public interface BunkerEnemyBuilder {

    public BunkerEnemyBuilder addPosition(int index);

    public BunkerEnemyBuilder addSize(int index);

    public BunkerEnemyBuilder addProjectileStrategy(int index);

    public Renderable build();

}
