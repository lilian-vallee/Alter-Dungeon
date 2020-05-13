package com.alterdungeon.game.entities.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPlayer {

    TextureRegion[][] sprite;

    public AnimationPlayer(Texture texture){
        sprite = TextureRegion.split(texture, 32,32);
    }

    public TextureRegion getSprite(int x, int y) {
        return sprite[x][y];
    }
}
