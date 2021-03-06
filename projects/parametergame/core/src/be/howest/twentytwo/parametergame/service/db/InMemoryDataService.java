package be.howest.twentytwo.parametergame.service.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyData;
import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.DroneData;
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyData;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.FixtureData;
import be.howest.twentytwo.parametergame.dataTypes.GameIdDataI;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsData;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipData;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.PowerupData;
import be.howest.twentytwo.parametergame.dataTypes.PowerupDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserData;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData.WeaponDataBuilder;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

import com.badlogic.gdx.math.Vector2;

public class InMemoryDataService implements IDataService {
	@Override
	public UserDataI getUser(String username, String hashedPassword) {
		return new UserData(username, hashedPassword, "Casual");
	}

	@Override
	public Collection<PlayerShipDataI> getPlayerShips(UserDataI user) {
		Collection<PlayerShipDataI> ships = new ArrayList<PlayerShipDataI>();
		// RECON
		PhysicsDataI physicsData = new PhysicsData(Collision.PLAYER_CATEGORY, Collision.PLAYER_MASK);
		physicsData.addFixture(new FixtureData("circle", 8f, 8f, 0, 0, 0.5f, 0.1f, 0f));

		ArrayList<WeaponDataI> weapons = new ArrayList<>();
		WeaponDataBuilder builder = new WeaponData.WeaponDataBuilder();
		WeaponDataI primaryWeapon = builder.setId("bullet_basic").setOffsetX(0f).setOffsetY(0f).setFireRate(5f)
				.setBulletsPerShot(1).setShotConeAngle(0f).setBulletDamage(1f).setBulletSpeed(60f).setBulletMass(25f)
				.setRange(200f).setAmmoCount(WeaponDataI.INFINITE_AMMO).setBulletSize(new Vector2(1.5f, 0.5f))
				.setTimeDelay(0f).setTurnSpeed(0f).build();
		// new WeaponData("P001", 0f, 0f, 7.5f, 1, 0f, 1f, 5f, 75f, 1500f,0f,
		// 5f,
		// WeaponDataI.INFINITE_AMMO, new Vector2(1f, 0.25f));
		// TODO: Switch to builder to clarify arguments.
		WeaponDataI secondaryWeapon = new WeaponData("bullet_drone", 0f, 0f, 0.75f, 1, 0f, 1f, 10f, 100f, 3500f, 0f,
				0.5f, 25, new Vector2(4f, 1f));
		weapons.add(primaryWeapon);
		weapons.add(secondaryWeapon);
		ShipDataI ship = new ShipData("Recon", "recon", 3, 70.0f, 45.0f, 30.0f, 30.0f, 0.35f, 1f, weapons,
				physicsData, 8f, 8f, 5f);
		PlayerShipDataI playerShip = new PlayerShipData(ship, "Deadline", 10f, 0, 1, 50f, 1);
		ships.add(playerShip);

		// FIGHTER
		physicsData = new PhysicsData(Collision.PLAYER_CATEGORY, Collision.PLAYER_MASK);
		physicsData.addFixture(new FixtureData("circle", 12f, 12f, 0, 0, 0.25f, 0.1f, 0f));
		ship = new ShipData("Fighter", "fighter", 3, 60.0f, 30.0f, 30.0f, 20.0f, 0.45f, 1f, weapons,
				physicsData, 12f, 12f, 0.75f);
		playerShip = new PlayerShipData(ship, "X-Wing", 10f, 0, 1, 50f, 1);
		ships.add(playerShip);

		// JUGGERNAUT
		physicsData = new PhysicsData(Collision.PLAYER_CATEGORY, Collision.PLAYER_MASK);
		physicsData.addFixture(new FixtureData("box", 8f, 8f, 0, 0, 0.25f, 0.1f, 0f));
		ship = new ShipData("Juggernaught", "juggernaught", 3, 40.0f, 30.0f, 15.0f, 15.0f, 0.75f, 1f, weapons,
				physicsData, 16f, 16f, 0.75f);
		playerShip = new PlayerShipData(ship, "Juggernaughty", 10f, 0, 1, 50f, 1);
		ships.add(playerShip);

		return ships;
	}

	@Override
	public List<DroneDataI> getDrones(UserDataI user) {
		List<DroneDataI> data = new ArrayList<>();		// sprite names:
		data.add(new DroneData("Miner", 0, 0));		// drone_geom_collector
		data.add(new DroneData("GravAssistBot-500", 0, 0));		// drone_gravitator
		data.add(new DroneData("Combat Drone", 0, 0));		// drone_minigun
		return data;
	}

	@Override
	public List<EnemyDataI> getEnemies(String... name) {
		List<EnemyDataI> data = new ArrayList<>();
		// scouter
		PhysicsDataI physicsData = new PhysicsData(Collision.ENEMY_CATEGORY, Collision.ENEMY_MASK);
		physicsData.addFixture(new FixtureData("circle", 6f, 6f, 0, 0, 0.5f, 0.1f, 0f));
		ArrayList<WeaponDataI> weapons = new ArrayList<>();
		WeaponDataBuilder builder = new WeaponData.WeaponDataBuilder();
		WeaponDataI primaryWeapon = builder.setId("bullet_shooter").setOffsetX(0f).setOffsetY(0f).setFireRate(1.5f)
				.setBulletsPerShot(1).setShotConeAngle(0f).setBulletDamage(1f).setBulletSpeed(75f).setBulletMass(5f)
				.setRange(250f).setAmmoCount(WeaponDataI.INFINITE_AMMO).setBulletSize(new Vector2(1f, 0.25f))
				.setTimeDelay(0f).setTurnSpeed(0f).build();
		weapons.add(primaryWeapon);

		ShipData shipData = new ShipData("scouter", "scouter", 3, 30.0f, 30.0f, 10.0f, 10.0f, 0.1f, 1.0f, weapons,
				physicsData, 8f, 8f, 1f);

		data.add(new EnemyData("scouter", 5f, 100, "Scouter", shipData));

		// encloser
		physicsData = new PhysicsData(Collision.ENEMY_CATEGORY, Collision.ENEMY_MASK);
		physicsData.addFixture(new FixtureData("circle", 6f, 6f, 0, 0, 0.5f, 0.1f, 0f));
		weapons = new ArrayList<>();
		builder = new WeaponData.WeaponDataBuilder();
		primaryWeapon = builder.setId("missile_projectile").setOffsetX(0f).setOffsetY(0f).setFireRate(1f)
				.setBulletsPerShot(1).setShotConeAngle(0f).setBulletDamage(1f).setBulletSpeed(100f).setBulletMass(10f)
				.setRange(500f).setAmmoCount(WeaponDataI.INFINITE_AMMO).setBulletSize(new Vector2(2.25f, 1))
				.setTimeDelay(0f).setTurnSpeed(0f).build();
		weapons.add(primaryWeapon);

		shipData = new ShipData("encloser", "encloser", 3, 30.0f, 30.0f, 10.0f, 10.0f, 0.1f, 1.0f, weapons, physicsData,
				8f, 8f, 1f);

		data.add(new EnemyData("encloser", 10f, 250, "Brutalizer", shipData));

		return data;
	}

	public Collection<WeaponDataI> getWeapons(ShipDataI ship) {
		ArrayList<WeaponDataI> weapons = new ArrayList<>();
		WeaponDataBuilder builder = new WeaponData.WeaponDataBuilder();
		WeaponDataI primaryWeapon = builder.setId("P001").setOffsetX(0f).setOffsetY(0f).setFireRate(7.5f)
				.setBulletsPerShot(1).setShotConeAngle(0f).setBulletDamage(1f).setBulletSpeed(75f).setBulletMass(5f)
				.setRange(250f).setAmmoCount(WeaponDataI.INFINITE_AMMO).setBulletSize(new Vector2(1f, 0.25f))
				.setTimeDelay(0f).setTurnSpeed(0f).build();
		// new WeaponData("P001", 0f, 0f, 7.5f, 1, 0f, 1f, 5f, 75f, 1500f,0f,
		// 5f,
		// WeaponDataI.INFINITE_AMMO, new Vector2(1f, 0.25f));
		// TODO: Switch to builder to clarify arguments.
		WeaponDataI secondaryWeapon = new WeaponData("W02", 0f, 0f, 0.75f, 1, 0f, 1f, 10f, 100f, 3500f, 0f, 1f, 25,
				new Vector2(2.5f, 0.5f));
		weapons.add(primaryWeapon);
		weapons.add(secondaryWeapon);
		return weapons;
	}

	@Override
	public Collection<PowerupDataI> getPowerups() {
		Collection<PowerupDataI> powerups = new HashSet<>();
		powerups.add(new PowerupData("geom", "geomEffect", -1, 180, "movementSpeed", 3));
		return powerups;
	}

	@Override
	public Collection<DifficultyDataI> getDifficulties() {
		Collection<DifficultyDataI> difficulties = new ArrayList<DifficultyDataI>();
		difficulties.add(new DifficultyData("Casual", 0.25f, 0.5f, 0.5f, 0.25f));
		difficulties.add(new DifficultyData("Normal", 1f, 1f, 1f, 1f));
		difficulties.add(new DifficultyData("Dark Souls", 3f, 3f, 4f, 5f));
		difficulties.add(new DifficultyData("Advanced JS", 0.1f, 0.33f, 0.2f, 25f));
		return difficulties;
	}

	@Override
	public void saveUser(UserDataI data) {

	}

	@Override
	public void savePlayerShip(PlayerShipDataI data) {
	}

	@Override
	public void saveDrone(DroneDataI data, UserDataI user) {

	}

	@Override
	public void saveWeapon(WeaponDataI weapon, ShipDataI ship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveScore(PlayerShipDataI ship, GameIdDataI game, int points, Timestamp date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

}

/*
 * Collector drones effects
 * 
 * @param range(utility) the range in which geoms will be accelerated towards
 * the ships current position.
 * 
 * @param acceleration(power) the speed the geoms are accelerated with. this is
 * uncapped, and is only limited by the initial speed. geoms stop moving
 * (?decelerate?) if the ship flies away and they get out of the range again.
 * 
 * @return geoms are only collected once they reach the ship's actual collect
 * position
 */

/*
 * Shooter drones effects
 * 
 * @param range(utility) the range in which it will shoot enemies.
 * 
 * @param attackSpeed(power) the speed at wich it shoots
 * 
 * @return always does one damage
 */

/*
 * gravitator drones effects
 * 
 * @param gravityReduction(utility) the percentage of gravity ignored
 * 
 * @param antigravitation(power) creates a negative gravity field, making it
 * harder for enemies to close in to you. (suicider & suicide squadron mainly)
 * 
 * @return has a static range, for antigravitation only
 */
