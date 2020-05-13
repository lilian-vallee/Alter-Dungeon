package com.alterdungeon.game.entities.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPlayer {

    TextureRegion[][] sprite;
    int counter = 0;

    public AnimationPlayer(Texture texture){
        sprite = TextureRegion.split(texture, 32,32);
    }

    public TextureRegion getSprite(int x, int y) {
        return sprite[x][y];
    }

    public TextureRegion animate(int dir){
        updateCounter(counter++);
        return sprite[dir][counter];
    }

    private void updateCounter(int counter){
        this.counter = counter%3;
    }
}
