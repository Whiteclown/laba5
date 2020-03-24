package Bees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BeeBig extends Bee {
    public static int countBeeBig = 0;
    static public Image image;

    public BeeBig(int x, int y){
        super(x, y, image);
        countBeeBig++;
        countBees++;
    }

    static {
        try {
            image = ImageIO.read(new File("src/pictures/BeeBig.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
