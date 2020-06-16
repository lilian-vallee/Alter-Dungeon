package com.alterdungeon.game.entities.stats;

import com.alterdungeon.game.entities.stats.objet.Inventaire;
import com.alterdungeon.game.entities.stats.objet.equipement.Accessoire;
import com.alterdungeon.game.entities.stats.objet.equipement.Arme;
import com.alterdungeon.game.entities.stats.objet.equipement.Botte;
import com.alterdungeon.game.entities.stats.objet.equipement.Casque;
import com.alterdungeon.game.entities.stats.objet.equipement.Gant;
import com.alterdungeon.game.entities.stats.objet.equipement.Torse;

public class PlayerStats extends Stats {

    private int niveau;// niveau du joueur
    private int exp;// experience du joueur

    private Inventaire inventaire;//inventaire du joueur

    private int maxEtage; // record de l'étage maximum atteind
    private int actualEtage; // Etage actuel

    private Arme arme1;
    private Arme arme2;
    private Casque casque;
    private Torse torse;
    private Gant gant;
    private Botte botte;
    private Accessoire accessoire;

    public PlayerStats(){

    }

}
