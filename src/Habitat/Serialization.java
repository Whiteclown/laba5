package Habitat;

import Bees.Bee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class Serialization implements Serializable {
    public ArrayList<Bee> beesList;
    public HashSet<Integer> beesSet;
    public TreeMap<Integer, Integer> beesMap;

    public Serialization() {
        this.beesList = SingletonObjects.beesList;
        this.beesSet = SingletonID.beesSet;
        this.beesMap = SingletonTimeBorn.beesMap;
    }
}