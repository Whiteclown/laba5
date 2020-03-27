package Habitat;

import Bees.Bee;
import Bees.BeeBig;
import Bees.BeeWork;
import views.FirstFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Habitat {
    private int N1; //Bigers
    private int N2; //workers with P
    private double P; // вероятность рождения рабочих пчел
    private double K;   // проценты для расчета вероятности рождения трутней
    FirstFrame firstFrame;
    private int WIDTH = 600;
    private int HEIGHT = 600;
    int time = 0;
    private ArrayList<Bee> beesList = new ArrayList<>();
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            time++;
            update(time);
        }
    };

    public Habitat(int N1, int N2, double P, double K, FirstFrame firstFrame){
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

    private Point generatePoint(){
        int x =(int)(Math.random() * (firstFrame.getVisualPanelWidth()));
        int y =(int)(Math.random() * (firstFrame.getVisualPanelHeight()));
        return new Point(x, y);
    }

    public void update(int time){
        N1 = firstFrame.getN1();
        N2 = firstFrame.getN2();
        P = firstFrame.getP();
        K = firstFrame.getK();
        if ((Math.random() <= P) && (time % N2 == 0)){
            Point rPoint = generatePoint();
            Bee mBee = new BeeWork(rPoint.x, rPoint.y);
            beesList.add(mBee);
        }
        if (BeeWork.countBeeWork != 0){
            if (((((double)BeeBig.countBeeBig) / BeeWork.countBeeWork) < K) && (time % N1 == 0)){
                Point rPoint = generatePoint();
                Bee mBee = new BeeBig(rPoint.x, rPoint.y);
                beesList.add(mBee);
            }
        }
        firstFrame.beesDraw(beesList);
        firstFrame.updateTime(time);
    }

    public void startBorn(){
        timer.schedule(timerTask, 0,1000);
    }

    public void stopBorn(){
        timer.cancel();
        timer.purge();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                time++;
                update(time);
            }
        };
        beesList = new ArrayList<>();
        time = 0;
    }
}