package Habitat;

import Bees.Bee;

import java.util.TreeMap;

public class SingletonTimeBorn {
    public static TreeMap<Integer, Integer> beesMap;
    private static SingletonTimeBorn instance;
    private SingletonTimeBorn(){
        beesMap = new TreeMap<>();
    }

    public static SingletonTimeBorn getInstance() {
        if (instance == null){
            instance = new SingletonTimeBorn();
        }
        return instance;
    }
}