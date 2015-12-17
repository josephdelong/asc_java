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
public class UnitType {
	
	/**
	 * Private Data Members
	 */
	private int id;
	private String name;
	private String type;
	private int offense;
	private int defense;
	private int foodCost;
	private int woodCost;
	private int stoneCost;
	private int ironCost;
	private int cottonCost;
	private int silkCost;
	private int goldCost;
	private boolean special;
	private File image;
	private String description;
	private ArrayList<Integer> requiredBuildings;

	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public UnitType() {
		this.setId(0);
		this.setName("");
		this.setOffense(0);
		this.setDefense(0);
		this.setFoodCost(0);
		this.setWoodCost(0);
		this.setStoneCost(0);
		this.setIronCost(0);
		this.setCottonCost(0);
		this.setSilkCost(0);
		this.setGoldCost(0);
		this.setSpecial(false);
		this.setImage(null);
		this.setDescription("");
		this.setRequiredBuildings(new ArrayList<Integer>());
	}
	
	/**
	 * Constructor which sets a new Unit Type's details based on its ID
	 * @param unitTypeId <code>int</code> representing the ID of this UnitType.
	 */
	public UnitType(int unitTypeId) {
		//PARSE XML FILE TO GET DEFAULT VALUES BASED ON ID
		this.setId(unitTypeId);
		//set this unit type's values to the parsed XML values
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
	 * @return the type
	 */
	public String getType() {
		return type;
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
	 * @return the foodCost
	 */
	public int getFoodCost() {
		return foodCost;
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
	 * @return the ironCost
	 */
	public int getIronCost() {
		return ironCost;
	}

	/**
	 * @return the cottonCost
	 */
	public int getCottonCost() {
		return cottonCost;
	}

	/**
	 * @return the silkCost
	 */
	public int getSilkCost() {
		return silkCost;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the requiredBuildings
	 */
	public ArrayList<Integer> getRequiredBuildings() {
		return requiredBuildings;
	}

	/**
	 * @param iD the iD to set
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
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @param foodCost the foodCost to set
	 */
	public void setFoodCost(int foodCost) {
		this.foodCost = foodCost;
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
	 * @param ironCost the ironCost to set
	 */
	public void setIronCost(int ironCost) {
		this.ironCost = ironCost;
	}

	/**
	 * @param cottonCost the cottonCost to set
	 */
	public void setCottonCost(int cottonCost) {
		this.cottonCost = cottonCost;
	}

	/**
	 * @param silkCost the silkCost to set
	 */
	public void setSilkCost(int silkCost) {
		this.silkCost = silkCost;
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
		builder.append("UnitType: [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", offense=");
		builder.append(offense);
		builder.append(", defense=");
		builder.append(defense);
		builder.append(", foodCost=");
		builder.append(foodCost);
		builder.append(", woodCost=");
		builder.append(woodCost);
		builder.append(", stoneCost=");
		builder.append(stoneCost);
		builder.append(", ironCost=");
		builder.append(ironCost);
		builder.append(", cottonCost=");
		builder.append(cottonCost);
		builder.append(", silkCost=");
		builder.append(silkCost);
		builder.append(", goldCost=");
		builder.append(goldCost);
		builder.append(", special=");
		builder.append(special);
		builder.append(", image=");
		builder.append(image);
		builder.append(", description=");
		builder.append(description);
		builder.append(", requiredBuildings=");
		builder.append(requiredBuildings);
		builder.append("]");
		return builder.toString();
	}
	
}
