package invaders.entities;

import invaders.ConfigReader;
import invaders.GameObject;
import invaders.entities.builderPattern.Alien;
import invaders.rendering.Renderable;

import java.util.ArrayList;
import java.util.Random;

public class AlienHorde implements GameObject {

    private ArrayList<Alien> alienList = new ArrayList<>();
    private ConfigReader config = new ConfigReader();
    private double rightCount = 0;
    private double leftCount = 0;
    private boolean movingDown;
    private boolean rightWall;
    private boolean leftWall;

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
    public void update() {
        for(int i=0; i<alienList.size(); i++) {
            Alien alien = alienList.get(i);

            int gameSize = Integer.parseInt(config.getGameSize()[0].toString());

            if((alien.getPosition().getX() >= (gameSize - 1 - alien.getWidth())) && rightCount <= 1 && !movingDown) {
                setDirectionForAll(Direction.DOWN);
                movingDown = true;
                rightWall = true;
                break;
            }
            else if(movingDown && rightCount > 1) {
                setDirectionForAll(Direction.LEFT);
                movingDown = false;
                rightWall = false;
                rightCount = 0;
                break;
            }

            if(alien.getPosition().getX() <= 1 && leftCount <= 1 && !movingDown) {
                setDirectionForAll(Direction.DOWN);
                movingDown = true;
                leftWall = true;
                break;
            }
            else if (movingDown && leftCount > 1){
                setDirectionForAll(Direction.RIGHT);
                movingDown = false;
                leftWall = false;
                leftCount = 0;
                break;
            }

        }

        if(movingDown && rightWall) {
            rightCount += 0.017;
        }
        else if(movingDown && leftWall) {
            leftCount += 0.017;
        }
    }
}
