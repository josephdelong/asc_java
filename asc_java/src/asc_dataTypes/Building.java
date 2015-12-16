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
public class Building {

	/**
	 * Private data members: id buildingType name city location defense offense
	 * upgrade special image occupants garrisonedUnits
	 */
	private int id;
	private int buildingType;
	private String name;
	private int city;
	private int location;
	private int defense;
	private int offense;
	private int upgrade;
	private boolean special;
	private File image;
	private ArrayList<Integer> occupants;
	private ArrayList<Integer> garrisonedUnits;
	private int productionType;
	private ArrayList<Integer> requiredBuildings;

	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Building() {
		setId(0);
		setBuildingType(0);
		setName("");
		setCity(0);
		setLocation(0);
		setDefense(0);
		setOffense(0);
		setUpgrade(0);
		setSpecial(false);
		setImage(null);
		setOccupants(new ArrayList<Integer>());
		setGarrisonedUnits(new ArrayList<Integer>());
		setProductionType(0);
		setRequiredBuildings(new ArrayList<Integer>());
	}

	/**
	 * Constructor which sets a new Building's details based on its Building Type
	 * @param buildingTypeId int representing the ASC BuildingType of this Building
	 */
	public Building(int buildingTypeId) {
		BuildingType buildingType = new BuildingType(buildingTypeId);
		setBuildingType(buildingType.getId());
		setName("New " + buildingType.getName());
		setDefense(buildingType.getDefense());
		setOffense(buildingType.getDefense());
		setSpecial(buildingType.getSpecial());
		setImage(buildingType.getImage());
		setRequiredBuildings(buildingType.getRequiredBuildings());
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the buildingType
	 */
	public int getBuildingType() {
		return buildingType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * @return the offense
	 */
	public int getOffense() {
		return offense;
	}

	/**
	 * @return the upgrade
	 */
	public int getUpgrade() {
		return upgrade;
	}

	/**
	 * @return the special
	 */
	public boolean isSpecial() {
		return special;
	}

	/**
	 * @return the image
	 */
	public File getImage() {
		return image;
	}

	/**
	 * @return the occupants
	 */
	public ArrayList<Integer> getOccupants() {
		return occupants;
	}

	/**
	 * @return the garrisonedUnits
	 */
	public ArrayList<Integer> getGarrisonedUnits() {
		return garrisonedUnits;
	}

	/**
	 * @return the productionType
	 */
	public int getProductionType() {
		return productionType;
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
	 * @param buildingType the buildingType to set
	 */
	public void setBuildingType(int buildingType) {
		this.buildingType = buildingType;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param defense the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * @param offense the offense to set
	 */
	public void setOffense(int offense) {
		this.offense = offense;
	}

	/**
	 * @param upgrade the upgrade to set
	 */
	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
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
	 * @param occupants the occupants to set
	 */
	public void setOccupants(ArrayList<Integer> occupants) {
		this.occupants = occupants;
	}

	/**
	 * @param garrisonedUnits the garrisonedUnits to set
	 */
	public void setGarrisonedUnits(ArrayList<Integer> garrisonedUnits) {
		this.garrisonedUnits = garrisonedUnits;
	}

	/**
	 * @param productionType the productionType to set
	 */
	public void setProductionType(int productionType) {
		this.productionType = productionType;
	}

	/**
	 * @param requiredBuildings the requiredBuildings to set
	 */
	public void setRequiredBuildings(ArrayList<Integer> requiredBuildings) {
		this.requiredBuildings = requiredBuildings;
	}

}
