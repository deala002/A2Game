package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;

public class SilverDoor extends Door {

    int doorNum;

    public SilverDoor(int x, int y, int num) {
        super(x, y);
        doorNum = num;
    }

    @Override
    public boolean checkCollision(Player player, Level level) {
        if (horizontalBox.overlaps(player.getDeltaRectangle())
        || verticalBox.overlaps(player.getDeltaRectangle())) {

            if (player.getSilverKeys() > 0) {
                //Gdx.app.log("COLLISION: ", "TRUE");
                if (level.tiledMap.getLayers().get("Door1").isVisible() && doorNum == 1) {
                    level.tiledMap.getLayers().get("Door1").setVisible(false);
                } else if (level.tiledMap.getLayers().get("Door2").isVisible() && doorNum == 2) {
                    level.tiledMap.getLayers().get("Door2").setVisible(false);
                } else if (level.tiledMap.getLayers().get("Door3").isVisible() && doorNum == 3) {
                    level.tiledMap.getLayers().get("Door3").setVisible(false);
                }
                player.setSilverKeys(player.getSilverKeys() - 1);

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
