package com.mygdx.game;

import static jdk.internal.org.jline.utils.Colors.h;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.LinkedList;

public class MyGdxGame extends ApplicationAdapter {
	//movement speed
	public static final float MOVEMENT_SPEED = 200.0f;

	public static final float MOVEMENT_COOLDOWN_TIME = 0.1f;

	// camera and render
	SpriteBatch batch;
	//UI spritebatch
	SpriteBatch uiBatch;

	OrthographicCamera camera;

	// tilemap levels
	Level[] levels;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	TiledMap currentLevel;

	// Collision rectangle for the wall layer of the tilemap
	Rectangle tileRectangle;
	Rectangle playerDeltaRectangle;

	//

	//Player instantiation
	Player player;

	//DT
	float dt;

	int characterX;
	int characterY;
	float movementCooldown;

	//Texture for buttons
	Texture buttonTexture;

	//4 way directional movement buttons
	Button moveLeftButton;
	Button moveRightButton;
	Button moveDownButton;
	Button moveUpButton;

	// texture atlas and animations
	TextureAtlas charactersAtlas;
	TextureAtlas interactablesAtlas;
	Animation<TextureRegion> playerAnim;
	Animation<TextureRegion> enemyAnim; // separate into different enemies
	Animation<TextureRegion> coinAnim;
	Animation<TextureRegion> silverKeyAnim;
	Animation<TextureRegion> goldKeyAnim;
	Animation<TextureRegion> potionAnim;
	
	@Override
	public void create () {
		dt = Gdx.graphics.getDeltaTime();
		// set up camera
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		//playerData
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 272);

		buttonTexture = new Texture("buttonSquare_blue.png");

		// set up stage
		batch = new SpriteBatch();

		//spriteBatch for UI elements
		uiBatch = new SpriteBatch();



		// set up assets
		charactersAtlas = new TextureAtlas("characters.atlas");
		interactablesAtlas = new TextureAtlas("interactables.atlas");

		playerAnim = new Animation<TextureRegion>(0.15f, charactersAtlas.findRegions("priest3_v1"), Animation.PlayMode.LOOP);
		// TODO: add enemy/enemies animation here
		coinAnim = new Animation<TextureRegion>(0.2f, interactablesAtlas.findRegions("coin"), Animation.PlayMode.LOOP);
		silverKeyAnim = new Animation<TextureRegion>(0.2f, interactablesAtlas.findRegions("keys_2"), Animation.PlayMode.LOOP);
		goldKeyAnim = new Animation<TextureRegion>(0.2f, interactablesAtlas.findRegions("keys_1"), Animation.PlayMode.LOOP);
		potionAnim =  new Animation<TextureRegion>(0.2f, interactablesAtlas.findRegions("flasks_1"), Animation.PlayMode.LOOP);

		//Buttons
		float buttonSize = h * 0.2f;
		moveLeftButton = new Button(0.0f, buttonSize, 100, 100, buttonTexture, buttonTexture);
		moveRightButton = new Button(buttonSize*2, buttonSize, 100, 100, buttonTexture, buttonTexture);
		moveDownButton = new Button(buttonSize, 0.0f, 100, 100, buttonTexture, buttonTexture);
		moveUpButton = new Button(buttonSize, buttonSize*2, 100, 100, buttonTexture, buttonTexture);

		//Player Sprite defined
		/*player = new Player("player.png");
		playerSprite = player.sprite;
		playerDelta = player.delta;
		playerDeltaRectangle = player.deltaRectangle;

		player.sprite.setX(150);
		player.sprite.setY(800);*/

		player = new Player(playerAnim);


		// set up levels and map renderer
		// TODO: currentLevel is hard coded to be level 1 for testing
		levels = new Level[2];
		levels[0] = new Level(1, coinAnim, silverKeyAnim, goldKeyAnim, potionAnim);
		levels[1] = new Level(2, coinAnim, silverKeyAnim, goldKeyAnim, potionAnim);
		currentLevel = levels[0].tiledMap;

		tiledMapRenderer = new LayerRenderer(currentLevel);
		dt = 0.0f;

		newGame();
	}

	private void newGame() {
		dt = 0.0f;

		characterX = 2;
		characterY = 3;
		movementCooldown = 0.0f;

		player.setHealth(100);
		player.setPlayerScore(0);
	}

	public void update () {
		dt += Gdx.graphics.getDeltaTime();
		movementCooldown += Gdx.graphics.getDeltaTime();

		currentLevel = levels[player.getCurrentLevel()].tiledMap;

		characterX = (int) player.getX() / 16;
		characterY = (int) player.getY() / 16;

		//Touch Input info
		boolean checkTouch = Gdx.input.isTouched();
		int touchX = Gdx.input.getX();
		int touchY = Gdx.input.getY();

		moveLeftButton.update(checkTouch, touchX, touchY);
		moveRightButton.update(checkTouch, touchX, touchY);
		moveDownButton.update(checkTouch, touchX, touchY);
		moveUpButton.update(checkTouch, touchX, touchY);

		int moveX = 0;
		int moveY = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || moveLeftButton.isDown) {
			moveLeftButton.isDown = true;
			moveX -= 1;
			if (!player.getFlip()) {
				player.setFlip(true);
			}
			moveLeftButton.isDown = false;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) || moveRightButton.isDown) {
			moveRightButton.isDown = true;
			moveX += 1;
			if (player.getFlip()) {
				player.setFlip(false);
			}
			moveRightButton.isDown = false;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) || moveDownButton.isDown) {
			moveDownButton.isDown = true;
			moveY -= 1;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) || moveUpButton.isDown) {
			moveUpButton.isDown = true;
			moveY += 1;
			Gdx.app.log("MOVING: ", "TRUE");
		}

/*		playerDelta.x = moveX * MOVEMENT_SPEED * dt;
		playerDelta.y = moveY * MOVEMENT_SPEED * dt;*/

		//Gets the collision layer we need and creates a second variable that is a TiledMapTiledLayer which is the previous variable casted to said
		//data type

		if (movementCooldown >= MOVEMENT_COOLDOWN_TIME) {

			MapLayer collisionLayer = currentLevel.getLayers().get("Walls");
			TiledMapTileLayer tileLayer = (TiledMapTileLayer) collisionLayer;

			TiledMapTileLayer doorOne = (TiledMapTileLayer) currentLevel.getLayers().get("Door1");
			TiledMapTileLayer.Cell doorOneCell = null;
			TiledMapTileLayer doorTwo = (TiledMapTileLayer) currentLevel.getLayers().get("Door2");
			TiledMapTileLayer.Cell doorTwoCell = null;
			TiledMapTileLayer doorThree = (TiledMapTileLayer) currentLevel.getLayers().get("Door3");
			TiledMapTileLayer.Cell doorThreeCell = null;
			TiledMapTileLayer doorFour = (TiledMapTileLayer) currentLevel.getLayers().get("Door4");
			TiledMapTileLayer.Cell doorFourCell = null;

			if ((moveX != 0 || moveY != 0)
					&& moveX + characterX >= 0 && moveX + characterX < tileLayer.getWidth()
					&& moveY + characterY >= 0 && moveY + characterY < tileLayer.getHeight()
			) {
				TiledMapTileLayer.Cell targetCell = tileLayer.getCell(characterX + moveX, characterY + moveY);

				if (doorOne.isVisible()) {
					doorOneCell = doorOne.getCell(characterX + moveX, characterY + moveY);
				}
				if (doorTwo.isVisible()) {
					doorTwoCell = doorTwo.getCell(characterX + moveX, characterY + moveY);
				}
				if (doorThree.isVisible()) {
					doorThreeCell = doorThree.getCell(characterX + moveX, characterY + moveY);
				}
				if (doorFour.isVisible()) {
					doorFourCell = doorFour.getCell(characterX + moveX, characterY + moveY);
				}

				if (targetCell == null
				&& doorOneCell == null && doorTwoCell == null
				&& doorThreeCell == null && doorFourCell == null
				) {
					characterX += moveX;
					characterY += moveY;
					float x = player.deltaRectangle.getX() + (moveX * 16);
					float y = player.deltaRectangle.getY() + (moveY * 16);
					player.deltaRectangle.setX(x);
					player.deltaRectangle.setY(y);

					//Gdx.app.log("X: ", String.valueOf(x));
					//Gdx.app.log("Y: ", String.valueOf(y));

					movementCooldown = 0.0f;
				}
			}
		}

		player.checkCollision(levels[player.getCurrentLevel()]);

		//Finds the top right and bottom left corner of the player sprite
/*		int right = (int) Math.ceil(Math.max(playerSprite.getX() + playerSprite.getWidth(),playerSprite.getX() + playerSprite.getWidth() + playerDelta.x));
		int top = (int) Math.ceil(Math.max(playerSprite.getY() + playerSprite.getHeight(),playerSprite.getY() + playerSprite.getHeight() + playerDelta.y));


		int left = (int) Math.floor(Math.min(playerSprite.getX(),playerSprite.getX() + playerDelta.x));
		int bottom = (int) Math.floor(Math.min(playerSprite.getY(),playerSprite.getY() + playerDelta.y));

		// Divide bounds by tile sizes to retrieve tile indices
		right /= tileLayer.getTileWidth();
		top /= tileLayer.getTileHeight();
		left /= tileLayer.getTileWidth();
		bottom /= tileLayer.getTileHeight();

		//Loops through the relevant squares and prevents movement into them

		for(int y = bottom; y <= top; y++) {
			for (int x = left; x <= right; x++) {
				TiledMapTileLayer.Cell targetCell = tileLayer.getCell(x, y);

				//Ignores cell if empty
				if (targetCell == null) continue;

				//Tests against the relevant squares
				tileRectangle.x = x * tileLayer.getTileWidth();
				tileRectangle.y = y * tileLayer.getHeight();

				playerDeltaRectangle.x = playerSprite.getX() + playerDelta.x;
				playerDeltaRectangle.y = playerSprite.getY();
				if (tileRectangle.overlaps(playerDeltaRectangle)) {
					System.out.println("Something");
					playerDelta.x = 0;
				}

				playerDeltaRectangle.x = playerSprite.getX();
				playerDeltaRectangle.x = playerSprite.getY();
				if (tileRectangle.overlaps(playerDeltaRectangle)) playerDelta.y = 0;
			}
		}
			playerSprite.translate(playerDelta.x, playerDelta.y);*/

	}

	@Override
	public void render () {

		update();

		ScreenUtils.clear(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Renders player character
		//this.batch.begin();
		//this.player.render(this.batch);
		//this.batch.end();


		// clear screen before drawing
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// render map
		camera.update();
		tiledMapRenderer.setView(camera);

		for (MapLayer l : currentLevel.getLayers()) {
			if (! l.isVisible() || ! (l instanceof TiledMapTileLayer)) {
				continue;
			}
			tiledMapRenderer.renderTileLayer((TiledMapTileLayer) l);
		}

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		levels[player.getCurrentLevel()].draw(batch);
		//moveLeftButton.draw(batch);
		player.draw(batch);
		batch.end();


		uiBatch.begin();

		moveLeftButton.draw(uiBatch);

		moveRightButton.draw(uiBatch);

		moveUpButton.draw(uiBatch);

		moveDownButton.draw(uiBatch);

		uiBatch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Level l : levels) {
			l.tiledMap.dispose();
		}
		buttonTexture.dispose();
	}

	//@Override


}
