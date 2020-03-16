package Bees;

import Bees.Bee;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BeeWork extends Bee {
    public static int countBeeWork = 0;
    public static Image image;

    BeeWork(int x, int y, String pathToImg){
        super(x, y, pathToImg);
        countBeeWork++;
        countBees++;
    }

    static {
        try {
            image = ImageIO.read(new File("src/pictures/UsualBee.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
