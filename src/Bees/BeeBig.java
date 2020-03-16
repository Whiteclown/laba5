package Bees;

import Bees.Bee;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BeeBig extends Bee {
    public static int countBeeBig = 0;
    public static Image image;

    BeeBig(int x, int y, String pathToImg){
        super(x, y, pathToImg);
        countBeeBig++;
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
