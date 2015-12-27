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
public class Unit extends DataType {

	/**
	 * Private Data Members
	 */
	private int id;
	private int unitId;
	private String name;
	private Player player;
	private City city;
	private int location;
	private int offense;
	private int defense;
	private int upgrade;
	private int xp;
	private boolean special;
	private File image;
	private ArrayList<Building> requiredBuildings;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Unit() {
		this.setId(0);
		this.setUnitId(0);
		this.setName("");
		this.setPlayer(null);
		this.setCity(null);
		this.setLocation(0);
		this.setOffense(0);
		this.setDefense(0);
		this.setUpgrade(0);
		this.setXp(0);
		this.setSpecial(false);
		this.setImage(null);
		this.setRequiredBuildings(new ArrayList<Building>());
	}
	
	/**
	 * Constructor which sets a new Unit's details based on its Unit Type
	 * @param unitTypeId <code>int</code> representing the ASC UnitType of this Unit.
	 */
	public Unit(int unitTypeId) {
		UnitType unitType = new UnitType(unitTypeId);
		this.setUnitId(unitType.getId());
		this.setName(unitType.getName());
		this.setOffense(unitType.getOffense());
		this.setDefense(unitType.getDefense());
		this.setSpecial(unitType.getSpecial());
		this.setImage(unitType.getImage());
		this.setRequiredBuildings(unitType.getRequiredBuildings());
	}

	/**
	 * Parse method which sets the data members of this class to values parsed from input
	 */
	@Override
	public void parse(String fieldName, String attribute, String value) {
		if(fieldName.equals(null) || fieldName.isEmpty() || fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("name")) {
			this.setName(value);
		} else if(fieldName.equalsIgnoreCase("player")) {
			this.setPlayer(Player.getInstance(Integer.parseInt(value)));
		} else if(fieldName.equalsIgnoreCase("city")) {
			this.setCity(City.getInstance(Integer.parseInt(value)));
		} else if(fieldName.equalsIgnoreCase("location")) {
			this.setLocation(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("offense")) {
			this.setOffense(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("defense")) {
			this.setDefense(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("upgrade")) {
			this.setUpgrade(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("xp")) {
			this.setXp(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("special")) {
			this.setSpecial(Boolean.parseBoolean(value));
		} else if(fieldName.equalsIgnoreCase("image")) {
			this.setImage(new File(value));
		} else if(fieldName.equalsIgnoreCase("requiredBuildings")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("requiredBuilding")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				if(attribute == null || attribute.equals(null) || attribute.isEmpty() || attribute.equalsIgnoreCase("")) {
					this.addRequiredBuilding(Integer.valueOf(s));
				} else {
					this.addRequiredBuilding(Integer.valueOf(s), Integer.valueOf(attribute.trim()));
				}
			}
		}
	}

	/**
	 * Method which returns an instance of Unit based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of Unit you are looking for.
	 * @return Unit associated with instanceId, or null.
	 */
	public static Unit getInstance(Integer instanceId) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> units = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			units = parser.parse("src/asc_dataTypes/units.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get Unit Instance lookup", e);
		}
		
		Iterator<DataType> it = units.iterator();
		if(it.hasNext()) {
			return (Unit)it.next();
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
		fields.add("unitId");
		fields.add("name");
		fields.add("player");
		fields.add("city");
		fields.add("location");
		fields.add("offense");
		fields.add("defense");
		fields.add("upgrade");
		fields.add("xp");
		fields.add("special");
		fields.add("image");
		fields.add("requiredBuildings");
		return fields;
	}
	
	/**
	 * Method which sets this instance's type-specific fields based on input.
	 */
	public void setType(String type) {
		// do nothing
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
	public String getName() {
		return name;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
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
	 * @param unitId the unitId to set
	 */
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
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
	public void setRequiredBuildings(ArrayList<Building> requiredBuildings) {
		this.requiredBuildings = requiredBuildings;
	}
	
	/**
	 * Adds a Required Building to this Unit
	 * @param buildingTypeId
	 */
	private void addRequiredBuilding(Integer buildingTypeId) {
		ArrayList<Building> temp = this.getRequiredBuildings();
		temp.add(new Building(buildingTypeId));
		this.setRequiredBuildings(temp);
	}
	
	/**
	 * Adds a Required Building to this Unit
	 * @param buildingTypeId
	 * @param buildingSubType
	 */
	private void addRequiredBuilding(Integer buildingTypeId, Integer buildingSubType) {
		ArrayList<Building> temp = this.getRequiredBuildings();
		Building newBuilding = new Building(buildingTypeId);
		newBuilding.setProductionType(buildingSubType);
		temp.add(newBuilding);
		this.setRequiredBuildings(temp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Unit: [id=");
		builder.append(id);
		builder.append(", unitId=");
		builder.append(unitId);
		builder.append(", unitName=");
		builder.append(name);
		builder.append(", player=");
		builder.append(player);
		builder.append(", city=");
		builder.append(city);
		builder.append(", location=");
		builder.append(location);
		builder.append(", offense=");
		builder.append(offense);
		builder.append(", defense=");
		builder.append(defense);
		builder.append(", upgrade=");
		builder.append(upgrade);
		builder.append(", xp=");
		builder.append(xp);
		builder.append(", special=");
		builder.append(special);
		builder.append(", image=");
		builder.append(image);
		builder.append(", requiredBuildings=");
		builder.append(requiredBuildings);
		builder.append("]");
		return builder.toString();
	}
}
