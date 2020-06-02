package Bees;

import Habitat.SingletonID;

import java.awt.*;

abstract public class Bee implements IBehaviour{
    private int x;
    private int y;
    private int id;
    private int timeOfLife;
    private int timeOfBorn;
    public static int countBees = 0;

    Bee(int x, int y, int timeOfLife, int timeOfBorn){
        this.x = x;
        this.y = y;
        this.timeOfBorn = timeOfBorn;
        this.timeOfLife = timeOfLife;
        do {
            this.id = (int)(Math.random() * 100000);
        } while (SingletonID.beesSet.contains(this.id));
    }

    @Override
    public void move(int x, int y){}

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public abstract Image getImage();

    public int getTimeOfLife() {
        return timeOfLife;
    }

    public void setTimeOfLife(int timeOfLife) {
        this.timeOfLife = timeOfLife;
    }

    public int getTimeOfBorn() {
        return timeOfBorn;
    }

    public void setTimeOfBorn(int timeOfBorn) {
        this.timeOfBorn = timeOfBorn;
    }

    public int getID(){
        return id;
    }
}