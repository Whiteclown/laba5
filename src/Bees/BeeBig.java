package Bees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BeeBig extends Bee {
    public static int countBeeBig = 0;
    static public Image image;

    public BeeBig(int x0, int y0, int x1, int y1, int x, int y, int timeOfLife, int timeOfBorn){
        super(x0, y0, x1, y1, x, y, timeOfLife, timeOfBorn);
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

    @Override
    public Image getImage() {
        return image;
    }
}
