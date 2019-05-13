package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.boi.*;
import com.mygdx.game.menu.achievements.Achievement;
import com.mygdx.game.menu.store.Store;
import com.mygdx.game.obstacles.*;

import java.util.Scanner;

public class SpiderBoiGame extends ApplicationAdapter implements InputProcessor {

    static Preferences savedState;
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

	@Override
	public void create() {
        savedState = Gdx.app.getPreferences("com.mygdx.game.gameState");
        store = new Store(savedState);
        initializeVisuals();
        initializeSpiderBoi();
        adjustScreen();
		loadLevel();
		loadLabels();
        loadSavedState();
	}

	public void initializeVisuals() {
        batch = new SpriteBatch();
        store.selectBackground(3);
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

	public void loadLevel() {
        gameLevel = new Level(2);
        gameLevel.showLevel(sp);
    }

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
		batch.begin();
		renderBackground();
		for (int i = 0; i < gameLevel.getObstacles().size(); i++)
		{
			gameLevel.getObstacles().get(i).draw(batch);
			gameLevel.getObstacles().get(i).checkCollision(sp);

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
	public void dispose () {
	    saveGame();
		batch.dispose();
		sp.getImage().dispose();
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
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
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

    public static Preferences getSavedState() { return savedState; }

    public SpiderBoi getSp() { return sp; }

    public BitmapFont getStoreFlyBoiLabel() { return storeFlyBoiLabel; }

    public BitmapFont getTotalKnotsLabel() { return totalKnotsLabel; }

    public Store getStore() { return store; }

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

    public void setSavedState(Preferences setTo) { savedState = setTo; }

    public void setSp(SpiderBoi sp) { this.sp = sp; }

    public void setStoreFlyBoiLabel(BitmapFont storeFlyBoiLabel) { this.storeFlyBoiLabel = storeFlyBoiLabel; }

    public void setTotalKnotsLabel(BitmapFont totalKnotsLabel) { this.totalKnotsLabel = totalKnotsLabel; }

    public void setStore(Store store) { this.store = store; }
}