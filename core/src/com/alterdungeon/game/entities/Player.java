package com.alterdungeon.game.entities;

import com.alterdungeon.game.AlterDungeonGame;
import com.alterdungeon.game.entities.animation.AnimationPlayer;
import com.alterdungeon.game.entities.stats.PlayerStatistique;
import com.alterdungeon.game.maps.ExplorationMap;
import com.alterdungeon.game.screen.Exploration;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Player {

    private PlayerStatistique stat;
    AlterDungeonGame game;
    public AnimationPlayer animation;

    Exploration screen;
    ExplorationMap map;


    Vector3 mapPosition;
    Vector3 position;

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getAnimation(){
        return animation.getAnimation();
    }

    /**
     * Constructeur de la classe Joueur
     * Sauvegarde non implémentée => Créé un nouveau joueur à chaque fois
     * @param game
     */
    public Player(AlterDungeonGame game) {
        this.game = game;
        animation = new AnimationPlayer(this);
        this.stat = new PlayerStatistique();
    }

    public void update(float delta) {
        animation.update(delta);
    }

    /**
     * met à jour les attributs du joueur à l'arrivé sur une map
     * @param screen
     */
    public void setScreenLevel(Exploration screen) {
        this.screen = screen;
        this.map = screen.map;
        mapPosition = map.getSpawn();
        position = new Vector3(mapPosition.x * 32, mapPosition.y *32, 0);
    }

    /**
     * Bouge le joueur en fonction de la direction
     * @param direction
     */
    public void movePlayer(int direction) {
        int[] newposition = {(int)position.x, (int)position.y};
        /*
        direction :
        0 = down
        1 = left
        2 = right
        3 = up
         */
        switch (direction){
            case 0 :
                // test de collision avec une simulation du deplacement avec +32 pour up et right du a la taille de la texture du personnage
                if (map.getCollision(newposition[0]/32, (newposition[1]-3)/32))
                {
                    break;
                }
                screen.camera.translate(0,-3,0);
                position.y += -3;
                break;
            case 1 :
                if (map.getCollision((newposition[0]-3)/32, (newposition[1])/32)) {
                    break;
                }
                screen.camera.translate(-3,0,0);
                position.x += -3;
                break;
            case 2 :
                if (map.getCollision((newposition[0]+3+32)/32, (newposition[1])/32)) {
                    break;
                }
                screen.camera.translate(3,0,0);
                position.x += 3;
                break;
            case 3 :
                if (map.getCollision((newposition[0])/32, (newposition[1]+3+32)/32)) {
                    break;
                }
                screen.camera.translate(0,3,0);
                position.y += 3;
                break;
            default:
                break;
        }
        //mise à jour des coordonnée sur la map
        updateMapPosition();
        //Animation de deplacement
        if(direction != -1)
            animation.animateMove(direction);
        else {
            animation.animateStanding();
        }
    }

    /**
     * Mise à jour des coordonnée du joueur sur la map
     */
    private void updateMapPosition() {
        float tx = position.x;
        float ty = position.y;
        mapPosition.x = (int) tx/32;
        mapPosition.y = (int) ty/32;
        //System.out.println(mapPosition);
        //System.out.println(position);
    }
}