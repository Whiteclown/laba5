package Bees;

import Habitat.SingletonID;

import java.awt.*;

abstract public class Bee implements IBehaviour{
    private int x, y, x0, y0, x1, y1;
    private int id, timeFromStart;
    private int timeOfLife;
    private int timeOfBorn;
    public static int countBees = 0;

    Bee(int x0, int y0, int x1, int y1, int x, int y, int timeOfLife, int timeOfBorn){
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.x = x;
        this.y = y;
        this.timeFromStart = 0;
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

    @Override
    public int getX0() {
        return x0;
    }

    @Override
    public void setX0(int x0) {
        this.x0 = x0;
    }

    @Override
    public int getY0() {
        return y0;
    }

    @Override
    public void setY0(int y0) {
        this.y0 = y0;
    }

    @Override
    public int getTimeFromStart() {
        return timeFromStart;
    }

    @Override
    public void setTimeFromStart(int timeFromStart) {
        this.timeFromStart = timeFromStart;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }
}