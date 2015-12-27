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
public class Building extends DataType {

	/**
	 * Private data members
	 */
	private int id;
	private int buildingType;
	private String name;
	private City city;
	private int location;
	private int defense;
	private int offense;
	private int upgrade;
	private boolean special;
	private File image;
	private ArrayList<Unit> occupants;
	private ArrayList<Unit> garrisonedUnits;
	private int productionType;
	private ArrayList<Building> requiredBuildings;

	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Building() {
		this.setId(0);
		this.setBuildingType(0);
		this.setName("");
		this.setCity(null);
		this.setLocation(0);
		this.setDefense(0);
		this.setOffense(0);
		this.setUpgrade(0);
		this.setSpecial(false);
		this.setImage(null);
		this.setOccupants(new ArrayList<Unit>());
		this.setGarrisonedUnits(new ArrayList<Unit>());
		this.setProductionType(0);
		this.setRequiredBuildings(new ArrayList<Building>());
	}

	/**
	 * Constructor which sets a new Building's details based on its Building Type.
	 * @param buildingTypeId <code>int</code> representing the ASC BuildingType of this Building.
	 */
	public Building(int buildingTypeId) {
		BuildingType buildingType = BuildingType.getInstance(buildingTypeId);
		if(buildingType == null) {
			new Building();
		} else {
			this.setBuildingType(buildingType.getId());
			this.setName(buildingType.getName());
			this.setDefense(buildingType.getDefense());
			this.setOffense(buildingType.getDefense());
			this.setSpecial(buildingType.getSpecial());
			this.setImage(buildingType.getImage());
			this.setRequiredBuildings(buildingType.getRequiredBuildings());
		}
	}
	
	/**
	 * Constructor which sets a new Building's details based on its Building Type and Production Type.
	 * @param buildingTypeId
	 * @param productionType
	 */
	public Building(int buildingTypeId, int productionType) {
		new Building(buildingTypeId);
		setProductionType(productionType);
	}

	/**
	 * Parse method which sets the data members of this class to values parsed from input
	 */
	@Override
	public void parse(String fieldName, String attribute, String value) {
		if(fieldName == null || fieldName.equals(null) || fieldName.isEmpty() || fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("name")) {
			this.setName(value);
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
		} else if(fieldName.equalsIgnoreCase("special")) {
			this.setSpecial(Boolean.parseBoolean(value));
		} else if(fieldName.equalsIgnoreCase("image")) {
			this.setImage(new File(value));
		} else if(fieldName.equalsIgnoreCase("occupants")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("occupant")) {
			this.addOccupant(Integer.valueOf(value));
		} else if(fieldName.equalsIgnoreCase("garrisonedUnits")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("garrisonedUnit")) {
			this.addGarrisonedUnit(Integer.valueOf(value));
		} else if(fieldName.equalsIgnoreCase("productionType")) {
			this.setProductionType(Integer.parseInt(value));
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
	 * Method which returns an instance of Building based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of Building you are looking for.
	 * @return Building associated with instanceId, or null.
	 */
	public static Building getInstance(Integer instanceId) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> buildings = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			buildings = parser.parse("src/asc_dataTypes/buildings.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get Building Instance lookup", e);
		}
		
		Iterator<DataType> it = buildings.iterator();
		if(it.hasNext()) {
			return (Building)it.next();
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
		fields.add("buildingType");
		fields.add("name");
		fields.add("city");
		fields.add("location");
		fields.add("offense");
		fields.add("defense");
		fields.add("upgrade");
		fields.add("special");
		fields.add("image");
		fields.add("occupants");
		fields.add("garrisonedUnits");
		fields.add("productionType");
		fields.add("requiredBuildings");
		return fields;
	}

	/**
	 * Method which sets this instance's type-specific fields based on input.
	 */
	public void setType(String type) {
		int i = Integer.parseInt(type);
		if(i < 1 || i > 3) {
			//throw new InvalidBuildingProductionTypeException("Cannot set a Building's productionType to " + i + ". Valid range is 1-3.");
		} else {
			this.setProductionType(i);
		}
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
	 * @return the occupants
	 */
	public ArrayList<Unit> getOccupants() {
		return occupants;
	}

	/**
	 * @return the garrisonedUnits
	 */
	public ArrayList<Unit> getGarrisonedUnits() {
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
	public void setOccupants(ArrayList<Unit> occupants) {
		this.occupants = occupants;
	}

	/**
	 * @param garrisonedUnits the garrisonedUnits to set
	 */
	public void setGarrisonedUnits(ArrayList<Unit> garrisonedUnits) {
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
	public void setRequiredBuildings(ArrayList<Building> requiredBuildings) {
		this.requiredBuildings = requiredBuildings;
	}
	
	/**
	 * Adds a Required Building to this Building
	 * @param buildingTypeId
	 */
	private void addRequiredBuilding(Integer buildingTypeId) {
		ArrayList<Building> temp = this.getRequiredBuildings();
		temp.add(new Building(buildingType));
		this.setRequiredBuildings(temp);
	}

	/**
	 * Adds the specified Unit ID to this Building's Garrison, provided there is sufficient room.
	 *   Otherwise, will throw BuildingAtMaxGarrisonException
	 * @param unitId
	 */
	private void addGarrisonedUnit(Integer unitId) {
		// Parse data source for Unit by unitId
		if(Unit.getInstance(unitId) == null) {
			// throw new UnitNotFoundException
			return;
		}
		
		ArrayList<Unit> temp = this.getGarrisonedUnits();
		BuildingType tempBT = BuildingType.getInstance(this.getBuildingType());
		
		if(tempBT.getMaxGarrison() > temp.size()) {
			temp.add(Unit.getInstance(unitId));
			this.setGarrisonedUnits(temp);
		} else {
			// throw new BuildingAtMaxGarrisonException();
		}
	}

	/**
	 * Adds the specified Unit ID to this Building's Occupants ArrayList.
	 * @param unitId
	 */
	private void addOccupant(Integer unitId) {
		// Parse data source for Unit by unitId
		if(Unit.getInstance(unitId) == null) {
			// throw new UnitNotFoundException
			return;
		}
		ArrayList<Unit> temp = this.getOccupants();
		temp.add(Unit.getInstance(unitId));
		this.setOccupants(temp);
	}
	
	/**
	 * Removes the specified Unit from this Building's Garrison, if present.
	 *   Otherwise throws UnitNotFoundInBuildingException
	 * @param unitId
	 */
	private void removeGarrisonedUnit(Integer unitId) {
		ArrayList<Unit> temp = this.getGarrisonedUnits();
		if(temp.contains(Unit.getInstance(unitId))) {
			temp.remove(Unit.getInstance(unitId));
			this.setGarrisonedUnits(temp);
		} else {
			// throw new UnitNotFoundInBuildingException
		}
	}
	
	/**
	 * Remomves the specified Unit from this Building's Occupants, if present.
	 *   Otherwise throws UnitNotFoundInBuildingException
	 * @param unitId
	 */
	private void removeOccupant(Integer unitId) {
		ArrayList<Unit> temp = this.getGarrisonedUnits();
		if(temp.contains(Unit.getInstance(unitId))) {
			temp.remove(Unit.getInstance(unitId));
			this.setOccupants(temp);
		} else {
			// throw new UnitNotFoundInBuildingException
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Building:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("buildingType=");
		builder.append(buildingType);
		builder.append("\n\t");
		builder.append("name=");
		builder.append(name);
		builder.append("\n\t");
		builder.append("city=");
		builder.append(city);
		builder.append("\n\t");
		builder.append("location=");
		builder.append(location);
		builder.append("\n\t");
		builder.append("defense=");
		builder.append(defense);
		builder.append("\n\t");
		builder.append("offense=");
		builder.append(offense);
		builder.append("\n\t");
		builder.append("upgrade=");
		builder.append(upgrade);
		builder.append("\n\t");
		builder.append("special=");
		builder.append(special);
		builder.append("\n\t");
		builder.append("image=");
		builder.append(image);
		builder.append("\n\t");
		builder.append("occupants=");
		builder.append(occupants);
		builder.append("\n\t");
		builder.append("garrisonedUnits=");
		builder.append(garrisonedUnits);
		builder.append("\n\t");
		builder.append("productionType=");
		builder.append(productionType);
		builder.append("\n\t");
		builder.append("requiredBuildings=");
		ArrayList<Building> buildings = this.getRequiredBuildings();
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
		return builder.toString();
	}

}
