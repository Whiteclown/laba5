package Habitat;

import Bees.Bee;
import views.FirstFrame;

import java.util.ArrayList;
import java.util.Timer;

public class Habitat {
    private int N1; //Bigers
    private int N2; //workers with P
    private int P; // вероятность рождения рабочих пчел
    private double K;   // проценты для расчета вероятности рождения трутней
    FirstFrame firstFrame;
    private int WIDTH = 600;
    private int HEIGHT = 600;
    final private String pathToBig = "src/pictures/UsualBee";
    final private String pathToWork = "src/pictures/UsualBee";
    private ArrayList<Bee> beesList = new ArrayList<>();
    private Timer timer = new Timer();

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
}
