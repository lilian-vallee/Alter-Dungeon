package com.alterdungeon.game.entities;

import com.alterdungeon.game.AlterDungeonGame;
import com.alterdungeon.game.maps.ExplorationMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player {

    private AlterDungeonGame game;
    private Texture texture = new Texture("entities/player.png");

    Vector3 position;

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(float y, float x) {
        this.position.x = y;//inverser pour la tiled map
        this.position.y = x;
        this.position.z = 0;
    }

    public Player(AlterDungeonGame game) {
        this.game = game;
    }

    public void update() {
        game.spriteBatch.draw(this.texture, game.V_WITDH/2 -16, game.V_HEIGHT/2 -16);
    }

    public void setMap(ExplorationMap map) {
        position = map.getSpawn();
    }

    public void move(int direction) {
        //todo
    }


    //    //chargement des textures de la map
//    Texture tiles = new Texture(Gdx.files.internal("maps/tiles1To10.png"));
//    //partitionement des textures
//    TextureRegion[][] splitTiles = TextureRegion.split(tiles, 32, 32);

}