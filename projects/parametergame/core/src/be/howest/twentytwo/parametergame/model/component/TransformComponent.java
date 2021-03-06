package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Position, scale and orientation component used to determine how to render the
 * object. Note that this does not track the physics object! This happens in the
 * {@link BodyComponent}.
 */
public class TransformComponent implements Component, Poolable {
	
	public static final ComponentMapper<TransformComponent> MAPPER = ComponentMapper
			.getFor(TransformComponent.class);
	
	private Vector2 position;
	private Vector2 worldSize;
	private float rotation; // 0 - 360
	
	public Vector2 getPos() {
		return position;
	}
	public void setPos(Vector2 position) {
		this.position = position;
	}
	public void setPos(float x, float y){
		setPos(new Vector2(x, y));
	}
	public Vector2 getWorldSize() {
		return worldSize;
	}
	public void setWorldSize(Vector2 worldSize) {
		this.worldSize = worldSize;
	}
	public void setWorldSize(float x, float y){
		setWorldSize(new Vector2(x, y));
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	@Override
	public void reset() {
		setPos(null);
		setWorldSize(null);
		// Need to reset? Should be set by factory anyway.
	}

}
