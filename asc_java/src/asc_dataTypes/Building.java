/**
 * 
 */
package asc_dataTypes;

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
	private int image;
	private ArrayList<Integer> occupants;
	private ArrayList<Integer> garrisonedUnits;
	private int productionType;

	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Building() {
		this.id = 0;
		this.buildingType = 0;
		this.name = "";
		this.city = 0;
		this.location = 0;
		this.defense = 0;
		this.offense = 0;
		this.upgrade = 0;
		this.special = false;
		this.image = 0;
		this.occupants = new ArrayList<Integer>();
		this.garrisonedUnits = new ArrayList<Integer>();
		this.productionType = 0;
	}

	/**
	 * Constructor which sets a new Building's details based on its Building Type
	 * @param buildingType int representing the ASC BuildingType of this Building
	 */
	public Building(int buildingType) {
		BuildingType type = new BuildingType(buildingType);
		this.setBuildingType(type.getId());
		this.setName("New " + type.getName());
		this.setDefense(type.getDefense());
		this.setOffense(type.getDefense());
		this.setSpecial(type.getSpecial());
		this.setImage(type.getImage());
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
	public int getImage() {
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
	public void setImage(int image) {
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

}
