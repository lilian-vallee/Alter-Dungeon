package com.alterdungeon.game.entities;

import com.alterdungeon.game.AlterDungeonGame;
import com.alterdungeon.game.entities.animation.AnimationPlayer;
import com.alterdungeon.game.maps.ExplorationMap;
import com.alterdungeon.game.screen.Exploration;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Player {

    AlterDungeonGame game;
    public AnimationPlayer animation;

    Exploration screen;
    ExplorationMap map;

    Vector3 mapPosition;
    Vector3 position;

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position = new Vector3(x, y,0);
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
        setPosition(screen.map.getSpawn().x*32, screen.map.getSpawn().y*32);
    }

    /**
     * Bouge le joueur en fonction de la direction
     * @param direction
     */
    public void movePlayer(int direction) {
        /*
        direction :
        1 = up
        2 = left
        3 = right
        4 = down
         */
        switch (direction){
            case 0 :
                screen.camera.translate(0,-3,0);
                position.y += -3;
                break;
            case 1 :
                screen.camera.translate(-3,0,0);
                position.x += -3;
                break;
            case 2 :
                screen.camera.translate(3,0,0);
                position.x += 3;
                break;
            case 3 :
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
    }
}