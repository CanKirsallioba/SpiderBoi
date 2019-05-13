package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GUI.LevelSelection;
import com.mygdx.game.GUI.MainMenu;
import com.mygdx.game.GUI.StoreScreen;
import com.mygdx.game.boi.*;
import com.mygdx.game.menu.achievements.Achievement;
import com.mygdx.game.menu.store.Store;
import com.mygdx.game.obstacles.*;

import java.util.Scanner;

public class SpiderBoiGame extends ApplicationAdapter implements InputProcessor {

	Preferences savedState;
	static final float SWIPE_THRESHOLD = 80;
	SpriteBatch batch;
	SpiderBoi sp;
	private Vector2 lastTouch;
	PlainObstacle plObs;
	boolean isTouching;
	SpiderSilk silk;
	Level gameLevel;
	BitmapFont knotLabel;
	BitmapFont storeFlyBoiLabel;
	BitmapFont totalKnotsLabel;
	String lastDirection;
	Texture background;
	Store store;
	int gameState;
	Texture playButton, storeButton, achievementsButton, exitButton;
	Rectangle playRect, storeRect, achievementsRect, exitRect;
	MainMenu mainMenu;
	LevelSelection levelSelection;
	//StoreScreen storeScreen;

	@Override
	public void create() {
		gameState = 1;
		savedState = Gdx.app.getPreferences("com.mygdx.game.gameState");
		store = new Store(savedState);
		initializeVisuals();
		initializeSpiderBoi();
		adjustScreen();
		loadLabels();
		loadSavedState();
		initialiseButtons();
		initialiseMenus();
	}

	public void initialiseMenus()
	{
		mainMenu = new MainMenu();
		levelSelection = new LevelSelection();
		//storeScreen = new StoreScreen();
	}

	public void initialiseButtons()
	{
		playButton = new Texture("playBut1.png");
		storeButton = new Texture("storeBut1.png");
		achievementsButton = new Texture("achBut1.png");
		exitButton = new Texture("exitBut1.png");
		playRect = new Rectangle(Gdx.graphics.getWidth()/2 - playButton.getWidth()/2, 800, playButton.getWidth(), playButton.getHeight());
		storeRect = new Rectangle(Gdx.graphics.getWidth()/2 - storeButton.getWidth()/2, 600, storeButton.getWidth(), storeButton.getHeight());
		achievementsRect = new Rectangle(Gdx.graphics.getWidth()/2 - achievementsButton.getWidth()/2, 400, achievementsButton.getWidth(), achievementsButton.getHeight());
		exitRect = new Rectangle(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2, 200, exitButton.getWidth(), exitButton.getHeight());
	}

	public void initializeVisuals() {
		batch = new SpriteBatch();
		background = store.getSelectedSpiderBoiBackground();
	}

	public void initializeSpiderBoi() {
		sp = new SpiderBoi(store.getSelectedSpiderBoiSkin());
		silk = new SpiderSilk(sp);
		lastDirection = "n";
	}

	public void adjustScreen() {
		Gdx.input.setInputProcessor(this);
		isTouching = false;
	}

	/*public void loadLevel(int x) {
		gameLevel = new Level(x);
		gameLevel.showLevel(sp);
	}*/

	public void loadLabels() {
		knotLabel = new BitmapFont();
		knotLabel.setUseIntegerPositions(false);
		knotLabel.getData().setScale(3, 3);
		knotLabel.setColor(1f, 1f, 1f, 1f);
		storeFlyBoiLabel = new BitmapFont();
		storeFlyBoiLabel.setUseIntegerPositions(false);
		storeFlyBoiLabel.getData().setScale(3, 3);
		storeFlyBoiLabel.setColor(1f, 1f, 1f, 1f);
		totalKnotsLabel = new BitmapFont();
		totalKnotsLabel.setUseIntegerPositions(false);
		totalKnotsLabel.getData().setScale(3, 3);
		totalKnotsLabel.setColor(1f, 1f, 1f, 1f);
	}



	public void loadSavedState() {
		savedState.putInteger("storeFlyBoi", 0);
		savedState.putInteger("totalKnots", 0);
		savedState.putString("unlockedSkins", "0");
		savedState.putString("unlockedBackgrounds", "0");


		store.setTotalFlyBoi(savedState.getInteger("storeFlyBoi"));
		Achievement.setTotalKnots(savedState.getInteger("totalKnots"));
	}

	public void renderBackground() {
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		if(gameState == 1)
			mainMenu.draw(batch);
		else if(gameState == 2)
			levelSelection.draw(batch);
		//else if (gameState == 3)
		//	storeScreen.draw(batch);
		else if (gameState == 6)
			playGame(1);
		else if (gameState == 7)
			playGame(2);

	}

	@Override
	public void dispose () {
		saveGame();
		batch.dispose();
		sp.getImage().dispose();
	}

	public void playGame(int level)
	{
		gameLevel = new Level(level);
		gameLevel.showLevel(sp);
		batch.begin();
		renderBackground();
		for (int i = 0; i < gameLevel.obstacles.size(); i++)
		{
			gameLevel.obstacles.get(i).draw(batch);
			gameLevel.obstacles.get(i).checkCollision(sp);

		}
		for (int i = 0; i < gameLevel.collectableBois.size(); i++)
		{
			if (gameLevel.collectableBois.get(i).isPresent()) {
				gameLevel.collectableBois.get(i).draw(batch);
				gameLevel.collectableBois.get(i).performInteraction(this);
			}
		}

		Boolean a = silk.checkKnot();
		if (a) {
			silk.addKnot();
			Achievement.incrementTotalKnots();
		}

		if (!isGameOver()) {
			sp.draw(batch);
		}

		knotLabel.draw(batch, "Knots: " + silk.getKnotCount(), 50, Gdx.graphics.getHeight() - 50);
		storeFlyBoiLabel.draw(batch, "StoreFlyBois: " + store.getTotalFlyBoi(), 50, 75);
		totalKnotsLabel.draw(batch, "Total Knots: " + Achievement.getTotalKnots(), Gdx.graphics.getWidth() - 300, 75);

		batch.end();

		if (!isGameOver())
			silk.drawSilk();

		sp.move();

	}


	public void saveGame() {
		savedState.putInteger("storeFlyBoi", store.getTotalFlyBoi());
		savedState.putInteger("totalKnots", Achievement.getTotalKnots());

		for (int index = 0; index < store.getSpiderBoiSkinUnlockedList().size(); index++)
			savedState.putString("unlockedSkins", store.getUnlockedSkinData());

		for (int index = 0; index < store.getSpiderBoiBackgroundUnlockedList().size(); index++)
			savedState.putString("unlockedBackgrounds", store.getUnlockedBackgroundData());

		System.out.println(store.indexOfSelectedBackground());
		System.out.println(store.indexOfSelectedSkin());
		savedState.putInteger("selectedSkin", store.indexOfSelectedSkin());
		savedState.putInteger("selectedBackground", store.indexOfSelectedBackground());

		savedState.flush();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		lastTouch = new Vector2(screenX, screenY);
		mainMenu.touchDown(screenX, screenY);
		levelSelection.touchDown(screenX, screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 lastTouchInv = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
		mainMenu.resetAll();
		if (mainMenu.playButtonPressed(lastTouchInv))
		{
			gameState = 2;
			System.out.println(gameState);
		}

		//else if (mainMenu.storeButtonPressed(lastTouchInv))
			//gameState = 3;
		//else if (mainMenu.aboutButtonPressed(lastTouchInv))
			//gameState = 4;
		//else if (mainMenu.achievementsButtonPressed(lastTouchInv))
			//gameState = 5;
		else if (levelSelection.level1ButtonPressed(lastTouchInv))
			gameState = 6;
		else if (levelSelection.level2ButtonPressed(lastTouchInv))
			gameState = 7;
		else if (mainMenu.exitButtonPressed(lastTouchInv))
			System.exit(1);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 newTouch = new Vector2(screenX, screenY);
		if(newTouch.cpy().sub(lastTouch).len() < SWIPE_THRESHOLD)
			return false;
		float angle = newTouch.cpy().sub(lastTouch).angle();
		if((angle < 45 || angle > 315) && sp.getVelocity().isZero() && !lastDirection.equals("r") && !lastDirection.equals("l")) {
			sp.moveRight();
			lastDirection = "r";
		}
		if((angle > 45 && angle < 135) && sp.getVelocity().isZero() && !lastDirection.equals("u") && !lastDirection.equals("d")) {
			sp.moveDown();
			lastDirection = "d";
		}
		if((angle > 135 && angle < 225) && sp.getVelocity().isZero() && !lastDirection.equals("r") && !lastDirection.equals("l")) {
			sp.moveLeft();
			lastDirection = "l";
		}
		if((angle > 225 && angle < 315) && sp.getVelocity().isZero() && !lastDirection.equals("u") && !lastDirection.equals("d")) {
			sp.moveUp();
			lastDirection = "u";
		}
		if (sp.getVelocity().isZero())
			sp.move();
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) { return false; }

	@Override
	public boolean scrolled(int amount) { return false; }

	public boolean isGameOver() {
		if (gameLevel.getMaxAllowedKnots() < silk.getKnotCount()) {
			return true;
		}

		if (sp.getPosition().x + sp.getImage().getWidth() / 2 > Gdx.graphics.getWidth()
				|| sp.getPosition().x < 0
				|| sp.getPosition().y + sp.getImage().getHeight() / 2 > Gdx.graphics.getHeight()
				|| sp.getPosition().y < 0) {
			sp.getVelocity().setZero();
			return true;
		}

		/*for (int index = 0; index < gameLevel.getObstacles().size(); index++) {
			if (gameLevel.getObstacles().get(index) instanceof WinObstacle) {
				if (gameLevel.getObstacles().get(index).checkCollision(sp)) {
					if (sp.getStopLocations().size() > 1)
						return true;
				}
			}

		}*/

		return false;
	}

	//getters
	public static float getSwipeThreshold() { return SWIPE_THRESHOLD; }

	public SpriteBatch getBatch() { return batch; }

	public SpiderBoi getSpiderBoi() { return sp; }

	public Vector2 getLastTouch() { return lastTouch; }

	public PlainObstacle getPlObs() { return plObs; }

	public boolean isTouching() { return isTouching; }

	public SpiderSilk getSilk() { return silk; }

	public Level getGameLevel() { return gameLevel; }

	public BitmapFont getKnotLabel() { return knotLabel; }

	public String getLastDirection() { return lastDirection; }

	public Texture getBackground() { return background; }

	//setters
	public void setBatch(SpriteBatch batch) { this.batch = batch; }

	public void setSpiderBoi(SpiderBoi sp) { this.sp = sp; }

	public void setLastTouch(Vector2 lastTouch) { this.lastTouch = lastTouch; }

	public void setPlObs(PlainObstacle plObs) { this.plObs = plObs; }

	public void setTouching(boolean touching) { isTouching = touching; }

	public void setSilk(SpiderSilk silk) { this.silk = silk; }

	public void setGameLevel(Level gameLevel) { this.gameLevel = gameLevel; }

	public void setKnotLabel(BitmapFont knotLabel) { this.knotLabel = knotLabel; }

	public void setLastDirection(String lastDirection) { this.lastDirection = lastDirection; }

	public void setBackground(Texture background) { this.background = background; }


}