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
	private int maxOccupants;
	private int maxGarrison;
	private ArrayList<Integer> requiredBuildings;
	
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
		this.setMaxOccupants(0);
		this.setMaxGarrison(0);
		this.setRequiredBuildings(new ArrayList<Integer>());
	}

	/**
	 * Constructor which clones the passed in BuildingType.
	 * @param b The BuildingType to clone.
	 */
	public BuildingType(BuildingType b) {
		this.setId(b.getId());
		this.setName(b.getName());
		this.setOffense(b.getOffense());
		this.setDefense(b.getDefense());
		this.setWoodCost(b.getWoodCost());
		this.setStoneCost(b.getStoneCost());
		this.setGoldCost(b.getGoldCost());
		this.setSpecial(b.getSpecial());
		this.setImage(b.getImage());
		this.setMaxOccupants(b.getMaxOccupants());
		this.setMaxGarrison(b.getMaxGarrison());
		this.setRequiredBuildings(b.getRequiredBuildings());
	}
	
	/**
	 * Constructor which returns a BuildingType with data members initialized based on the Type of Building desired.
	 * @param buildingType <code>int</code> representing the ID of this Building Type.
	 */
	public BuildingType(Integer buildingTypeId) {
		// Parse data source for values
		BuildingType temp = BuildingType.getInstance(buildingTypeId);
		if(temp.equals(null)) {
			new BuildingType();
		} else {
			new BuildingType(temp);
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
		} else if(fieldName.equalsIgnoreCase("maxOccupants")) {
			this.setMaxOccupants(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("maxGarrison")) {
			this.setMaxGarrison(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("requiredBuildings")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("requiredBuilding")) {
			this.addRequiredBuilding(Integer.valueOf(value));
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
			buildingTypes = parser.parse("src/asc_dataTypes/buildingTypes.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get BuildingType Instance lookup", e);
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
	
	/**
	 * Adds a Required Building to this BuildingType
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
		builder.append("BuildingType: [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", offense=");
		builder.append(offense);
		builder.append(", defense=");
		builder.append(defense);
		builder.append(", woodCost=");
		builder.append(woodCost);
		builder.append(", stoneCost=");
		builder.append(stoneCost);
		builder.append(", goldCost=");
		builder.append(goldCost);
		builder.append(", special=");
		builder.append(special);
		builder.append(", image=");
		builder.append(image);
		builder.append(", maxOccupants=");
		builder.append(maxOccupants);
		builder.append(", maxGarrison=");
		builder.append(maxGarrison);
		builder.append(", requiredBuildings=");
		builder.append(requiredBuildings);
		builder.append("]");
		return builder.toString();
	}
	
}
