package invaders.entities.builderPattern;

public interface AlienBuilderInterface {

    public AlienBuilderInterface addProjectileStrategy(int index);

    public AlienBuilderInterface addPosition(int index);

    public Alien build();

}
