package invaders.entities.factoryMethod;
import invaders.entities.Entity;
import invaders.rendering.Renderable;

public interface ProjectileFactory {

    /**
     * This class is responsible for creating new Projectile objects
     * using the factory method
     */
    public Projectile make(Entity entity);
}
