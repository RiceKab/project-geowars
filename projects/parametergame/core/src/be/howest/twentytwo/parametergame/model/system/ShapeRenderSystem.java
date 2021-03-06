package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.ShapeComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ShapeRenderSystem extends IteratingSystem {

	public final static int PRIORITY = 0;

	private Viewport viewport;
	private ShapeRenderer shapes;

	public ShapeRenderSystem(ShapeRenderer batch, Viewport viewport) {
		super(Family.all(TransformComponent.class, ShapeComponent.class).get(), PRIORITY);
		this.shapes = batch;
		this.viewport = viewport;
	}
	
	@Override
	public void update(float deltaTime) {
		shapes.setProjectionMatrix(getCamera().combined);
		super.update(deltaTime);
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TransformComponent t = TransformComponent.MAPPER.get(entity);
		ShapeComponent sc = ShapeComponent.MAPPER.get(entity);
		
		shapes.begin(sc.getFillType());
		shapes.setColor(0.75f, 0, 0.25f, 1f);
		if(sc.getDrawType() == ShapeComponent.ShapeDraw.BOX){
			shapes.box(t.getPos().x, t.getPos().y, 0f, sc.getWidth(), sc.getHeight(), 0f);
		} else if(sc.getDrawType() == ShapeComponent.ShapeDraw.CIRCLE){
			shapes.circle(t.getPos().x, t.getPos().y, sc.getWidth(), Math.round(sc.getWidth()*6));
		}
		shapes.end();
	}

	private Camera getCamera(){
		return viewport.getCamera();
	}
}
