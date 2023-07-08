package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SilverKey extends Key {

    public SilverKey(int x, int y, Animation<TextureRegion> animation) {
        super(x, y, animation);
    }
    
    @Override
    public boolean checkCollision(Player player, Level level) {
        if (boundingBox.overlaps(player.getDeltaRectangle())) {
            player.setSilverKeys(player.getSilverKeys() + 1);
            return true;
        }
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, boundingBox.getX(), boundingBox.getY(),
                boundingBox.getWidth(), boundingBox.getHeight());
    }
}
