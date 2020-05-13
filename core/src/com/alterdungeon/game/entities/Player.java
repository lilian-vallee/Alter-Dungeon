package com.alterdungeon.game.entities;

import com.alterdungeon.game.AlterDungeonGame;
import com.alterdungeon.game.entities.animation.AnimationPlayer;
import com.alterdungeon.game.maps.ExplorationMap;
import com.alterdungeon.game.screen.Exploration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Player {

    AlterDungeonGame game;
    AnimationPlayer animation;
    TextureRegion texture;

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

    public Player(AlterDungeonGame game) {
        this.game = game;
        animation = new AnimationPlayer(new Texture("entities/playerSprite.png"));
        this.texture = animation.getSprite(0,1);
    }

    public void update() {
        game.spriteBatch.draw(texture, game.V_WITDH/2 -16, game.V_HEIGHT/2 -16);
    }

    public void setScreenLevel(Exploration screen) {
        this.screen = screen;
        this.map = screen.map;
        mapPosition = map.getSpawn();
        setPosition(screen.map.getSpawn().x*32, screen.map.getSpawn().y*32);
    }

    public void movePlayer(int direction) {
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
        }
        updateMapPosition();
    }

    private void updateMapPosition() {
        float tx = position.x;
        float ty = position.y;
        mapPosition.x = (int) tx/32;
        mapPosition.y = (int) ty/32;
    }
}