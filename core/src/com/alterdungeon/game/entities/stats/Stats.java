package com.alterdungeon.game.entities.stats;

public abstract class Stats {

    private int vieMax;//vie maximum
    private int vie;//vie actuelle
    private int manaMax;//mana maximum
    private int mana;//mana actuelle

    //caractéristique
    private int force;//dégats phisique
    private int endurance;//defense phisique
    private int intelligence;//degat magique
    private int resistance;//defense magique
    private int dexterite;//chance d'esquive
    private int agilite;//chance de degat critique (dégats x3)
    private int chance;//chance de drop de loot

    //pourcentage d'affinité pour les elements
    private float affFeu;
    private float affEau;
    private float affTerre;
    private float affVent;
    private float affLumiere;
    private float affTenebre;

}
