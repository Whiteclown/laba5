package Habitat;

import Bees.Bee;
import Bees.BeeBig;
import Bees.BeeWork;
import views.FirstFrame;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Habitat {
    SingletonObjects singletonObjects = SingletonObjects.getInstance();
    SingletonID singletonID = SingletonID.getInstance();
    SingletonTimeBorn singletonTimeBorn = SingletonTimeBorn.getInstance();
    private int N1; //Bigers
    private int N2; //workers with P
    private double P; // вероятность рождения рабочих пчел
    private double K;   // проценты для расчета вероятности рождения трутней
    FirstFrame firstFrame;
    private int WIDTH = 600;
    private int HEIGHT = 600;
    int time = 0;
    int timeOfLifeWork = 3;
    int timeOfLifeBig = 5;
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

    public synchronized void update(int time){
        N1 = firstFrame.getN1();
        N2 = firstFrame.getN2();
        P = firstFrame.getP();
        K = firstFrame.getK();
        timeOfLifeWork = firstFrame.getTimeOfLifeWork();
        timeOfLifeBig = firstFrame.getTimeOfLifeBig();
        if ((Math.random() <= P) && (time % N2 == 0)){
            Point rPoint = generatePoint();
            Bee mBee = new BeeWork(rPoint.x, rPoint.y, 0, 0, rPoint.x, rPoint.y,  timeOfLifeWork, time);
            SingletonObjects.beesList.add(mBee);
            SingletonID.beesSet.add(mBee.getID());
            SingletonTimeBorn.beesMap.put(mBee.getID(), mBee.getTimeOfBorn());
        }
        if ((BeeWork.countBeeWork != 0)){
            if (((((double)BeeBig.countBeeBig) / BeeWork.countBeeWork) < K) && (time % N1 == 0)){
                Point rPoint = generatePoint();
                Point aPoint = generatePoint();
                Bee mBee = new BeeBig(rPoint.x, rPoint.y, aPoint.x, aPoint.y, rPoint.x, rPoint.y, timeOfLifeBig, time);
                SingletonObjects.beesList.add(mBee);
                SingletonID.beesSet.add(mBee.getID());
                SingletonTimeBorn.beesMap.put(mBee.getID(), mBee.getTimeOfBorn());
            }
        }
        remove(time);
        firstFrame.beesDraw();
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
        SingletonObjects.beesList = new ArrayList<>();
        SingletonTimeBorn.beesMap = new TreeMap<>();
        SingletonID.beesSet = new HashSet<>();
        time = 0;
    }

    public void pauseTimer(){
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
    }

    private void remove(int time) {
        for (int i = 0; i < SingletonObjects.beesList.size(); i++) {
            if ((SingletonObjects.beesList.get(i).getTimeOfBorn() + SingletonObjects.beesList.get(i).getTimeOfLife()) == time) {
                int tempId = SingletonObjects.beesList.get(i).getID();
                SingletonObjects.beesList.remove(i);
                SingletonID.beesSet.remove(tempId);
                SingletonTimeBorn.beesMap.remove(tempId);
                i--;
            }
        }
    }

    synchronized public void saveFile(File file) {
        ObjectOutputStream objectOutputStream = null;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (fileOutputStream != null) {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                Serialization serialization = new Serialization();
                objectOutputStream.writeObject(serialization);
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            if (objectOutputStream != null) {
                try { objectOutputStream.close(); }
                catch (IOException e) { e.printStackTrace(); }
            }
        }
    }

    synchronized public void loadFile(File file) {
        Bee.setCountBees(0);
        BeeBig.setCountBeeBig(0);
        BeeWork.setCountBeeWork(0);
        SingletonObjects.beesList.clear();
        SingletonID.beesSet.clear();
        SingletonTimeBorn.beesMap.clear();
        ObjectInputStream objectInputStream = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            if(fileInputStream != null) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                Serialization serialization = (Serialization) objectInputStream.readObject();
                for(int i = 0; i < serialization.beesList.size(); i++) {
                    if(serialization.beesList.get(i) instanceof BeeWork) {
                        Bee beeWork = new BeeWork(serialization.beesList.get(i).getX0(), serialization.beesList.get(i).getY0(), serialization.beesList.get(i).getX1(), serialization.beesList.get(i).getY1(), serialization.beesList.get(i).getX(), serialization.beesList.get(i).getY(), serialization.beesList.get(i).getTimeOfLife(), time);
                        SingletonObjects.beesList.add(beeWork);
                        SingletonID.beesSet.add(beeWork.getID());
                        SingletonTimeBorn.beesMap.put(beeWork.getID(), beeWork.getTimeOfBorn());
                    }
                    if(serialization.beesList.get(i) instanceof BeeBig) {
                        Bee beeBig = new BeeBig(serialization.beesList.get(i).getX0(), serialization.beesList.get(i).getY0(), serialization.beesList.get(i).getX1(), serialization.beesList.get(i).getY1(), serialization.beesList.get(i).getX(), serialization.beesList.get(i).getY(), serialization.beesList.get(i).getTimeOfLife(), time);
                        SingletonObjects.beesList.add(beeBig);
                        SingletonID.beesSet.add(beeBig.getID());
                        SingletonTimeBorn.beesMap.put(beeBig.getID(), beeBig.getTimeOfBorn());
                    }
                }
            }
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        finally {
            try { objectInputStream.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

}