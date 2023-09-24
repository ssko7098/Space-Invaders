package invaders.entities.builderPattern;

public interface BunkerBuilderInterface {
    public BunkerBuilderInterface addSize(int index);

    public BunkerBuilderInterface addPosition(int index);

    public Bunker build();
}
