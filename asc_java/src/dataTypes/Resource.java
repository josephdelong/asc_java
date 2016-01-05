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
public class Resource extends DataType {
	
	/**
	 * Private Data Members
	 */
	private int id;
	private String name;
	private double value;
	private File image;
	private float baseAccumulationRate;
	private ArrayList<Building> requiredBuildings;
	private ArrayList<Building> producingBuildings;
	private int amount;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Resource() {
		this.setId(0);
		this.setName("");
		this.setValue(0);
		this.setImage(null);
		this.setBaseAccumulationRate(0);
		this.setRequiredBuildings(new ArrayList<Building>());
		this.setProducingBuildings(new ArrayList<Building>());
		this.setAmount(0);
	}
	
	/**
	 * Constructor which sets a Resource's details based on its Resource ID.
	 * @param resourceId <code>int</code> representing the ID of this Resource.
	 */
	public Resource(int resourceId) {
		// Parse data source for values
		Resource.getInstance(resourceId);
	}
	
	/**
	 * Constructor which sets a Resource's details based on its Resource ID and amount.
	 * @param resourceId
	 * @param amount
	 */
	public Resource(int resourceId, int amount) {
		new Resource(resourceId);
		setAmount(amount);
	}

	/**
	 * Parse method which sets the data members of this class to values parsed from input
	 * @throws InvalidBuildingProductionTypeException 
	 * @throws NumberFormatException 
	 */
	@Override
	public void parse(String fieldName, String attribute, String value) throws ASCException {
		if(fieldName == null || fieldName.equals(null) || fieldName.isEmpty() || fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("name")) {
			this.setName(value);
		} else if(fieldName.equalsIgnoreCase("value")) {
			this.setValue(Double.parseDouble(value));
		} else if(fieldName.equalsIgnoreCase("image")) {
			this.setImage(new File(value));
		} else if(fieldName.equalsIgnoreCase("baseAccumulationRate")) {
			this.setBaseAccumulationRate(Float.parseFloat(value));
		} else if(fieldName.equalsIgnoreCase("requiredBuildings")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("requiredBuilding")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				if(attribute == null || attribute.equals(null) || attribute.isEmpty() || attribute.equalsIgnoreCase("")) {
					this.addRequiredBuilding(Integer.valueOf(value));
				} else {
					this.addRequiredBuilding(Integer.valueOf(s), Integer.valueOf(attribute.trim()));
				}
			}
		} else if(fieldName.equalsIgnoreCase("producingBuildings")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("producingBuilding")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				if(attribute == null || attribute.equals(null) || attribute.isEmpty() || attribute.equalsIgnoreCase("")) {
					this.addProducingBuilding(Integer.parseInt(s));
				} else {
					this.addProducingBuilding(Integer.parseInt(s), Integer.valueOf(attribute.trim()));
				}
			}
		} else if(fieldName.equalsIgnoreCase("amount")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				this.setAmount(Integer.parseInt(s));
			}
		}
	}

	/**
	 * Method which returns an instance of Resource based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of Resource you are looking for.
	 * @return Resource associated with instanceId, or null.
	 */
	public static Resource getInstance(Integer instanceId) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> resources = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			resources = parser.parse("src/datastore/resources.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get Resource instance lookup: " + instanceId, e);
			System.exit(1);
		}
		
		Iterator<DataType> it = resources.iterator();
		if(it.hasNext()) {
			return (Resource)it.next();
		} else {
			return null;
		}
	}
	
	/**
	 * Method which returns this Data Type's fields.
	 * @return ArrayList of Strings containing the names of this Data Type's fields. 
	 */
	@Override
	public ArrayList<String> getFields() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("name");
		fields.add("value");
		fields.add("image");
		fields.add("baseAccumulationRate");
		fields.add("requiredBuildings");
		fields.add("producingBuildings");
		fields.add("amount");
		return fields;
	}
	
	/**
	 * @return the id
	 */
	@Override
	public int getId() {
		return this.id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * @return the image
	 */
	public File getImage() {
		return this.image;
	}

	/**
	 * @return the baseAccumulationRate
	 */
	public float getBaseAccumulationRate() {
		return this.baseAccumulationRate;
	}

	/**
	 * @return the requiredBuildings
	 */
	public ArrayList<Building> getRequiredBuildings() {
		return this.requiredBuildings;
	}

	/**
	 * @return the producingBuildings
	 */
	public ArrayList<Building> getProducingBuildings() {
		return this.producingBuildings;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return this.amount;
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
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(File image) {
		this.image = image;
	}

	/**
	 * @param baseAccumulationRate the baseAccumulationRate to set
	 */
	public void setBaseAccumulationRate(float baseAccumulationRate) {
		this.baseAccumulationRate = baseAccumulationRate;
	}

	/**
	 * @param requiredBuildings the requiredBuildings to set
	 */
	public void setRequiredBuildings(ArrayList<Building> requiredBuildings) {
		this.requiredBuildings = requiredBuildings;
	}

	/**
	 * @param producingBuildings the producingBuildings to set
	 */
	public void setProducingBuildings(ArrayList<Building> producingBuildings) {
		this.producingBuildings = producingBuildings;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	/**
	 * Adds a Required Building to this Resource
	 * @param buildingTypeId
	 * @throws InvalidBuildingProductionTypeException 
	 */
	private void addRequiredBuilding(Integer buildingTypeId) throws InvalidBuildingProductionTypeException {
		ArrayList<Building> temp = this.getRequiredBuildings();
		temp.add(new Building(buildingTypeId));
		this.setRequiredBuildings(temp);
	}
	
	/**
	 * Adds a Required Building to this Resource
	 * @param buildingTypeId
	 * @param buildingSubType
	 * @throws InvalidBuildingProductionTypeException 
	 */
	private void addRequiredBuilding(Integer buildingTypeId, Integer buildingSubType) throws InvalidBuildingProductionTypeException {
		ArrayList<Building> temp = this.getRequiredBuildings();
		Building newBuilding = new Building(buildingTypeId);
		newBuilding.setProductionType(buildingSubType);
		temp.add(newBuilding);
		this.setRequiredBuildings(temp);
	}
	
	/**
	 * Adds a Producing Building to this Resource
	 * @param buildingTypeId
	 * @throws InvalidBuildingProductionTypeException 
	 */
	private void addProducingBuilding(Integer buildingTypeId) throws InvalidBuildingProductionTypeException {
		ArrayList<Building> temp = this.getProducingBuildings();
		temp.add(new Building(buildingTypeId));
		this.setProducingBuildings(temp);
	}
	
	/**
	 * Adds a Producing Building to this Resource
	 * @param buildingTypeId
	 * @param buildingSubType
	 * @throws InvalidBuildingProductionTypeException 
	 */
	private void addProducingBuilding(Integer buildingTypeId, Integer buildingSubType) throws InvalidBuildingProductionTypeException {
		ArrayList<Building> temp = this.getProducingBuildings();
		Building newBuilding = new Building(buildingTypeId);
		newBuilding.setProductionType(buildingSubType);
		temp.add(newBuilding);
		this.setProducingBuildings(temp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resource:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("name=");
		builder.append(name);
		builder.append("\n\t");
		builder.append("value=");
		builder.append(value);
		builder.append("\n\t");
		builder.append("image=");
		builder.append(image);
		builder.append("\n\t");
		builder.append("baseAccumulationRate=");
		builder.append(baseAccumulationRate);
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
		builder.append("\n\t");
		builder.append("producingBuildings:");
		buildings = this.getProducingBuildings();
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
		builder.append("\n\t");
		builder.append("amount=");
		builder.append(amount);
		return builder.toString();
	}

}
