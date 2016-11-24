package be.howest.twentytwo.parametergame.model.physics.events;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Describes a torque event that is applied on the target body.
 */
public class AngularForceEvent extends SinglePhysicsEvent {

	private Body body;
	private float torque;

	// TODO: Data passed as param or smth? (See GravityPhysicsEvent)
	public AngularForceEvent(Body body, float torque) {
		super();
		this.body = body;
		this.torque = torque;
	}

	@Override
	public void execute() {
		super.execute();
		//body.applyAngularImpulse(impulse, true);
		body.applyTorque(torque, true);
	}
	
	public Body getBody(){
		return body;
	}
	
	public float getImpulse(){
		return torque;
	}
	
	@Override
	public int hashCode() {
		int prime = 41;
		return getBody().hashCode() * Math.round(getImpulse()) * prime;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof AngularForceEvent) {
			AngularForceEvent other = (AngularForceEvent) obj;
			if(getBody().equals(other.getBody()) && getImpulse() == other.getImpulse()) {
				return true;
			}
		}
		return false;
	}
}
