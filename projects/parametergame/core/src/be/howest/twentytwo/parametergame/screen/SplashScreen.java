package be.howest.twentytwo.parametergame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.ScreenContext;

public class SplashScreen extends BaseScreen {

	/** Time to show splash screen (in seconds) */
	private float splashTime;

	private Texture[] logos;
	
	public SplashScreen(ScreenContext context, float splashTime) {
		super(context);
		this.splashTime = splashTime;
	}
	
	public SplashScreen(ScreenContext context) {
		this(context, 1.5f);
	}

	@Override
	public void show() {
		getContext().getAssetManager().load("logo/box2d_logo.png", Texture.class);
		getContext().getAssetManager().load("logo/howest_logo.jpg", Texture.class);
		getContext().getAssetManager().load("logo/libgdx_logo.png", Texture.class);
		getContext().getAssetManager().load(ParameterGame.UI_SKIN, Skin.class);
		getContext().getAssetManager().finishLoading();
		logos = new Texture[3];
		int idx = 0;
		logos[idx++] = getContext().getAssetManager().get("logo/box2d_logo.png", Texture.class);
		logos[idx++] = getContext().getAssetManager().get("logo/howest_logo.jpg", Texture.class);
		logos[idx++] = getContext().getAssetManager().get("logo/libgdx_logo.png", Texture.class);
	}

	@Override
	public void render(float delta) {
		System.out.println(splashTime);
		if(splashTime < 0f) {
			getContext().setScreen(new MenuScreen(getContext()));
			return;
		}
		SpriteBatch batch = getContext().getSpriteBatch();
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		int maxWidth = width / 3;
		int nextHeight = height / 6;

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Texture logo : logos) {
			int widthPad = maxWidth;
			widthPad = (widthPad - Math.min(logo.getWidth(), maxWidth)) / 2;
			batch.draw(logo, width / 3 + widthPad, nextHeight, Math.min(logo.getWidth(), maxWidth),
					Math.min(logo.getHeight(), height / 6));
			nextHeight += height / 4;
		}
		batch.end();
		splashTime -= delta;
	}

	@Override
	public void resize(int width, int height) {

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

	}

}
