package Habitat;

import Bees.Bee;
import Bees.BeeBig;
import Bees.BeeWork;
import views.VisualPanel;

import java.awt.*;

import static java.lang.Math.sqrt;

public class BeeBigAI extends BaseAI{
    private VisualPanel visualPanel;
    int tempX, tempY, tempX0, tempY0, tempXd, tempYd, tempX1, tempY1;
    int tempT, V = 1;
    int N = 3;

    public BeeBigAI(String myThread, VisualPanel visualPanel) {
        super(myThread);
        this.visualPanel = visualPanel;
        start();
    }

    @Override
    public synchronized void run() {
        while (true){
            if (SingletonObjects.beesList.size() != 0){
                for (int i = 0; i < SingletonObjects.beesList.size(); i++){
                    if (SingletonObjects.beesList.get(i) instanceof BeeWork){
                        continue;
                    }
                    Bee bee = SingletonObjects.beesList.get(i);
                    tempX = bee.getX();
                    tempY = bee.getY();
                    tempX0 = bee.getX0();
                    tempY0 = bee.getY0();
                    tempX1 = bee.getX1();
                    tempY1 = bee.getY1();
                    tempT = bee.getTimeFromStart();
                    tempXd = tempX1 - tempX0;
                    tempYd = tempY1 - tempY0;
                    tempX = (int) (tempX0 + ((tempXd / sqrt(tempXd^2 + tempYd^2)) * V * tempT));
                    tempY = (int) (tempY0 + ((tempYd / sqrt(tempXd^2 + tempYd^2)) * V * tempT));
                    bee.setX(tempX);
                    bee.setY(tempY);
                    System.out.println("F");
                    /*if (tempT > Math.abs(sqrt((tempX1 - tempX0)^2 + (tempY1 - tempY0)^2))){
                        bee.setY0(tempY);
                        bee.setX0(tempX);
                        bee.setY1(tempY0);
                        bee.setX1(tempX0);
                        tempT = -1;
                    }*/
                    if (tempT % N == 0){
                        int xp = (int) Math.random() * 600;
                        int yp = (int) Math.random() * 600;
                        bee.setY0(tempY);
                        bee.setX0(tempX);
                        bee.setY1(yp);
                        bee.setX1(xp);
                        tempT = -1;
                    }
                    tempT++;
                    bee.setTimeFromStart(tempT);
                }
            }
            visualPanel.paintBee();
            try { Thread.sleep(100); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
