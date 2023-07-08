package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Key extends Interactable {
    Animation<TextureRegion> animation;

    public Key(int x, int y, Animation<TextureRegion> animation) {
        super(x, y);
        this.animation = animation;
    }
}
