/**
 * @author Joseph DeLong
 *
 */
package dataTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import exceptions.ASCException;
import exceptions.DataSourceParseException;
import exceptions.SurplusManagementException;
import util.XMLparser;

/**
*
*/
public class Player extends DataType {

	/**
	 * Private data members
	 */
	private int id;
	private String name;
	private boolean active;
	private ArrayList<City> cities;
	private City currentCity;
	private ArrayList<Integer> allies;
	private double score;
	private ArrayList<Resource> resourceSurplus;

	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Player() {
		this.setId(0);
		this.setName("");
		this.setActive(false);
		this.setCities(new ArrayList<City>());
		this.setCurrentCity(null);
		this.setScore(0);
		this.setResourceSurplus(new ArrayList<Resource>());
	}

	/**
	 * Constructor which sets up a new Player instance. Note: input fields
	 * expected to not be null.
	 * 
	 * @param playerId
	 * @param name
	 */
	public Player(Integer playerId, String name) {
		new Player();
		this.setActive(true);
		this.setId(playerId);
		this.setName(name);
	}

	/**
	 * Parse method which sets the data members of this class to values parsed
	 * from input
	 * @throws DataSourceParseException 
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
		} else if (fieldName.equalsIgnoreCase("active")) {
			this.setActive(Boolean.parseBoolean(value));
		} else if (fieldName.equalsIgnoreCase("cities")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("city")) {
			this.addCity(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("currentCity")) {
			this.setCurrentCity(City.getInstance(Integer.parseInt(value)));
		} else if (fieldName.equalsIgnoreCase("allies")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("ally")) {
			this.addAlly(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("score")) {
			this.setScore(Double.parseDouble(value));
		} else if (fieldName.equalsIgnoreCase("resourceSurplus")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("resource")) {
			this.addResource(Integer.parseInt(attribute),
					Integer.parseInt(value));
		}
	}

	/**
	 * Method which returns an instance of Player based on a unique instance ID,
	 * if found. Otherwise returns null.
	 * 
	 * @param instanceId
	 *            The unique identifier for the instance of Player you are
	 *            looking for.
	 * @return Player associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public static Player getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> players = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();

		try {
			players = parser.parse("src/datastore/players.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get Player instance lookup: " + instanceId, e);
		}

		Iterator<DataType> it = players.iterator();
		if (it.hasNext()) {
			return (Player) it.next();
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
	@Override
	public ArrayList<String> getFields() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("name");
		fields.add("active");
		fields.add("cities");
		fields.add("currentCity");
		fields.add("allies");
		fields.add("score");
		fields.add("resourceSurplus");
		return fields;
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
	 * @return the active
	 */
	public boolean getActive() {
		return active;
	}

	/**
	 * @return the cities
	 */
	public ArrayList<City> getCities() {
		return cities;
	}

	/**
	 * @return the currentCity
	 */
	public City getCurrentCity() {
		return currentCity;
	}

	/**
	 * @return the allies
	 */
	public ArrayList<Integer> getAllies() {
		return allies;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @return the resoruceSurplus
	 */
	public ArrayList<Resource> getResourceSurplus() {
		return resourceSurplus;
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
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @param cities
	 *            the cities to set
	 */
	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	/**
	 * @param currentCity
	 *            the currentCity to set
	 */
	public void setCurrentCity(City currentCity) {
		this.currentCity = currentCity;
	}

	/**
	 * @param allies
	 *            the allies to set
	 */
	public void setAllies(ArrayList<Integer> allies) {
		this.allies = allies;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @param resoruceSurplus
	 *            the resoruceSurplus to set
	 */
	public void setResourceSurplus(ArrayList<Resource> resourceSurplus) {
		this.resourceSurplus = resourceSurplus;
	}

	/**
	 * Adds a City to this Player's cities list
	 * 
	 * @param cityId
	 *            the ID of the City to be added to this Player's cities
	 * @throws DataSourceParseException 
	 */
	private void addCity(Integer cityId) throws DataSourceParseException {
		ArrayList<City> temp = this.getCities();
		temp.add(City.getInstance(cityId));
		this.setCities(temp);
	}

	/**
	 * Adds an Ally to this Player's allies list.
	 * <p>
	 * Note: In order to avoid infinite recursion, this method was changed from
	 * addAlly(Player) to addAlly(Integer). Otherwise, Players which are Allies
	 * would be infinitely added to each other's Allies ArrayList.
	 * 
	 * @param playerId
	 *            the Id of the Player to be added as an Ally
	 */
	private void addAlly(Integer playerId) {
		ArrayList<Integer> temp = this.getAllies();
		temp.add(playerId);
		this.setAllies(temp);
	}

	/**
	 * Adds the Integer values of the Resource.amount to this Player's resource
	 * surplus.
	 * 
	 * @param values
	 *            ArrayList of Resource which will be added in a one-to-one
	 *            fashion to this Player's resource surplus.
	 * @return
	 * @throws SurplusManagementException 
	 */
	public ArrayList<Resource> addToSurplus(ArrayList<Resource> values) throws SurplusManagementException {
		ArrayList<Resource> result = this.getResourceSurplus();
		if (result == null || result.isEmpty() || result.size() == 0) {
			this.setResourceSurplus(values);
		} else if (values == null || values.size() != result.size()) {
			throw new SurplusManagementException("Add to Surplus: invalid ArrayList size.");
		} else {
			for (int i = 0; i < result.size(); i++) {
				Resource r = result.get(i);
				int temp = r.getAmount() + values.get(i).getAmount();
				r.setAmount(temp);
				result.set(i, r);
			}
		}
		return result;
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
		ArrayList<Resource> resources = this.getResourceSurplus();
		resources.add(resourceType, new Resource(resourceType, amount));
		this.setResourceSurplus(resources);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", active=");
		builder.append(active);
		builder.append(", cities=");
		builder.append(cities);
		builder.append(", currentCity=");
		builder.append(currentCity);
		builder.append(", allies=");
		builder.append(allies);
		builder.append(", score=");
		builder.append(score);
		builder.append(", resourceSurplus=");
		builder.append(resourceSurplus);
		builder.append("]");
		return builder.toString();
	}

}
