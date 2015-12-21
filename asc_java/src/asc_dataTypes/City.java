/**
 * @author joseph_delong
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
public class City extends DataType {
	
	/**
	 * Private Data Members.
	 */
	private int id;
	private String name;
	private Player owner;
	private ArrayList<Building> buildings;
	private int offense;
	private int defense;
	private int population;
	private ArrayList<Resource> resources;
	private double value;
	private double happiness;
	private double score;
	private File image;
	private ArrayList<Integer> assignedWork;

	/**
	 * Parse method which sets the data members of this class to values parsed
	 * from input
	 */
	@Override
	public void parse(String fieldName, String value) {
		if (fieldName.equals(null) || fieldName.isEmpty() || fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("name")) {
			this.setName(value);
		} else if (fieldName.equalsIgnoreCase("owner")) {
			this.setOwner(Player.getInstance(Integer.parseInt(value)));
		} else if (fieldName.equalsIgnoreCase("buildings")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("building")) {
			// TODO: SOMETHING
		} else if (fieldName.equalsIgnoreCase("offense")) {
			this.setOffense(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("defense")) {
			this.setDefense(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("population")) {
			this.setPopulation(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("resources")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("resource")) {
			// TODO: SOMETHING
		} else if (fieldName.equalsIgnoreCase("value")) {
			this.setValue(Double.parseDouble(value));
		} else if (fieldName.equalsIgnoreCase("happiness")) {
			this.setHappiness(Double.parseDouble(value));
		} else if (fieldName.equalsIgnoreCase("score")) {
			this.setScore(Double.parseDouble(value));
		} else if (fieldName.equalsIgnoreCase("image")) {
			this.setImage(new File(value));
		} else if (fieldName.equalsIgnoreCase("assignedWork")) {
			// TODO: SOMETHING
		}
	}

	/**
	 * Method which returns an instance of City based on a unique instance ID,
	 * if found. Otherwise returns null.
	 * 
	 * @param instanceId
	 *            The unique identifier for the instance of City you are
	 *            looking for.
	 * @return City associated with instanceId, or null.
	 */
	public static City getInstance(int instanceId) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> cities = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();

		try {
			cities = parser.parse("src/asc_dataTypes/citys.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get City Instance lookup", e);
		}

		Iterator<DataType> it = cities.iterator();
		if (it.hasNext()) {
			return (City) it.next();
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
		fields.add("owner");
		fields.add("buildings"); // + buildingLocs
		fields.add("offense");
		fields.add("defense");
		fields.add("population");
		fields.add("resources"); // resourceList + resourceAmount
		fields.add("value");
		fields.add("happiness");
		fields.add("score");
		fields.add("image");
		fields.add("assignedWork");
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
	 * @return the owner
	 */
	public Player getOwner() {
		return this.owner;
	}

	/**
	 * @return the buildings
	 */
	public ArrayList<Building> getBuildings() {
		return this.buildings;
	}

	/**
	 * @return the offense
	 */
	public int getOffense() {
		return this.offense;
	}

	/**
	 * @return the defense
	 */
	public int getDefense() {
		return this.defense;
	}

	/**
	 * @return the population
	 */
	public int getPopulation() {
		return this.population;
	}

	/**
	 * @return the resources
	 */
	public ArrayList<Resource> getResources() {
		return this.resources;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * @return the happiness
	 */
	public double getHappiness() {
		return this.happiness;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return this.score;
	}

	/**
	 * @return the image
	 */
	public File getImage() {
		return this.image;
	}

	/**
	 * @return the assignedWork
	 */
	public ArrayList<Integer> getAssignedWork() {
		return this.assignedWork;
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
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @param buildings the buildings to set
	 */
	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
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
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @param happiness the happiness to set
	 */
	public void setHappiness(double happiness) {
		this.happiness = happiness;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(File image) {
		this.image = image;
	}

	/**
	 * @param assignedWork the assignedWork to set
	 */
	public void setAssignedWork(ArrayList<Integer> assignedWork) {
		this.assignedWork = assignedWork;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("City [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", buildings=");
		builder.append(buildings);
		builder.append(", offense=");
		builder.append(offense);
		builder.append(", defense=");
		builder.append(defense);
		builder.append(", population=");
		builder.append(population);
		builder.append(", resources=");
		builder.append(resources);
		builder.append(", value=");
		builder.append(value);
		builder.append(", happiness=");
		builder.append(happiness);
		builder.append(", score=");
		builder.append(score);
		builder.append(", image=");
		builder.append(image);
		builder.append(", assignedWork=");
		builder.append(assignedWork);
		builder.append("]");
		return builder.toString();
	}

}
