package Bees;

public class BeeWork extends Bee {
    public static int countBeeWork = 0;

    public BeeWork(int x, int y, String pathToImg){
        super(x, y, pathToImg);
        countBeeWork++;
        countBees++;
    }
}
