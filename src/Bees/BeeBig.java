package Bees;

public class BeeBig extends Bee {
    public static int countBeeBig = 0;

    public BeeBig(int x, int y, String pathToImg){
        super(x, y, pathToImg);
        countBeeBig++;
        countBees++;
    }
}
