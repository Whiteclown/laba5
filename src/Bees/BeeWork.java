package Bees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BeeWork extends Bee {
    public static int countBeeWork = 0;
    static public Image image;

    public BeeWork(int x, int y){
        super(x, y, image);
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
}
