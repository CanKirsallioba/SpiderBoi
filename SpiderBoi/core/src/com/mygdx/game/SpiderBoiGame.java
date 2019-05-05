package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class SpiderBoiGame extends ApplicationAdapter  implements InputProcessor {
	SpriteBatch batch;
	SpiderBoi sp;
	private Vector2 lastTouch;
	final float SWIPE_THRESHOLD = 80;
	PlainObstacle plObs;
	boolean isTouching;
	SpiderSilk silk;

	@Override
	public void create () {
		batch = new SpriteBatch();
		sp = new SpiderBoi();
		silk = new SpiderSilk(sp);
		Gdx.input.setInputProcessor(this);
		plObs = new PlainObstacle(200, 200);
		isTouching = false;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(sp.getBoundary().overlaps(plObs.getBoundary()))
		{
			Vector2 reverseVel = sp.getVelocity().scl(-1);
			while(sp.getBoundary().overlaps(plObs.getBoundary())) {
				sp.setVelocity(reverseVel);
				sp.move();
			}
			sp.getVelocity().setZero();

        }

        if (sp.getPosition().x > Gdx.graphics.getWidth() || sp.getPosition().x < 0
		|| sp.getPosition().y > Gdx.graphics.getHeight() || sp.getPosition().y < 0) {
			sp.getVelocity().setZero();
		}

		batch.begin();
		sp.draw(batch);
		silk.addSilkCoords();
		for(int i = 0; i < silk.getSilkCoords().size(); i ++) {
			silk.drawSilk(batch, silk.getSilkCoords().get(i).x, silk.getSilkCoords().get(i).y);
		}
		plObs.draw(batch);
		batch.end();
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
		if(angle < 45 || angle > 315)
			sp.moveRight();
		if(angle > 45 && angle < 135)
			sp.moveDown();
		if(angle > 135 && angle < 225)
			sp.moveLeft();
		if(angle > 225 && angle < 315)
			sp.moveUp();
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
