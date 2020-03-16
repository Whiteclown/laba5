package Bees;

abstract public class Bee implements IBehaviour{
    private int x;
    private int y;
    public static int countBees = 0;
    private String pathToImg;

    Bee(int x, int y, String pathToImg){
        this.x = x;
        this.y = y;
        this.pathToImg = pathToImg;
    }

    public String getPathToImg() {
        return pathToImg;
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
}
