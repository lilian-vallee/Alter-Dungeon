package com.alterdungeon.game.screen;

import com.alterdungeon.game.AlterDungeonGame;
import com.alterdungeon.game.entities.Player;
import com.alterdungeon.game.maps.ExplorationMap;
import com.alterdungeon.game.scenes.ExplorationHud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Exploration class
 * ecran de jeu pour la phase d'exploration.
 *
 * //disclamer Alert\\
 * Le code qui vas suivre est considéré par le developpeur comme un véritable plat de bolognaise (architecture spaghettie) ... et il le regrette.
 */
public class Exploration implements Screen {

    public AlterDungeonGame game;

    public ExplorationMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    public OrthographicCamera camera;
    private Viewport viewport;
    ExplorationHud hud;

    public Player player;

    public Exploration(AlterDungeonGame game){
        this.game = game;
        player = game.player;

        camera = new OrthographicCamera();

        viewport = new FitViewport(game.V_WITDH,game.V_HEIGHT,camera);
        hud = new ExplorationHud(this);

        map = new ExplorationMap();
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        player.setScreenLevel(this);
        camera.position.set(player.getPosition().x , player.getPosition().y, 0);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(hud.stage);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        mapRenderer.setView(camera);

        int[] background = {0,1};
        int[] frontground = {2};
        //on fait un rendu du background puis du player puis le forground
        mapRenderer.render(background);

        game.spriteBatch.begin();
        game.spriteBatch.draw(player.getAnimation(), game.V_WITDH/2 , game.V_HEIGHT/2 );
        game.spriteBatch.end();

        mapRenderer.render(frontground);
        hud.stage.draw();
    }

    private void update(float deltaTime) {
        camera.update();
        hud.update();
        player.update(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }
}
