package be.howest.twentytwo.parametergame.model.component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Stores movement data and state.
 */
public class MovementComponent implements Component, Poolable {

	public static final ComponentMapper<MovementComponent> MAPPER = ComponentMapper
			.getFor(MovementComponent.class);

	// Flags set by input
	private boolean accelerateForward;
	private boolean accelerateBackward;
	private boolean turnLeft;
	private boolean turnRight;
	private BooleanProperty dampenOnProperty;
	// private boolean dampenOn;
	

	private float maxLinearVelocity; // Limit to linear velocity (units/second)
	private float maxAngularVelocity; // Limit to angular velocity (in radians)

	private float linearAcceleration; // units / s�
	private float angularAcceleration; // = Turn rate in radians / second

	private float linearDampStrength;

	public MovementComponent(){
		reset();
	}
	
	public boolean isAccelerateForward() {
		return accelerateForward;
	}

	public void setAccelerateForward(boolean accelerateForward) {
		this.accelerateForward = accelerateForward;
	}

	public boolean isAccelerateBackward() {
		return accelerateBackward;
	}

	public void setAccelerateBackward(boolean accelerateBackward) {
		this.accelerateBackward = accelerateBackward;
	}

	public boolean isTurnLeft() {
		return turnLeft;
	}

	public void setTurnLeft(boolean turnLeft) {
		this.turnLeft = turnLeft;
	}

	public boolean isTurnRight() {
		return turnRight;
	}

	public void setTurnRight(boolean turnRight) {
		this.turnRight = turnRight;
	}

	public BooleanProperty getDampenOnProperty(){
		return dampenOnProperty;
	}
	
	public boolean isDampenOn() {
		// return dampenOn;
		return dampenOnProperty.get();
	}

	public void toggleDampen() {
//		this.dampenOn = !dampenOn;
		dampenOnProperty.set(! dampenOnProperty.get());
	}

	public float getMaxLinearVelocity() {
		return maxLinearVelocity;
	}

	public void setMaxLinearVelocity(float maxLinearVelocity) {
		this.maxLinearVelocity = maxLinearVelocity;
	}

	public float getMaxAngularVelocity() {
		return maxAngularVelocity;
	}

	public void setMaxAngularVelocity(float maxAngularVelocity) {
		this.maxAngularVelocity = maxAngularVelocity;
	}

	public float getLinearAcceleration() {
		return linearAcceleration;
	}

	public void setLinearAcceleration(float linearAcceleration) {
		this.linearAcceleration = linearAcceleration;
	}

	public float getAngularAcceleration() {
		return angularAcceleration;
	}

	public void setAngularAcceleration(float angularAcceleration) {
		this.angularAcceleration = angularAcceleration;
	}

	public float getLinearDampStrength() {
		return linearDampStrength;
	}

	public void setLinearDampStrength(float linearDampStrength) {
		this.linearDampStrength = linearDampStrength;
	}

	@Override
	public void reset() {
		dampenOnProperty = new SimpleBooleanProperty();
	}
}
