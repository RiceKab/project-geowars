package be.howest.twentytwo.parametergame.service.db;

import java.sql.Timestamp;
import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
/*
 * Reads all data regarding the game, but not the level. This contains data about users, ships, drones & enemies
 */
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.EnemyDataI;
import be.howest.twentytwo.parametergame.dataTypes.GameIdDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.PowerupDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;

public interface IDataService {
	//This is the general blueprint for different classes, such as MySQLDataService, T-SQLDataService,....
	/**
	 * fetches an user from the db, based on username and hashed password
	 * @param username the username that is checked upon
	 * @param hashedPassword the hashed password, this must be equal to the value in the database
	 * @return the first user found, or null if none is found or the passwords don't match
	 */
	public UserDataI getUser(String username, String hashedPassword);

	/**
	 * @param names A string or an array of strings that contain the ID('s) of the enemy/enemies
	 * @return HashSet of enemies, if the enemy is not found, there will be a null value
	 */
	public Collection<EnemyDataI> getEnemies(String... names);	//allows you to write getEnemies("Str1","Str2) instead of getEnemies(Str[])

	/**
	 * @param user an implementation of UserDataI that can provide a getUser() method to call the user's name
	 * @return returns a playerShip with an empty collection of drones, or a new HashSet if no playerShips are found
	 */
	public Collection<PlayerShipDataI> getPlayerShips(UserDataI user);

	/**
	 * @return returns an empty HashSet if no drones are found
	 */
	public Collection<DroneDataI> getDrones(UserDataI user);

	/**
	 * @return returns an empty HashSet if no powerups are found
	 */
	public Collection<PowerupDataI> getPowerups();

	/**
	 * @return returns an empty HashSet if no difficulties are found
	 */
	public Collection<DifficultyDataI> getDifficulties();
	/**
	 * This method looks an user up in the database, searching on the username. 
	 * If an user is found, it will modify the data in the database to match the passed object.
	 * If no user is found, it will create a new record matching the passed object.
	 * @param data the user we want to save
	 */
	public void saveUser(UserDataI data);
	
	public void savePlayerShip(PlayerShipDataI data);
	
	public void saveDrone(DroneDataI data, UserDataI user);
	
	public void saveWeapon(WeaponDataI weapon, ShipDataI ship);
	
	public void saveScore(PlayerShipDataI ship, GameIdDataI game, int points, Timestamp date);
	
	public void closeConnection();
	
}
