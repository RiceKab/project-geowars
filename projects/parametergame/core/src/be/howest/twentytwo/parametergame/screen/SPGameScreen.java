package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.model.event.EventQueue;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SPGameScreen extends BaseScreen {

	private World world;
	private PooledEngine engine;
	private Viewport gameViewport, uiViewport; // Needs to be saved for resizes
	private EventQueue eventQueue;

	public SPGameScreen(ScreenContext context, PooledEngine engine, EventQueue eventQueue,
			Viewport vp, Viewport uiViewport) {
		super(context);
		this.engine = engine;
		this.gameViewport = vp;
		this.uiViewport = uiViewport;
		this.eventQueue = eventQueue;
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		engine.update(delta);
		eventQueue.dispatch();
	}

	@Override
	public void resize(int width, int height) {
		gameViewport.update(width, height);
		uiViewport.update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		super.dispose();
		world.dispose();
	}

}
