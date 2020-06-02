package Habitat;

import Bees.Bee;

import java.util.ArrayList;

public class SingletonObjects {
    public static ArrayList<Bee> beesList;
    private static SingletonObjects instance;
    private SingletonObjects(){
        beesList = new ArrayList<>();
    }

    public static SingletonObjects getInstance() {
        if (instance == null){
            instance = new SingletonObjects();
        }
        return instance;
    }
}
