package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    Animation<TextureRegion> animation;
    Texture texture;
    Rectangle deltaRectangle;
    Vector2 delta;
    Sprite sprite;

    float x;
    float y;

    float dt;

    private boolean flip;

    public Entity(Animation<TextureRegion> animation) {
        this.animation = animation;

        dt = 0f;

        flip = false;
    }

    public Vector2 getPosition() {
        float currentX = this.x + (this.texture.getWidth()  / 2.0f);
        float currentY = this.y + (this.texture.getHeight() / 2.0f);
        //playerSprite = new Sprite(texture);
        return new Vector2(currentX, currentY);
    }

    public Rectangle getDeltaRectangle() {
        return deltaRectangle;
    }

    public Vector2 getDelta() {
        return delta;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean getFlip() {
        return this.flip;
    }
    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public void draw(SpriteBatch batch) {
        dt += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = animation.getKeyFrame(dt, true);
        batch.draw(currentFrame,
                flip ? deltaRectangle.getX()+deltaRectangle.getWidth() : deltaRectangle.getX(),
                deltaRectangle.getY(),
                flip ? -deltaRectangle.getWidth() : deltaRectangle.getWidth(),
                deltaRectangle.getHeight());
    }

    public void dispose() {
        this.texture.dispose();
    }
}