package com.alterdungeon.game.entities.stats.objet;

import java.util.HashMap;

public class Consommable extends Objet {

    private HashMap<Integer, Consommable> listConsommable = new HashMap<Integer, Consommable>();//a init via un json pour sauvegarder les objets

    public Consommable getConsommable(int id){
        return listConsommable.get(id);
    }

}
