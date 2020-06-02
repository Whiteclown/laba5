package Bees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BeeBig extends Bee {
    public static int countBeeBig = 0;
    static public Image image;

    public BeeBig(int x, int y, int timeOfLife, int timeOfBorn){
        super(x, y, timeOfLife, timeOfBorn);
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
