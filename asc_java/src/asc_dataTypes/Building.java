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
		this.setId(0);
		this.setBuildingType(0);
		this.setName("");
		this.setCity(0);
		this.setLocation(0);
		this.setDefense(0);
		this.setOffense(0);
		this.setUpgrade(0);
		this.setSpecial(false);
		this.setImage(null);
		this.setOccupants(new ArrayList<Integer>());
		this.setGarrisonedUnits(new ArrayList<Integer>());
		this.setProductionType(0);
		this.setRequiredBuildings(new ArrayList<Integer>());
	}

	/**
	 * Constructor which sets a new Building's details based on its Building Type.
	 * @param buildingTypeId <code>int</code> representing the ASC BuildingType of this Building.
	 */
	public Building(int buildingTypeId) {
		BuildingType buildingType = new BuildingType(buildingTypeId);
		this.setBuildingType(buildingType.getId());
		this.setName("New " + buildingType.getName());
		this.setDefense(buildingType.getDefense());
		this.setOffense(buildingType.getDefense());
		this.setSpecial(buildingType.getSpecial());
		this.setImage(buildingType.getImage());
		this.setRequiredBuildings(buildingType.getRequiredBuildings());
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Building: [id=");
		builder.append(id);
		builder.append(", buildingType=");
		builder.append(buildingType);
		builder.append(", name=");
		builder.append(name);
		builder.append(", city=");
		builder.append(city);
		builder.append(", location=");
		builder.append(location);
		builder.append(", defense=");
		builder.append(defense);
		builder.append(", offense=");
		builder.append(offense);
		builder.append(", upgrade=");
		builder.append(upgrade);
		builder.append(", special=");
		builder.append(special);
		builder.append(", image=");
		builder.append(image);
		builder.append(", occupants=");
		builder.append(occupants);
		builder.append(", garrisonedUnits=");
		builder.append(garrisonedUnits);
		builder.append(", productionType=");
		builder.append(productionType);
		builder.append(", requiredBuildings=");
		builder.append(requiredBuildings);
		builder.append("]");
		return builder.toString();
	}

}
