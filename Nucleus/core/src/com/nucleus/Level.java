package com.nucleus;

import java.util.ArrayList;
import java.util.List;

public class Level implements ILevel { //add level size
    private int level;
    private List<Nucleon> airborneNucleons = new ArrayList<Nucleon>();
    private Molecule molecule;

    public Level(int level, Molecule molecule){
        this.level = level;
        this.molecule = molecule;
    }

    public List<Nucleon> getNucleons(){
        return airborneNucleons;
    }

    public void addAirborneNucleon(Nucleon nucleon){
        airborneNucleons.add(nucleon);
    }

}