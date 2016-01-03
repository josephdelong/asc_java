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

import exceptions.ASCException;
import exceptions.BuildingAtMaxGarrisonException;
import exceptions.DataSourceParseException;
import exceptions.InvalidBuildingProductionTypeException;
import exceptions.UnitNotFoundException;
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
	private ArrayList<Integer> resources;
	private double value;
	private double happiness;
	private double score;
	private File image;
	private ArrayList<Integer> assignedWork;

	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public City() {
		this.setId(0);
		this.setName("");
		this.setOwner(null);
		this.setBuildings(null);
		this.setOffense(0);
		this.setDefense(0);
		this.setPopulation(0);
		this.setResources(null);
		this.setValue(0);
		this.setHappiness(0);
		this.setScore(0);
		this.setImage(null);
		this.setAssignedWork(null);
	}

	/**
	 * Constructor which sets up a new City for a particular Player, with
	 * optional City name, Town Center name, Brute name, Assigned Work. If any
	 * of these optional values are null, they will be set to default values
	 * (e.g. "New City", etc.).
	 * 
	 * @param playerId
	 * @param cityName
	 * @param townCenterName
	 * @param bruteName
	 * @param assignedWork
	 * @throws DataSourceParseException 
	 * @throws UnitNotFoundException 
	 * @throws BuildingAtMaxGarrisonException 
	 * @throws InvalidBuildingProductionTypeException 
	 */
	public City(Integer playerId, String cityName, String townCenterName,
			String bruteName, ArrayList<Integer> assignedWork) throws DataSourceParseException, UnitNotFoundException, BuildingAtMaxGarrisonException, InvalidBuildingProductionTypeException {
		this.setId(0);
		if (cityName == null || cityName.equals(null) || cityName.isEmpty()
				|| cityName.equals("")) {
			this.setName("New City");
		} else {
			this.setName(cityName);
		}
		this.setOwner(Player.getInstance(playerId));
		// new Town Center
		ArrayList<Building> newBuildings = new ArrayList<Building>();
		Building b = new Building(1);
		if (townCenterName == null || townCenterName.equals(null)
				|| townCenterName.isEmpty() || townCenterName.equals("")) {
			// keep default Town Center name
		} else {
			b.setName(townCenterName);
		}
		b.setCity(this.getId());
		// new Brute
		Unit u = new Unit(1);
		if (bruteName == null || bruteName.equals(null) || bruteName.isEmpty()
				|| bruteName.equals("")) {
			// keep default Brute name
		} else {
			u.setName(bruteName);
		}
		b.addOccupant(u.getId());
		b.addGarrisonedUnit(u.getId());
		newBuildings.add(b);
		this.setBuildings(newBuildings);
		// calculate new City offense & defense
		Integer off = 0;
		Integer def = 0;
		Iterator<Building> it = newBuildings.iterator();
		while (it.hasNext()) {
			Building temp = it.next();
			off += temp.getOffense();
			def += temp.getDefense();
		}
		this.setOffense(off);
		this.setDefense(def);
		this.setPopulation(100);
		// default Resources
		this.addResource(1, 0);
		this.addResource(2, 0);
		this.addResource(3, 0);
		this.addResource(4, 0);
		this.addResource(5, 0);
		this.addResource(6, 0);
		this.addResource(7, 0);
		this.addResource(8, 100);
		this.addResource(9, 100);
		this.setValue(this.getResourceValue());
		this.setHappiness(this.getHappinessValue());
		this.setScore(this.getValue() + this.getHappiness() + this.getPopulation());
		this.setImage(null);
		ArrayList<Integer> work = new ArrayList<Integer>();
		if (assignedWork == null || assignedWork.equals(null)
				|| assignedWork.isEmpty() || assignedWork.size() == 0) {
			work.add(0); // farmers
			work.add(0); // miners
			work.add(0); // lumberjacks
			work.add(0); // merchants
			work.add(0); // civil defense
			work.add(100); // unemployed
			this.setAssignedWork(work);
		} else {
			this.setAssignedWork(assignedWork);
		}
	}

	/**
	 * Parse method which sets the data members of this class to values parsed
	 * from input
	 * @throws ASCException 
	 */
	@Override
	public void parse(String fieldName, String attribute, String value) throws ASCException {
		if (fieldName.equals(null) || fieldName.isEmpty()
				|| fieldName.equalsIgnoreCase("")) {
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
			this.addBuilding(Integer.parseInt(attribute),
					Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("offense")) {
			this.setOffense(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("defense")) {
			this.setDefense(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("population")) {
			this.setPopulation(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("resources")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("resource")) {
			this.addResource(Integer.parseInt(attribute),
					Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("value")) {
			this.setValue(Double.parseDouble(value));
		} else if (fieldName.equalsIgnoreCase("happiness")) {
			this.setHappiness(Double.parseDouble(value));
		} else if (fieldName.equalsIgnoreCase("score")) {
			this.setScore(Double.parseDouble(value));
		} else if (fieldName.equalsIgnoreCase("image")) {
			this.setImage(new File(value));
		} else if (fieldName.equalsIgnoreCase("assignedWork")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("work")) {
			this.addWork(Integer.parseInt(attribute), Integer.parseInt(value));
		}
	}

	/**
	 * Method which returns an instance of City based on a unique instance ID,
	 * if found. Otherwise returns null.
	 * 
	 * @param instanceId
	 *            The unique identifier for the instance of City you are looking
	 *            for.
	 * @return City associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public static City getInstance(int instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> cities = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();

		try {
			cities = parser.parse("src/datastore/citys.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get City instance lookup: " + instanceId, e);
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
	 * 
	 * @return ArrayList of Strings containing the names of this Data Type's
	 *         fields.
	 */
	public ArrayList<String> getFields() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("name");
		fields.add("owner");
		fields.add("buildings"); // buildings + buildingLocs
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
	 * Calculates this City's Happiness, based on a number of factors:
	 * Resources, Assigned Work, Population, Military Units in City, & more
	 * 
	 * @return
	 */
	public double getHappinessValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Calculates this City's total Resource value.
	 * 
	 * @return
	 */
	public double getResourceValue() {
		ArrayList<Integer> resources = this.getResources();
		Iterator<Integer> it = resources.iterator();
		double value = 0;
		int counter = 1;
		while (it.hasNext()) {
			Integer i = it.next();
			Resource r = Resource.getInstance(counter);
			value += r.getValue() * i;
			counter++;
		}
		return value;
	}

	/**
	 * @return the id
	 */
	@Override
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
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @return the buildings
	 */
	public ArrayList<Building> getBuildings() {
		return buildings;
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
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * @return the resources
	 */
	public ArrayList<Integer> getResources() {
		return resources;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @return the happiness
	 */
	public double getHappiness() {
		return happiness;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @return the image
	 */
	public File getImage() {
		return image;
	}

	/**
	 * @return the assignedWork
	 */
	public ArrayList<Integer> getAssignedWork() {
		return assignedWork;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @param buildings
	 *            the buildings to set
	 */
	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}

	/**
	 * @param offense
	 *            the offense to set
	 */
	public void setOffense(int offense) {
		this.offense = offense;
	}

	/**
	 * @param defense
	 *            the defense to set
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * @param population
	 *            the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * @param resources
	 *            the resources to set
	 */
	public void setResources(ArrayList<Integer> resources) {
		this.resources = resources;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @param happiness
	 *            the happiness to set
	 */
	public void setHappiness(double happiness) {
		this.happiness = happiness;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(File image) {
		this.image = image;
	}

	/**
	 * @param assignedWork
	 *            the assignedWork to set
	 */
	public void setAssignedWork(ArrayList<Integer> assignedWork) {
		this.assignedWork = assignedWork;
	}

	/**
	 * Method which adds the specified work type and percent to this City's
	 * assignedWork. Used when parsing data from the data source only.
	 * 
	 * @param workType
	 * @param percent
	 */
	private void addWork(int workType, int percent) {
		ArrayList<Integer> work = this.getAssignedWork();
		work.add(workType, percent);
		this.setAssignedWork(work);
	}

	/**
	 * Method which adds the specified resourceType with the specified amount to
	 * this instance's Resources. Used when parsing data from the data source
	 * only.
	 * 
	 * @param resourceType
	 * @param amount
	 */
	private void addResource(int resourceType, int amount) {
//		Resource r = Resource.getInstance(resourceType);
//		r.setAmount(amount);
		ArrayList<Integer> resources = this.getResources();
		if(resources == null) {
			resources = new ArrayList<Integer>();
			for(int i = 0; i < 9; i++) {
				resources.add(0);
			}
		}
		resources.set(resourceType - 1, amount); // Java indexes start at 0
		this.setResources(resources);
	}

	/**
	 * Method which adds a new Building to this City.buildings, with the
	 * specified BuildingType and Resource production.
	 * 
	 * @param buildingType
	 * @param productionValue
	 * @throws InvalidBuildingProductionTypeException 
	 */
	private void addBuilding(int buildingType, int productionValue) throws InvalidBuildingProductionTypeException {
		ArrayList<Building> buildings = this.getBuildings();
		Building newBuilding = new Building(buildingType);
		Integer i = Integer.valueOf(productionValue);
		if (i == null || i.equals(null) || i.intValue() == 0) {
			// do nothing
		} else {
			newBuilding.setProductionType(productionValue);
		}
		buildings.add(newBuilding);
		this.setBuildings(buildings);
	}

	/*
	 * (non-Javadoc)
	 * 
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
