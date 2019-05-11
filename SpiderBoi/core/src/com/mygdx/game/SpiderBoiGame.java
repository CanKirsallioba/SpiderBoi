package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.boi.*;
import com.mygdx.game.obstacles.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.Vector;

public class SpiderBoiGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	SpiderBoi sp;
	private Vector2 lastTouch;
	final float SWIPE_THRESHOLD = 80;
	PlainObstacle plObs;
	boolean isTouching;
	SpiderSilk silk;
	Level gameLevel;
	int totalKnot = 0;
	private Stage stage;
	Label label1;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		sp = new SpiderBoi("SpiderBD.jpeg");
		silk = new SpiderSilk(sp);
		Gdx.input.setInputProcessor(this);
		isTouching = false;
		gameLevel = new Level(2);
		gameLevel.showLevel(sp);
		Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		label1 = new Label("Knots : " + totalKnot + " out of " + "3", labelStyle);
		label1.setPosition(0, Gdx.graphics.getWidth() / 16);
		label1.setAlignment(Align.topLeft);
		stage.addActor(label1);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(104/255f, 252/255f, 255/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*if(sp.getBoundary().overlaps(plObs.getBoundary()))
		{
			Vector2 reverseVel = sp.getVelocity().scl(-1);
			while(sp.getBoundary().overlaps(plObs.getBoundary())) {
				sp.setVelocity(reverseVel);
				sp.move();
			}
			sp.getVelocity().setZero();
			sp.onObstacle = true;

		}

		if (sp.getPosition().x > Gdx.graphics.getWidth() || sp.getPosition().x < 0
				|| sp.getPosition().y > Gdx.graphics.getHeight() || sp.getPosition().y < 0) {
			sp.getVelocity().setZero();
		}*/



		batch.begin();
		for (int i = 0; i < gameLevel.obstacles.size(); i++)
		{
			gameLevel.obstacles.get(i).draw(batch);
			gameLevel.obstacles.get(i).checkCollision(sp);

		}
		for (int i = 0; i < gameLevel.collectableBois.size(); i++)
		{
			if (gameLevel.collectableBois.get(i).isPresent()) {
				gameLevel.collectableBois.get(i).draw(batch);
				gameLevel.collectableBois.get(i).performInteraction(sp);
			}
		}
		sp.draw(batch);
		batch.end();

		silk.drawSilk();
		if (silk.checkKnot() != 0) {
			totalKnot = silk.checkKnot();
		}
		label1.setText("Knots : " + totalKnot + " out of " + "3");

		sp.move();

	}

	@Override
	public void dispose () {
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
		if(newTouch.cpy().sub(lastTouch).len()<SWIPE_THRESHOLD)
			return false;
		float angle = newTouch.cpy().sub(lastTouch).angle();
		if((angle < 45 || angle > 315) && sp.getVelocity().isZero())
			sp.moveRight();
		if((angle > 45 && angle < 135) && sp.getVelocity().isZero())
			sp.moveDown();
		if((angle > 135 && angle < 225) && sp.getVelocity().isZero())
			sp.moveLeft();
		if((angle > 225 && angle < 315) && sp.getVelocity().isZero())
			sp.moveUp();
		if (sp.getVelocity().isZero())
			sp.move();
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}