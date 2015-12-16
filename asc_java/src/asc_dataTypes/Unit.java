/**
 * 
 */
package asc_dataTypes;

import java.io.File;
import java.util.ArrayList;

/**
 * @author joseph_delong
 *
 */
public class Unit {

	/**
	 * Private Data Members
	 */
	private int id;
	private int unitId;
	private String unitName;
	private int player;
	private int city;
	private int location;
	private int offense;
	private int defense;
	private int upgrade;
	private int xp;
	private boolean special;
	private File image;
	private ArrayList<Integer> requiredBuildings;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Unit() {
		setId(0);
		setUnitId(0);
		setUnitName("");
		setPlayer(0);
		setCity(0);
		setLocation(0);
		setOffense(0);
		setDefense(0);
		setUpgrade(0);
		setXp(0);
		setSpecial(false);
		setImage(null);
		setRequiredBuildings(new ArrayList<Integer>());
	}
	
	/**
	 * Constructor which sets a new Unit's details based on its Unit Type
	 * @param unitTypeId
	 */
	public Unit(int unitTypeId) {
		UnitType unitType = new UnitType(unitTypeId);
		setUnitId(unitType.getId());
		setUnitName(unitType.getName());
		setOffense(unitType.getOffense());
		setDefense(unitType.getDefense());
		setSpecial(unitType.getSpecial());
		setImage(unitType.getImage());
		setRequiredBuildings(unitType.getRequiredBuildings());
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the unitId
	 */
	public int getUnitId() {
		return unitId;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @return the player
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * @return the city
	 */
	public int getCity() {
		return city;
	}

	/**
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * @return the offense
	 */
	public int getOffense() {
		return offense;
	}

	/**
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * @return the upgrade
	 */
	public int getUpgrade() {
		return upgrade;
	}

	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}

	/**
	 * @return the special
	 */
	public boolean getSpecial() {
		return special;
	}

	/**
	 * @return the image
	 */
	public File getImage() {
		return image;
	}

	/**
	 * @return the requiredBuildings
	 */
	public ArrayList<Integer> getRequiredBuildings() {
		return requiredBuildings;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(int player) {
		this.player = player;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(int city) {
		this.city = city;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(int location) {
		this.location = location;
	}

	/**
	 * @param offense the offense to set
	 */
	public void setOffense(int offense) {
		this.offense = offense;
	}

	/**
	 * @param defense the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * @param upgrade the upgrade to set
	 */
	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}

	/**
	 * @param xp the xp to set
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}

	/**
	 * @param special the special to set
	 */
	public void setSpecial(boolean special) {
		this.special = special;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(File image) {
		this.image = image;
	}

	/**
	 * @param requiredBuildings the requiredBuildings to set
	 */
	public void setRequiredBuildings(ArrayList<Integer> requiredBuildings) {
		this.requiredBuildings = requiredBuildings;
	}
}
