package Bees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

abstract public class Bee implements IBehaviour{
    private int x;
    private int y;
    public static int countBees = 0;
    private String pathToImg;
    private Image image;

    Bee(int x, int y, String pathToImg){
        this.x = x;
        this.y = y;
        this.pathToImg = pathToImg;
        try {
            image = ImageIO.read(new File(pathToImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Image getImage() {
        return image;
    }
}
