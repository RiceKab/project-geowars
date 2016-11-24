package be.howest.twentytwo.parametergame.dataTypes;

public interface WeaponDataI {
	
	public String getID();	// = Name
	
	public float getOffsetX();
	public float getOffsetY();
	
	public float getFireRate();
	public int getBulletsPerShot();
	public float getShotConeAngle();
	
	public float getDamage();	// Per bullet
	public float getSpeed();	// Bullet speed
	public float getRange();	// Bullet fizzle range
	
	public float getTimeDelay();	// Delay after collision with enemy before the Object triggers. bullet will be 0, mine will have a delay.
}
