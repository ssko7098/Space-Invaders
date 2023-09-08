package invaders.entities.projectiles;
import invaders.rendering.Renderable;

public interface ProjectileFactory {

    public Projectile make(Renderable entity);
}
