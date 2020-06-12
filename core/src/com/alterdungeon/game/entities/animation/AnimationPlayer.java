package com.alterdungeon.game.entities.animation;

import com.alterdungeon.game.entities.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public class AnimationPlayer{


    private Player player;
    private Texture texture;//texture du personnage
    private TextureRegion animation;//texture à animer

    public TextureRegion getAnimation() {
        return animation;
    }

    private enum State {
        GOUP, // etat lors du déplacement vert le haut
        STANDINGUP, // etat à l'arret apres s'être déplacé vers le haut
        GORIGHT, // etat lors du déplacement vert la droite
        STANDINGRIGHT, // etat à l'arret apres s'être déplacé vers la droite
        GODOWN, // etat lors du déplacement vert le bas
        STANDINGDOWN, // etat à l'arret apres s'être déplacé vers le bas
        GOLEFT, // etat lors du déplacement vert la gauche
        STANDINGLEFT // etat à l'arret apres s'être déplacé vers la gauche
    };

    private State currentState;
    private State previousState;

    // différente animation et texture en fonction de l'état
    private Animation goUp;
    private Animation goRight;
    private Animation goDown;
    private Animation goLeft;
    private TextureRegion standingDown;
    private TextureRegion standingLeft;
    private TextureRegion standingRight;
    private TextureRegion standingUp;

    private float timer;// timer pour changer les frames d'une animation


    public AnimationPlayer(Player player){

        texture = new Texture("entities/playerSprite.png");
        this.player = player;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        //définition des differentes animation de mouvement
        for (int i = 0 ; i < 3 ; i++){
            frames.add(new TextureRegion(texture, i *32, 0, 32,32));
        }
        goDown = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0 ; i < 3 ; i++){
            frames.add(new TextureRegion(texture, i *32, 1*32, 32,32));
        }
        goLeft = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0 ; i < 3 ; i++){
            frames.add(new TextureRegion(texture, i *32, 2*32, 32,32));
        }
        goRight = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0 ; i < 3 ; i++){
            frames.add(new TextureRegion(texture, i *32, 3*32, 32,32));
        }
        goUp = new Animation(0.1f, frames);
        frames.clear();

        //définition des textures a l'arret du personnage
        standingDown = new TextureRegion(texture, 1*32, 0, 32, 32);
        standingLeft = new TextureRegion(texture, 1*32, 1*32, 32, 32);
        standingRight = new TextureRegion(texture, 1*32, 2*32, 32, 32);
        standingUp = new TextureRegion(texture, 1*32, 3*32, 32, 32);

        previousState = State.STANDINGDOWN;
        currentState = State.STANDINGDOWN;
        animation = standingDown;
    }

    public void update(float delta){
        animation = getFrame(delta);
    }

    /**
     * Récupère la frame à animer en fonction de l'etat
     * @param delta
     * @return
     */
    private TextureRegion getFrame(float delta) {

        TextureRegion frame;
        switch (currentState){
            case GODOWN :
                frame = (TextureRegion) goDown.getKeyFrame(timer, true);
                break;
            case GOLEFT:
                frame = (TextureRegion) goLeft.getKeyFrame(timer, true);
                break;
            case GORIGHT:
                frame = (TextureRegion) goRight.getKeyFrame(timer, true);
                break;
            case GOUP:
                frame = (TextureRegion) goUp.getKeyFrame(timer, true);
                break;
            case STANDINGDOWN:
                frame = standingDown;
                break;
            case STANDINGLEFT:
                frame = standingLeft;
                break;
            case STANDINGRIGHT:
                frame = standingRight;
                break;
            case STANDINGUP:
                frame = standingUp;
                break;
            default:
                frame = standingDown;
                break;
        }
        //System.out.println(currentState.toString());

        timer = currentState == previousState ? timer + delta : 0;
        previousState = currentState;
        return frame;
    }

    /**
     * Change l'etat en fonction du déplacement du personnage
     * @param direction
     */
    public void animateMove(int direction) {
        switch (direction){
            case 0 :
                currentState = State.GODOWN;
                break;
            case 1 :
                currentState = State.GOLEFT;
                break;
            case 2 :
                currentState = State.GORIGHT;
                break;
            case 3 :
                currentState = State.GOUP;
                break;
            default :
                currentState = State.STANDINGDOWN;
                break;
        }
    }

    /**
     * Change l'etat lors de l'arret du personnage en fonction de l'etat precedent
     */
    public void animateStanding() {
        switch (currentState){
            case GODOWN:
                currentState = State.STANDINGDOWN;
                break;
            case GOLEFT:
                currentState = State.STANDINGLEFT;
                break;
            case GORIGHT :
                currentState = State.STANDINGRIGHT;
                break;
            case GOUP :
                currentState = State.STANDINGUP;
                break;
            default :
                currentState = previousState;
                break;
        }
    }
}
