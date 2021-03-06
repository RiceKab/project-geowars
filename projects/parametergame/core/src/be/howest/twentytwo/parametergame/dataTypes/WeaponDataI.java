package be.howest.twentytwo.parametergame.dataTypes;

import com.badlogic.gdx.math.Vector2;

public interface WeaponDataI {
	
	public final static int INFINITE_AMMO = -1;
	
	public String getID();	// = Name
	
	public float getOffsetX();
	public float getOffsetY();
	
	public float getFireRate();
	public int getBulletsPerShot();
	public float getShotConeAngle();
	public int getAmmoCount();
	
	public float getBulletMass();	// Defines how resistant it is to gravity.
	public Vector2 getBulletSize();	// TODO: Missing in information modelling
	public float getBulletDamage();	// Per bullet
	public float getBulletSpeed();	// Bullet speed
	public float getRange();	// Bullet fizzle range
	public float getTurnSpeed();	//How fast you can reposition the turret
	
	/**
	 * gets the time inbetween the collision and detonation, such as the delay between flying over a mine, and the mine exploding
	 * @return the time after colliding with an unit that the bullet should apply it's on-hit effect
	 */
	public float getTimeDelay();	// Delay after collision with enemy before the Object triggers. bullet will be 0, mine will have a delay.
}
