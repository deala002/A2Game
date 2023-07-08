package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.LinkedList;

public class Level {

    TiledMap tiledMap;
    LinkedList<Interactable> interactables;

    // interactables animations
    Animation<TextureRegion> coinAnim;
    Animation<TextureRegion> silverKeyAnim;
    Animation<TextureRegion> goldKeyAnim;
    Animation<TextureRegion> potionAnim;

    public Level(int number, Animation<TextureRegion> coinAnim, Animation<TextureRegion> silverKeyAnim,
                 Animation<TextureRegion> goldKeyAnim, Animation<TextureRegion> potionAnim) {
        this.coinAnim = coinAnim;
        this.silverKeyAnim = silverKeyAnim;
        this.goldKeyAnim = goldKeyAnim;
        this.potionAnim = potionAnim;

        if (number == 1) {
            createLevelOne();
        }
        else if (number == 2) {
            createLevelTwo();
        }
    }

    public void createLevelOne() {
        tiledMap = new TmxMapLoader().load("dungeon1.tmx");

        interactables = new LinkedList<>();

        // all x, y positions come from the .tmx file
        // with y position = 272 - y
        // create coins
        interactables.add(new Coin(32, 240, coinAnim));
        interactables.add(new Coin(128, 240, coinAnim));
        interactables.add(new Coin(160, 240, coinAnim));
        interactables.add(new Coin(128, 208, coinAnim));
        interactables.add(new Coin(160, 208, coinAnim));

        // create potions
        interactables.add(new Potion(272, 96, potionAnim));

        // create silverKeys
        interactables.add(new SilverKey(48, 240, silverKeyAnim));
        interactables.add(new SilverKey(384, 96, silverKeyAnim));
        interactables.add(new SilverKey(320, 192, silverKeyAnim));

        // create goldKeys
        interactables.add(new GoldKey(144, 224, goldKeyAnim));

        // create silverDoors
        interactables.add(new SilverDoor(80, 144, 1));
        interactables.add(new SilverDoor(208, 144, 2));
        interactables.add(new SilverDoor(208, 224, 3));

        // create goldDoor
        interactables.add(new GoldDoor(432, 144));

        // create Ladder
        interactables.add(new Ladder(448, 144));
    }

    public void createLevelTwo() {
        tiledMap = new TmxMapLoader().load("dungeon2.tmx");

        interactables = new LinkedList<>();

        // create coins
        interactables.add(new Coin(448, 224, coinAnim));
        interactables.add(new Coin(224, 32, coinAnim));
        interactables.add(new Coin(176, 32, coinAnim));
        interactables.add(new Coin(16, 64, coinAnim));
        interactables.add(new Coin(48, 64, coinAnim));
        interactables.add(new Coin(32, 48, coinAnim));
        interactables.add(new Coin(16, 32, coinAnim));
        interactables.add(new Coin(48, 32, coinAnim));

        // create potions
        interactables.add(new Potion(208, 32, potionAnim));

        // create silverKeys
        interactables.add(new SilverKey(448, 240, silverKeyAnim));
        interactables.add(new SilverKey(448, 64, silverKeyAnim));
        interactables.add(new SilverKey(192, 32, silverKeyAnim));

        // create goldKeys
        interactables.add(new GoldKey(96, 240, goldKeyAnim));

        // create silverDoors
        interactables.add(new SilverDoor(352, 176, 1));
        interactables.add(new SilverDoor(144, 192, 2));
        interactables.add(new SilverDoor(32, 96, 3));

        // create goldDoor
        interactables.add(new GoldDoor(32, 192));

        // create ladder
        interactables.add(new Ladder(32, 240));
    }

    public void deleteInteractable(int index) {
        interactables.remove(index);
    }

    public void draw(SpriteBatch batch) {
        for (Interactable i : interactables) {
            i.draw(batch);
        }
    }
}
