package invaders.entities;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.builderPattern.Alien;
import invaders.rendering.Renderable;

import java.util.ArrayList;

public class AlienHorde implements GameObject {

    private ArrayList<Alien> alienList = new ArrayList<>();
    private ConfigReader config = new ConfigReader();
    private double rightCount = 0;
    private double leftCount = 0;
    private boolean movingDown;

    public void addAlien(Renderable alien) {
        alienList.add( (Alien) alien);
    }

    public void setDirectionForAll(Direction dir) {
        for(Alien alien: alienList) {
            alien.setDirection(dir);
        }
    }

    public ArrayList<Alien> getAlienHorde() {
        return alienList;
    }

    public void removeAlien(Renderable alien) {
        alienList.remove( (Alien) alien);

        for(Alien alienB: alienList) {
            alienB.setSpeed(alienB.getSpeed() + 0.04);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        for(Alien alien : alienList) {
            int gameSize = Integer.parseInt(config.getGameSize()[0].toString());

            if((alien.getPosition().getX() >= (gameSize - 1 - alien.getWidth())) && rightCount <= 1) {
                setDirectionForAll(Direction.DOWN);
                movingDown = true;
                break;
//                rightCount += 0.017;
            }
            else if((alien.getPosition().getX() >= (gameSize - 1 - alien.getWidth())) && rightCount > 1) {
                setDirectionForAll(Direction.LEFT);
                movingDown = false;
                rightCount = 0;
                break;
            }

            if(alien.getPosition().getX() <= 1 && leftCount <= 1) {
                setDirectionForAll(Direction.DOWN);
                leftCount += 0.017;
            }
            else if (alien.getPosition().getX() <= 1 && leftCount > 1){
                setDirectionForAll(Direction.RIGHT);
                leftCount = 0;
            }

        }

        if(movingDown) {
            rightCount += 0.017;
        }
    }
}
