package Bees;

import java.awt.*;

abstract public class Bee implements IBehaviour{
    private int x;
    private int y;
    public static int countBees = 0;

    Bee(int x, int y){
        this.x = x;
        this.y = y;
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
}