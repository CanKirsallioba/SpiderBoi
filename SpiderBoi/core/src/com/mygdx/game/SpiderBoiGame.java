package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GUI.LevelSelection;
import com.mygdx.game.GUI.MainMenu;
import com.mygdx.game.GUI.StoreScreen;
import com.mygdx.game.boi.*;
import com.mygdx.game.menu.achievements.Achievement;
import com.mygdx.game.menu.store.Store;
import com.mygdx.game.obstacles.*;


/**
 * This is the SpiderBoiGame class, the game is created rendered and disposed in here.
 * @author JavaBoiz
 * @version 13.05.2019
 */
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
	MainMenu mainMenu;
	LevelSelection levelSelection;
	//StoreScreen storeScreen;

	/**
	 * This method sets up the game with it's basic features.
	 */
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
		initialiseMenus();
	}

	/**
	 * This method initialises the menus, int detail the main menu,
	 * and the levelSelection screen.
	 */
	public void initialiseMenus()
	{
		mainMenu = new MainMenu();
		levelSelection = new LevelSelection();
		//storeScreen = new StoreScreen();    --Since we could not have time to implement it it is commented--
	}

	/**
	 * This method initialises the visuals, in detail
	 * batch and the background.
	 */
	public void initializeVisuals() {
		batch = new SpriteBatch();
		background = store.getSelectedSpiderBoiBackground();
	}

	/**
	 * This method intialises the spiderBoi (it's constructor takes
	 * the selected spiderBoiSkin from the store --not implemented--),
	 * initialises the spiderSilk and sets the last direction.
	 */
	public void initializeSpiderBoi() {
		sp = new SpiderBoi(store.getSelectedSpiderBoiSkin());
		silk = new SpiderSilk(sp);
		lastDirection = "n";
	}

	/**
	 * This method sets the touch input.
	 */
	public void adjustScreen() {
		Gdx.input.setInputProcessor(this);
		isTouching = false;
	}

	/*public void loadLevel(int x) {
		gameLevel = new Level(x);
		gameLevel.showLevel(sp);
	}*/

	/**
	 * This method prints the labels on the screen.
	 */
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

	/**
	 * This method saves the current state of the game
	 * such as the total flyBois or totalknots.
	 */
	public void loadSavedState() {
		savedState.putInteger("storeFlyBoi", 0);
		savedState.putInteger("totalKnots", 0);
		savedState.putString("unlockedSkins", "0");
		savedState.putString("unlockedBackgrounds", "0");


		store.setTotalFlyBoi(savedState.getInteger("storeFlyBoi"));
		Achievement.setTotalKnots(savedState.getInteger("totalKnots"));
	}

	/**
	 * This method draws the background and fits it to the screen.
	 */
	public void renderBackground() {
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/**
	 * This method helps the menus to draw themselves, when pressed on certain buttons.
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(gameState == 1)
			mainMenu.draw(batch);
		else if(gameState == 2) //goes into level selection screen
			levelSelection.draw(batch);
		//else if (gameState == 3)
		//	storeScreen.draw(batch);
		else if (gameState == 6) //goes into first level
			playGame(1);
		else if (gameState == 7)//goes into second level
			playGame(2);

	}

	/**
	 * This method disposes of the batch and all the components
	 * in the screen.
	 */
	@Override
	public void dispose () {
		saveGame();
		batch.dispose();
		sp.getImage().dispose();
	}

	/**
	 * This method has the logic of the game. It holds the collision checkers,
	 * knot checkers, spiderBoi, and draws the silk. It also renders the background. Also holds the
	 * ending state of the game (winObstacle).
	 * @param level is the level number that takes their data from theit .txt files.
	 */
	public void playGame(int level)
	{
		batch.begin();
		renderBackground();

		//draws each obstacle to the screen and checks the collision of spiderboi and obstacles
		for (int i = 0; i < gameLevel.obstacles.size(); i++)
		{
			gameLevel.obstacles.get(i).draw(batch);
			if(gameLevel.obstacles.get(i) instanceof WinObstacle)
			{
				if (gameLevel.getObstacles().get(i).getBoundary().overlaps(sp.getBoundary()))
					sp.setVelocity(Vector2.Zero);
			}
			else
			gameLevel.obstacles.get(i).checkCollision(sp);

		}

		//draws present collectable bois and checks for interaction
		for (int i = 0; i < gameLevel.collectableBois.size(); i++)
		{
			if (gameLevel.collectableBois.get(i).isPresent()) {
				gameLevel.collectableBois.get(i).draw(batch);
				gameLevel.collectableBois.get(i).performInteraction(this);
			}
		}

		//if a knot is formed, increments knot number by one
		if (silk.checkKnot()) {
			silk.addKnot();
			Achievement.incrementTotalKnots();
		}


		sp.draw(batch);

		//updates game information labels
		knotLabel.draw(batch, "Knots: " + silk.getKnotCount(), 50, Gdx.graphics.getHeight() - 50);
		storeFlyBoiLabel.draw(batch, "StoreFlyBois: " + store.getTotalFlyBoi(), 50, 75);
		totalKnotsLabel.draw(batch, "Total Knots: " + Achievement.getTotalKnots(), Gdx.graphics.getWidth() - 300, 75);

		batch.end();


		silk.drawSilk();

		sp.move();

		if(isGameOver())
		{
            initializeSpiderBoi();
			gameState = 2;
		}
	}


	/**
	 *????????????????????????????????????????????????????????????????????????????????????????????????
	 */
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

	/**
	 * Unused
	 * @param keycode unused
	 * @return unused
	 */
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	/**
	 * Unused
	 * @param keycode unused
	 * @return unused
	 */
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * Unused
	 * @param character unused
	 * @return unused
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * This method helps set the changes between menus (game state is the menus' names. main menu = 1 and leveselection = 2)
	 * @param screenX is the x coordinate of the pressed location.
	 * @param screenY is the y coordinate of the pressed location.
	 * @param pointer unused
	 * @param button unused
	 * @return true when the method is called.
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		lastTouch = new Vector2(screenX, screenY);
		if(gameState == 1)
		mainMenu.touchDown(screenX, screenY);
		if(gameState == 2)
			levelSelection.touchDown(screenX, screenY);
		return true;
	}

	/**
	 * This method helps set the changes between menus (game state is the menus' names. main menu = 1 and leveselection = 2)
	 * @param screenX is the x coordinate of the pressed location.
	 * @param screenY is the y coordinate of the pressed location.
	 * @param pointer unused
	 * @param button unused
	 * @return true when the method is called.
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 lastTouchInv = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
		mainMenu.resetAll();
		if (gameState == 1 && mainMenu.playButtonPressed(lastTouchInv))
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
		else if (gameState == 2 && levelSelection.level1ButtonPressed(lastTouchInv)) {
			gameState = 6;
			gameLevel = new Level(1);
			gameLevel.showLevel(sp);
		}
		else if (gameState == 2 && levelSelection.level2ButtonPressed(lastTouchInv)) {
			gameState = 7;
			gameLevel = new Level(2);
			gameLevel.showLevel(sp);
		}
		else if (gameState == 2 && levelSelection.backButtonPressed(lastTouchInv))
		    gameState = 1;
		else if (gameState == 1 && mainMenu.exitButtonPressed(lastTouchInv))
			System.exit(1);
		return true;
	}

	/**
	 * This method takes the touch inputs about when the user
	 * swipes right, left, up or down. It sets teh direction
	 * and calls the move method.
	 * @param screenX is the x coordinate of the pressed location.
	 * @param screenY is the y coordinate of the pressed location.
	 * @param pointer unused
	 * @return true when the method is called.
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(gameState == 6 || gameState == 7) {
			Vector2 newTouch = new Vector2(screenX, screenY);
			if (newTouch.cpy().sub(lastTouch).len() < SWIPE_THRESHOLD)
				return false;
			float angle = newTouch.cpy().sub(lastTouch).angle();
			if ((angle < 45 || angle > 315) && sp.getVelocity().isZero() && !lastDirection.equals("r") && !lastDirection.equals("l")) {
				sp.moveRight();
				lastDirection = "r";
			}
			if ((angle > 45 && angle < 135) && sp.getVelocity().isZero() && !lastDirection.equals("u") && !lastDirection.equals("d")) {
				sp.moveDown();
				lastDirection = "d";
			}
			if ((angle > 135 && angle < 225) && sp.getVelocity().isZero() && !lastDirection.equals("r") && !lastDirection.equals("l")) {
				sp.moveLeft();
				lastDirection = "l";
			}
			if ((angle > 225 && angle < 315) && sp.getVelocity().isZero() && !lastDirection.equals("u") && !lastDirection.equals("d")) {
				sp.moveUp();
				lastDirection = "u";
			}
			if (sp.getVelocity().isZero())
				sp.move();
		}
		return true;
	}

	/**
	 * Unused
	 * @param screenX unused
	 * @param screenY unused
	 * @return unused
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) { return false; }

	/**
	 * unused
	 * @param amount unused
	 * @return unused
	 */
	@Override
	public boolean scrolled(int amount) { return false; }

	/**
	 * If the knots made by the player exceeds the max allowed knots hte game ends
	 * and the game returns the player to the level selection screen.
	 * @return a boolean value checking if the game is over or not.
	 */
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

		for (int index = 0; index < gameLevel.getObstacles().size(); index++) {
			if (gameLevel.getObstacles().get(index) instanceof WinObstacle) {
				if (gameLevel.getObstacles().get(index).getBoundary().overlaps(sp.getBoundary())) {
					if (sp.getStopLocations().size() > 1)
						return true;
				}
			}

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