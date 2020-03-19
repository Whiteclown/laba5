package Habitat;

import Bees.Bee;
import Bees.BeeWork;
import views.FirstFrame;
import views.FirstPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Habitat {
    private int N1; //Bigers
    private int N2; //workers with P
    private int P; // вероятность рождения рабочих пчел
    private double K;   // проценты для расчета вероятности рождения трутней
    FirstFrame firstFrame;
    FirstPanel firstPanel;
    private int WIDTH = 600;
    private int HEIGHT = 600;
    int time = 0;
    final private String pathToBig = "src/pictures/UsualBee";
    final private String pathToWork = "src/pictures/UsualBee";
    private ArrayList<Bee> beesList = new ArrayList<>();
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            time++;
            update(time);
        }
    };

    public Habitat(int N1, int N2, int P, double K, FirstFrame firstFrame){
        this.N1 = N1;
        this.N2 = N2;
        this.P = P;
        this.K = K;
        this.firstFrame = firstFrame;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    private Point generatePoint(){  //Разобрать!
        int x =(int)(Math.random() * (firstFrame.getWidth() - 99));
        int y =(int)(Math.random() * (firstFrame.getHeight() - 99));
        return new Point(x, y);
    }

    public void update(int time){
        Point rPoint = generatePoint();
        Bee mBee = new BeeWork(rPoint.x, rPoint.y, pathToWork);
        beesList.add(mBee);
        firstFrame.beesDraw(beesList);
        firstFrame.updateTime(time);
    }

    public void startBorn(){
        timer.schedule(timerTask, 0,1000);
    }

    public void stopBorn(){
        timer.cancel();
        timer.purge();
    }
}
