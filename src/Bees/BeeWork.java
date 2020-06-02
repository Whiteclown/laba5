package Bees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BeeWork extends Bee {
    public static int countBeeWork = 0;
    static public Image image;

    public BeeWork(int x, int y, int timeOfLife, int timeOfBorn){
        super(x, y, timeOfLife, timeOfBorn);
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
}
