package Habitat;

import Bees.Bee;

import java.util.ArrayList;

public class Singleton {
    public static ArrayList<Bee> beesList;
    private static Singleton instance;
    private Singleton(){
        beesList = new ArrayList<>();
    }

    public static Singleton getInstance() {
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
