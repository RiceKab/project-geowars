package be.howest.twentytwo.parametergame.model.spawn.message;

import com.badlogic.gdx.math.Vector2;

import be.howest.twentytwo.parametergame.factory.ISpawnFactory;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

public class SpawnGeomMessage implements ISpawnMessage {

	private final Vector2 pos;
	private final float rotation;
	private final int amount;

	public SpawnGeomMessage(Vector2 pos, float rotation, int amount) {
		this.pos = pos;
		this.rotation = rotation;
		this.amount = amount;
	}

	public SpawnGeomMessage(Vector2 pos, int amount) {
		this(pos, (float) Math.random(), amount);
	}

	@Override
	public String getType() {
		return "geom"; // HARD CODE
	}

	@Override
	public void execute(ISpawnFactory factory) {
		Vector2 spawnPos;
		for (int i = 0; i < amount; i++) {
			spawnPos = new Vector2(getPos().x + (float) Math.random() * 4 - 2,
					getPos().y + (float) Math.random() * 4 - 2);

			factory.spawnEntity(spawnPos, getRotation(), new Vector2(0, 0), Collision.PLAYER_PICKUPS,
					Collision.PICKUP_MASK);
		}

	}

	public Vector2 getPos() {
		return pos;
	}

	public float getRotation() {
		return rotation;
	}

}