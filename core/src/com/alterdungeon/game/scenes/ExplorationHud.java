package com.alterdungeon.game.scenes;

import com.alterdungeon.game.AlterDungeonGame;
import com.alterdungeon.game.entities.Player;
import com.alterdungeon.game.screen.Exploration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ExplorationHud {

    private final Exploration screen;

    public Stage stage;
    private Viewport viewport;

    ImageButton playerMenu; // bouton memu du joueur
    Table movingTable;
    ImageButton moveUp;
    ImageButton moveDown;
    ImageButton moveRight;
    ImageButton moveLeft;
    ImageButton InteractionButton;
    private boolean touchDown = false;
    private int touchDirection = -1;

    public ExplorationHud(Exploration screen) {
        this.screen = screen;

        viewport = new StretchViewport(AlterDungeonGame.V_WITDH, AlterDungeonGame.V_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, screen.game.spriteBatch);

        setMenuButton();
        setMovingButton();
    }

    public void update(){
        screen.player.movePlayer(touchDirection);
    }

    /**
     * Implémentation des boutons de déplacement du joueur sur l'ecran de jeu
     */
    private void setMovingButton() {
        //Bouton de deplacement
        movingTable = new Table();
        movingTable.setPosition(100,75);

        //init du boutton deplacement haut
        moveUp = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("ui/direction/upButton.png"))));
        moveUp.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("upButton");
                touchDirection = 3;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touchDirection = -1;
            }
        });

        //init du boutton deplacement bas
        moveDown = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("ui/direction/downButton.png"))));
        moveDown.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("downButton");
                touchDirection = 0;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touchDirection = -1;
            }
        });
        //init du boutton deplacement droite
        moveRight = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("ui/direction/rightButton.png"))));
        moveRight.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("rightButton");
                touchDirection = 2;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touchDirection = -1;
            }
        });
        //init du boutton deplacement gauche
        moveLeft = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("ui/direction/leftButton.png"))));
        moveLeft.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("leftButton");
                touchDirection = 1;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touchDirection = -1;
            }
        });

        movingTable.add(moveUp).colspan(3).expandX();
        movingTable.row();
        movingTable.add(moveLeft);
        movingTable.add(moveDown);
        movingTable.add(moveRight);
        stage.addActor(movingTable);
    }

    /**
     * Implementation du bouton memu sur l'écran de jeu.
     */
    private void setMenuButton() {
        //bouton menu du joueur
        playerMenu = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("ui/playerMenu.png"))));//texture
        playerMenu.setSize(50,50);
        playerMenu.setPosition(0, AlterDungeonGame.V_HEIGHT-playerMenu.getHeight());

        playerMenu.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("bouton playerMenu");
                return true;
            }
        });

        stage.addActor(playerMenu);//ajout a l'ecran
    }


}
