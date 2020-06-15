package com.alterdungeon.game.entities.stats;

import com.alterdungeon.game.entities.stats.objet.Inventaire;

public class PlayerStats extends Stats {

    private int niveau;// niveau du joueur
    private int exp;// experience du joueur

    private Inventaire inventaire;//inventaire du joueur

    private int maxEtage; // record de l'étage maximum atteind
    private int actualEtage; // Etage actuel

}
