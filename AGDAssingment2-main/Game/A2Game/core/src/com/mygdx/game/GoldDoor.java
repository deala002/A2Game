package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GoldDoor extends Door {

    public GoldDoor(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean checkCollision(Player player, Level level) {
        if (horizontalBox.overlaps(player.getDeltaRectangle())
                || verticalBox.overlaps(player.getDeltaRectangle())) {

            if (player.getGoldKeys() > 0) {
                level.tiledMap.getLayers().get("Door4").setVisible(false);
                player.setGoldKeys(player.getGoldKeys() - 1);

                Music music = Gdx.audio.newMusic(Gdx.files.internal("dorm-door-opening-6038.mp3"));
                music.play();
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }
}
