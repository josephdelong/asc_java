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
public class BuildingType {

	private int id;
	private String name;
	private int offense;
	private int defense;
	
	private int woodCost;
	private int stoneCost;
	private int goldCost;
	
	private boolean special;
	private File image;
	private int maxOccupants;
	private int maxGarrison;
	
	private ArrayList<Integer> requiredBuildings;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public BuildingType() {
		setId(0);
		setName("");
		setOffense(0);
		setDefense(0);
		setWoodCost(0);
		setStoneCost(0);
		setGoldCost(0);
		setSpecial(false);
		setImage(null);
		setMaxOccupants(0);
		setMaxGarrison(0);
		setRequiredBuildings(new ArrayList<Integer>());
	}
	
	/**
	 * Constructor which returns a BuildingType with data members initialized based on the Type of Building desired.
	 * @param buildingType
	 */
	public BuildingType(int buildingTypeId) {
		//PARSE XML FILE TO GET DEFAULT VALUES BASED ON ID
		setId(buildingTypeId);
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * @return the woodCost
	 */
	public int getWoodCost() {
		return woodCost;
	}

	/**
	 * @return the stoneCost
	 */
	public int getStoneCost() {
		return stoneCost;
	}

	/**
	 * @return the goldCost
	 */
	public int getGoldCost() {
		return goldCost;
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
	 * @return the maxOccupants
	 */
	public int getMaxOccupants() {
		return maxOccupants;
	}

	/**
	 * @return the maxGarrison
	 */
	public int getMaxGarrison() {
		return maxGarrison;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param woodCost the woodCost to set
	 */
	public void setWoodCost(int woodCost) {
		this.woodCost = woodCost;
	}

	/**
	 * @param stoneCost the stoneCost to set
	 */
	public void setStoneCost(int stoneCost) {
		this.stoneCost = stoneCost;
	}

	/**
	 * @param goldCost the goldCost to set
	 */
	public void setGoldCost(int goldCost) {
		this.goldCost = goldCost;
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
	 * @param maxOccupants the maxOccupants to set
	 */
	public void setMaxOccupants(int maxOccupants) {
		this.maxOccupants = maxOccupants;
	}

	/**
	 * @param maxGarrison the maxGarrison to set
	 */
	public void setMaxGarrison(int maxGarrison) {
		this.maxGarrison = maxGarrison;
	}

	/**
	 * @param requiredBuildings the requiredBuildings to set
	 */
	public void setRequiredBuildings(ArrayList<Integer> requiredBuildings) {
		this.requiredBuildings = requiredBuildings;
	}
}