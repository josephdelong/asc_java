/**
 * @author Joseph DeLong
 *
 */
package dataTypes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import exceptions.ASCException;
import exceptions.InvalidBuildingProductionTypeException;
import util.XMLparser;

/**
 *
 */
public class BuildingType extends DataType {

	/**
	 * Private Data Members
	 */
	private int id;
	private String name;
	private int offense;
	private int defense;
	private int woodCost;
	private int stoneCost;
	private int goldCost;
	private boolean special;
	private File image;
	private String description;
	private int maxOccupants;
	private int maxGarrison;
	private ArrayList<Building> requiredBuildings;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public BuildingType() {
		this.setId(0);
		this.setName("");
		this.setOffense(0);
		this.setDefense(0);
		this.setWoodCost(0);
		this.setStoneCost(0);
		this.setGoldCost(0);
		this.setSpecial(false);
		this.setImage(null);
		this.setDescription("");
		this.setMaxOccupants(0);
		this.setMaxGarrison(0);
		this.setRequiredBuildings(new ArrayList<Building>());
	}

	/**
	 * Parse method which sets the data members of this class to values parsed from input
	 * @throws ASCException 
	 */
	@Override
	public void parse(String fieldName, String attribute, String value) throws ASCException {
		if(fieldName == null || fieldName.equals(null) || fieldName.isEmpty() || fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("name")) {
			this.setName(value);
		} else if(fieldName.equalsIgnoreCase("offense")) {
			this.setOffense(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("defense")) {
			this.setDefense(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("woodCost")) {
			this.setWoodCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("stoneCost")) {
			this.setStoneCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("goldCost")) {
			this.setGoldCost(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("special")) {
			this.setSpecial(Boolean.parseBoolean(value));
		} else if(fieldName.equalsIgnoreCase("image")) {
			this.setImage(new File(value));
		} else if(fieldName.equalsIgnoreCase("description")) {
			this.setDescription(value);
		} else if(fieldName.equalsIgnoreCase("maxOccupants")) {
			this.setMaxOccupants(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("maxGarrison")) {
			this.setMaxGarrison(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("requiredBuildings")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("requiredBuilding")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				this.addRequiredBuilding(Integer.valueOf(s));
			}
		}
	}

	/**
	 * Method which returns an instance of BuildingType based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of BuildingType you are looking for.
	 * @return BuildingType associated with instanceId, or null.
	 */
	public static BuildingType getInstance(Integer instanceId) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> buildingTypes = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			buildingTypes = parser.parse("src/datastore/buildingTypes.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get BuildingType instance lookup: " + instanceId, e);
			System.exit(1);
		}
		
		Iterator<DataType> it = buildingTypes.iterator();
		if(it.hasNext()) {
			return (BuildingType)it.next();
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
		fields.add("offense");
		fields.add("defense");
		fields.add("woodCost");
		fields.add("stoneCost");
		fields.add("goldCost");
		fields.add("special");
		fields.add("image");
		fields.add("description");
		fields.add("maxOccupants");
		fields.add("maxGarrison");
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	public ArrayList<Building> getRequiredBuildings() {
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	public void setRequiredBuildings(ArrayList<Building> requiredBuildings) {
		this.requiredBuildings = requiredBuildings;
	}
	
	/**
	 * Adds a Required Building to this BuildingType
	 * @param buildingTypeId
	 * @throws InvalidBuildingProductionTypeException 
	 */
	private void addRequiredBuilding(Integer buildingTypeId) throws InvalidBuildingProductionTypeException {
		ArrayList<Building> temp = this.getRequiredBuildings();
		temp.add(new Building(buildingTypeId));
		this.setRequiredBuildings(temp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BuildingType:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("name=");
		builder.append(name);
		builder.append("\n\t");
		builder.append("offense=");
		builder.append(offense);
		builder.append("\n\t");
		builder.append("defense=");
		builder.append(defense);
		builder.append("\n\t");
		builder.append("woodCost=");
		builder.append(woodCost);
		builder.append("\n\t");
		builder.append("stoneCost=");
		builder.append(stoneCost);
		builder.append("\n\t");
		builder.append("goldCost=");
		builder.append(goldCost);
		builder.append("\n\t");
		builder.append("special=");
		builder.append(special);
		builder.append("\n\t");
		builder.append("image=");
		builder.append(image);
		builder.append("\n\t");
		builder.append("description=");
		builder.append(description);
		builder.append("\n\t");
		builder.append("maxOccupants=");
		builder.append(maxOccupants);
		builder.append("\n\t");
		builder.append("maxGarrison=");
		builder.append(maxGarrison);
		builder.append("\n\t");
		builder.append("requiredBuildings:");
		ArrayList<Building> buildings = this.getRequiredBuildings();
		if(buildings == null) {
			builder.append((String) null);
		} else {
			Iterator<Building> it = buildings.iterator();
			while(it.hasNext()) {
				Building b = it.next();
				builder.append("\n\t\t");
				builder.append(b.getName());
				Integer i = Integer.valueOf(b.getProductionType());
				if(i != null && i > 0 && i < 4) {
					builder.append(", Type=");
					builder.append(i);
				}
			}
		}
		return builder.toString();
	}
}
