package Habitat;

import Bees.Bee;

import java.util.HashSet;

public class SingletonID {
    public static HashSet<Integer> beesSet;
    private static SingletonID instance;
    private SingletonID(){
        beesSet = new HashSet<>();
    }

    public static SingletonID getInstance() {
        if (instance == null){
            instance = new SingletonID();
        }
        return instance;
    }
}