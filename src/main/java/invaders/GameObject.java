package invaders;

import invaders.rendering.Renderable;

// contains basic methods that all GameObjects must implement
public interface GameObject extends Renderable {
    public void update();

}
