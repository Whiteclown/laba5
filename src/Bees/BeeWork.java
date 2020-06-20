package Bees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class BeeWork extends Bee implements Serializable {
    public static int countBeeWork = 0;
    static public Image image;

    public BeeWork(int x0, int y0, int x1, int y1, int x, int y, int timeOfLife, int timeOfBorn){
        super(x0, y0, x1, y1, x, y, timeOfLife, timeOfBorn, 0);
        countBeeWork++;
        countBees++;
    }

    static {
        try {
            image = ImageIO.read(new File("src/pictures/BeeWork.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image getImage() {
        return image;
    }

    public static void setCountBeeWork(int countBeeWork) {
        BeeWork.countBeeWork = countBeeWork;
    }
}
