package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Ladder extends Interactable {
    Rectangle boundingBox;

    public Ladder(int x, int y) {
        super(x, y);
        boundingBox = new Rectangle(x, y-16, 16, 16);
    }

    // TODO: implement ladder logic for last level
    public boolean checkCollision(Player player, Level level) {
        if (boundingBox.overlaps(player.getDeltaRectangle())) {
            Gdx.app.log("COLLISION: ", "TRUE");
            if (player.getCurrentLevel() == 0) {
                Gdx.app.log("LEVEL CHECK: ", "TRUE");
                player.setCurrentLevel(1);
                player.setPosition(432, 128);
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }
}
