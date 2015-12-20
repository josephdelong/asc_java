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
public class Resource extends DataType {
	
	/**
	 * Private Data Members
	 */
	private int id;
	private String name;
	private double value;
	private File image;
	private float baseAccumulationRate;
	private ArrayList<Integer> requiredBuildings;
	private ArrayList<Integer> producingBuildings;
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
		this.setRequiredBuildings(new ArrayList<Integer>());
		this.setProducingBuildings(new ArrayList<Integer>());
		this.setAmount(0);
	}
	
	/**
	 * Constructor which clones the passed in Resource.
	 * @param r The Resource to clone.
	 */
	public Resource(Resource r) {
		this.setId(r.getId());
		this.setName(r.getName());
		this.setValue(r.getValue());
		this.setImage(r.getImage());
		this.setBaseAccumulationRate(r.getBaseAccumulationRate());
		this.setRequiredBuildings(r.getRequiredBuildings());
		this.setProducingBuildings(r.getProducingBuildings());
		this.setAmount(r.getAmount());
	}
	
	/**
	 * Constructor which sets a Resource's details based on its Resource ID.
	 * @param resourceId <code>int</code> representing the ID of this Resource.
	 */
	public Resource(int resourceId) {
		// Parse data source for values
		Resource temp = Resource.getInstance(resourceId);
		if(temp.equals(null)) {
			new Resource();
		} else {
			new Resource(temp);
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
		} else if(fieldName.equalsIgnoreCase("value")) {
			this.setValue(Double.parseDouble(value));
		} else if(fieldName.equalsIgnoreCase("image")) {
			this.setImage(new File(value));
		} else if(fieldName.equalsIgnoreCase("baseAccumulationRate")) {
			this.setBaseAccumulationRate(Float.parseFloat(value));
		} else if(fieldName.equalsIgnoreCase("requiredBuildings")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("requiredBuilding")) {
			this.addRequiredBuilding(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("producingBuildings")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("producingBuilding")) {
			this.addProducingBuilding(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("amount")) {
			this.setAmount(Integer.parseInt(value));
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
		ArrayList<DataType> units = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			units = parser.parse("src/asc_dataTypes/resources.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get Resource Instance lookup", e);
		}
		
		Iterator<DataType> it = units.iterator();
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
	public ArrayList<Integer> getRequiredBuildings() {
		return this.requiredBuildings;
	}

	/**
	 * @return the producingBuildings
	 */
	public ArrayList<Integer> getProducingBuildings() {
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
	public void setRequiredBuildings(ArrayList<Integer> requiredBuildings) {
		this.requiredBuildings = requiredBuildings;
	}

	/**
	 * @param producingBuildings the producingBuildings to set
	 */
	public void setProducingBuildings(ArrayList<Integer> producingBuildings) {
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
	 */
	private void addRequiredBuilding(Integer buildingTypeId) {
		ArrayList<Integer> temp = this.getRequiredBuildings();
		temp.add(buildingTypeId);
		this.setRequiredBuildings(temp);
	}
	
	/**
	 * Adds a Producing Building to this Resource
	 * @param buildingTypeId
	 */
	private void addProducingBuilding(Integer buildingTypeId) {
		ArrayList<Integer> temp = this.getProducingBuildings();
		temp.add(buildingTypeId);
		this.setProducingBuildings(temp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resource [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", value=");
		builder.append(value);
		builder.append(", image=");
		builder.append(image);
		builder.append(", baseAccumulationRate=");
		builder.append(baseAccumulationRate);
		builder.append(", requiredBuildings=");
		builder.append(requiredBuildings);
		builder.append(", producingBuildings=");
		builder.append(producingBuildings);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}

}
