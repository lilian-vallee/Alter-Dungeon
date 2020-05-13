package com.alterdungeon.game;

import com.alterdungeon.game.entities.Player;
import com.alterdungeon.game.screen.Exploration;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AlterDungeonGame extends Game{

	public static final int V_WITDH =  854;
	public static final int V_HEIGHT =  480;

	public SpriteBatch spriteBatch;

	public Player player;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		player = new Player(this);
		setScreen(new Exploration(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void pause() {
		super.pause();
	}

	public void resume() {
		super.resume();
	}

}
