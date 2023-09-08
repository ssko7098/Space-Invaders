package invaders.entities.factoryMethod;
import invaders.rendering.Renderable;

public interface ProjectileFactory {

    public Projectile make(Renderable entity);
}
