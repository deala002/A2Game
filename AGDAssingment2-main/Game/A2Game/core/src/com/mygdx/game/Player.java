package com.mygdx.game;

import static com.mygdx.game.MyGdxGame.MOVEMENT_SPEED;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    private final int MAX_HEALTH = 100;
    private int currentLevel;
    private int currentHealth;
    private int playerScore;
    private int silverKeys;
    private int goldKeys;

    float dt;

    private boolean dead;



    public Player(Animation<TextureRegion> animation) {
        super(animation);

        /*this.texture = new Texture(texturePath);
        this.sprite = new Sprite(this.texture);*/
        delta = new Vector2();
        deltaRectangle = new Rectangle(32, 32, 16, 16);

        currentLevel = 0;

        this.currentHealth = 100;
        this.dead = false;

        this.playerScore = 0;
    }

    public void checkCollision(Level level) {
        for (int i = 0; i < level.interactables.size(); i++) {
            if (level.interactables.get(i).checkCollision(this, level)) {
                level.deleteInteractable(i);
            }
        }
    }

    public float getX() {
        return deltaRectangle.getX();
    }

    public float getY() {
        return deltaRectangle.getY();
    }

    public void setPosition(float x, float y) {
        deltaRectangle.setPosition(x, y);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int num) {
        currentLevel = num;
    }

    public int getCurrentHealth() {
        return this.getCurrentHealth();
    }

    public void setHealth(int health) {
        currentHealth = health;
    }

    public void heal(int _health) {
        this.currentHealth += _health;
        if (currentHealth > MAX_HEALTH) {
            currentHealth = MAX_HEALTH;
        }
    }

    public void damagePlayer(int damage) {
        currentHealth -= damage;
        if (currentHealth <= 0) {
            this.dead = true;
        }
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int score) {
        playerScore = score;
        Gdx.app.log("SCORE", String.valueOf(playerScore));
    }

    public int getSilverKeys() {
        return silverKeys;
    }

    public void setSilverKeys(int silverKeys) {
        this.silverKeys = silverKeys;
        Gdx.app.log("SILVERKEYS", String.valueOf(this.silverKeys));
    }

    public int getGoldKeys() {
        return goldKeys;
    }

    public void setGoldKeys(int goldKeys) {
        this.goldKeys = goldKeys;
        Gdx.app.log("GOLDKEYS", String.valueOf(this.goldKeys));
    }

}