package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Door extends Interactable {
    Rectangle horizontalBox;
    Rectangle verticalBox;

    public Door(int x, int y) {
        super(x, y);
        horizontalBox = new Rectangle(x-16, y-16, 48, 16);
        verticalBox = new Rectangle(x, y-32, 16, 48);
    }
}

