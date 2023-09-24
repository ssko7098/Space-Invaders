package invaders.entities;

import invaders.rendering.Renderable;
import java.util.ArrayList;

public interface AlienController {

    void addAlien(Renderable alien);

    void setDirectionForAll(Direction dir);

    void removeAlien(Renderable alien);

    ArrayList<Shootable> getAlienHorde();

    void update();

}
