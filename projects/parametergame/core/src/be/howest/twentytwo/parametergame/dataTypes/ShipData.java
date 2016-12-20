package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShipData implements ShipDataI{
	
	private String name;
	private int health;
	private float maxLinearSpeed;
	private float maxAngularSpeed;
	private float linearAcceleration;
	private float angularAcceleration;
	private float linearDamping;
	private float angularDamping;
	private List<WeaponDataI> weapons;
	private PhysicsDataI physicsData;
	private float shipSizeX;
	private float shipSizeY;
	
	public ShipData(String name, int health, float maxLinearSpeed, float maxAngularSpeed, float linearAcceleration, float angularAcceleration, float linearDamping, float angularDamping, List<WeaponDataI> weapons, PhysicsDataI physicsData, float shipSizeX, float shipSizeY){
		this.name = name;
		this.maxLinearSpeed = maxLinearSpeed;
		this.maxAngularSpeed = maxAngularSpeed;
		this.linearAcceleration = linearAcceleration;
		this.angularAcceleration = angularAcceleration;
		this.linearDamping = linearDamping;
		this.angularDamping = angularDamping;
		this.weapons = weapons;
		this.physicsData = physicsData;
		this.shipSizeX = shipSizeX;
		this.shipSizeY = shipSizeY;
	}
	
	//	GETTERS

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	public float getLinearAcceleration() {
		return linearAcceleration;
	}

	public float getAngularAcceleration() {
		return angularAcceleration;
	}

	public float getLinearDamping() {
		return linearDamping;
	}

	public float getAngularDamping() {
		return angularDamping;
	}

	public List<WeaponDataI> getWeapons() {
		return weapons;
	}

	public PhysicsDataI getPhysicsData() {
		return physicsData;
	}
	
	public float getShipSizeX() {
		return shipSizeX;
	}
	
	public float getShipSizeY() {
		return shipSizeY;
	}

}
