/**
 * @author Joseph DeLong
 *
 */
package asc_dataTypes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import util.XMLparser;

/**
 *
 */
public class UnitType extends DataType {
	
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
	 * Constructor which clones the passed in UnitType.
	 * @param u The UnitType to clone.
	 */
	public UnitType(UnitType u) {
		this.setId(u.getId());
		this.setName(u.getName());
		this.setOffense(u.getOffense());
		this.setDefense(u.getDefense());
		this.setFoodCost(u.getFoodCost());
		this.setWoodCost(u.getWoodCost());
		this.setStoneCost(u.getStoneCost());
		this.setIronCost(u.getIronCost());
		this.setCottonCost(u.getCottonCost());
		this.setSilkCost(u.getSilkCost());
		this.setGoldCost(u.getGoldCost());
		this.setSpecial(u.getSpecial());
		this.setImage(u.getImage());
		this.setDescription(u.getDescription());
		this.setRequiredBuildings(u.getRequiredBuildings());
	}
	
	/**
	 * Constructor which sets a new Unit Type's details based on its ID
	 * @param unitTypeId <code>int</code> representing the ID of this UnitType.
	 */
	public UnitType(int unitTypeId) {
		// Parse data source for values
		UnitType temp = UnitType.getInstance(unitTypeId);
		if(temp.equals(null)) {
			new UnitType();
		} else {
			new UnitType(temp);
		}
	}

	/**
	 * Parse method which sets the data members of this class to values parsed from input
	 */
	@Override
	public void parse(String fieldName, String value) {
		if(fieldName.equals(null) || fieldName.isEmpty() || fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("name")) {
			this.setName(value);
		} else if(fieldName.equalsIgnoreCase("type")) {
			this.setType(value);
		} else if(fieldName.equalsIgnoreCase("offense")) {
			this.setOffense(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("defense")) {
			this.setDefense(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("foodCost")) {
			this.setFoodCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("woodCost")) {
			this.setWoodCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("stoneCost")) {
			this.setStoneCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("ironCost")) {
			this.setIronCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("cottonCost")) {
			this.setCottonCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("silkCost")) {
			this.setSilkCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("goldCost")) {
			this.setGoldCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("special")) {
			this.setSpecial(Boolean.parseBoolean(value));
		} else if(fieldName.equalsIgnoreCase("image")) {
			this.setImage(new File(value));
		} else if(fieldName.equalsIgnoreCase("description")) {
			this.setDescription(value);
		} else if(fieldName.equalsIgnoreCase("requiredBuildings")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("requiredBuilding")) {
			this.addRequiredBuilding(Integer.valueOf(value));
		}
	}

	/**
	 * Method which returns an instance of UnitType based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of UnitType you are looking for.
	 * @return UnitType associated with instanceId, or null.
	 */
	public static UnitType getInstance(Integer instanceId) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> units = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			units = parser.parse("src/asc_dataTypes/unitTypes.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get UnitType Instance lookup", e);
		}
		
		Iterator<DataType> it = units.iterator();
		if(it.hasNext()) {
			return (UnitType)it.next();
		} else {
			return null;
		}
	}
	
	/**
	 * Method which returns this Data Type's fields.
	 * @return ArrayList of Strings containing the names of this Data Type's fields. 
	 */
	public ArrayList<String> getFields() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("name");
		fields.add("type");
		fields.add("offense");
		fields.add("defense");
		fields.add("foodCost");
		fields.add("woodCost");
		fields.add("stoneCost");
		fields.add("ironCost");
		fields.add("cottonCost");
		fields.add("silkCost");
		fields.add("goldCost");
		fields.add("special");
		fields.add("image");
		fields.add("description");
		fields.add("requiredBuildings");
		return fields;
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
	
	/**
	 * Adds a Required Building to this UnitType
	 * @param buildingTypeId
	 */
	private void addRequiredBuilding(Integer buildingTypeId) {
		ArrayList<Integer> temp = this.getRequiredBuildings();
		temp.add(buildingTypeId);
		this.setRequiredBuildings(temp);
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
