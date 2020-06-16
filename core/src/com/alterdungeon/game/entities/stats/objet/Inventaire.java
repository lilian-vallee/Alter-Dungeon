package com.alterdungeon.game.entities.stats.objet;

import java.util.ArrayList;

public class Inventaire {

    private Consommable consommable = new Consommable();

    //a sauvgarder dans un fichier JSON
    private ArrayList<Integer> inventaireConsommable;//Integer reference a la liste
    private ArrayList<Integer> inventaireArme;
    private ArrayList<Integer> inventaireArmure;


    public Consommable getConsommable(int id){
        return consommable.getConsommable(id);
    }
}
