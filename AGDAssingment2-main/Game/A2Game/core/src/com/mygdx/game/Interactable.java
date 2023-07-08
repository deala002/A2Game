package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Interactable {
    // positioning and collision
    int x, y;
    Rectangle boundingBox;

    // textures


    // elapsed time
    float stateTime;

    public Interactable(int x, int y) {
        // converts x,y pos from tmx into workable values
        boundingBox = new Rectangle(x, y-16, 16, 16);

        stateTime = 0f;
    }

    // TODO: might need to return something but idk what
    public abstract boolean checkCollision(Player player, Level level);

    public abstract void draw(SpriteBatch batch);
}
