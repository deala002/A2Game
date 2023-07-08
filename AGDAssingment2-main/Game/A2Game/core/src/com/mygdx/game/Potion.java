package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Potion extends Interactable {

    Animation<TextureRegion> animation;
    private boolean consumed;

    public Potion(int x, int y, Animation<TextureRegion> animation) {
        super(x, y);
        this.animation = animation;
    }


    @Override
    public boolean checkCollision(Player player, Level level) {
        if (this.boundingBox.overlaps(player.getDeltaRectangle())) {
            player.heal(15);
            Music music = Gdx.audio.newMusic(Gdx.files.internal("bottle-open-14895.mp3"));
            music.play();
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
