/**
 * @author Joseph DeLong
 *
 */
package asc_dataTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
	private ArrayList<Player> allies;
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
			this.addAlly(Player.getInstance(Integer.parseInt(value)));
		} else if (fieldName.equalsIgnoreCase("score")) {
			this.setScore(Double.parseDouble(value));
		} else if (fieldName.equalsIgnoreCase("resourceSurplus")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("resource")) {
			// TODO: SOMETHING
			//this.setSurplusIndex(index, Integer.parseInt(value));
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
	 */
	public static Player getInstance(Integer instanceId) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> players = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();

		try {
			players = parser.parse("src/asc_dataTypes/players.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get Player Instance lookup", e);
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
		// TODO Auto-generated method stub
		return null;
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
	 * @return the active
	 */
	public boolean getActive() {
		return this.active;
	}

	/**
	 * @return the cities
	 */
	public ArrayList<City> getCities() {
		return this.cities;
	}

	/**
	 * @return the currentCity
	 */
	public City getCurrentCity() {
		return this.currentCity;
	}

	/**
	 * @return the allies
	 */
	public ArrayList<Player> getAllies() {
		return this.allies;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return this.score;
	}

	/**
	 * @return the resoruceSurplus
	 */
	public ArrayList<Resource> getResourceSurplus() {
		return this.resourceSurplus;
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
	public void setAllies(ArrayList<Player> allies) {
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
	 */
	private void addCity(Integer cityId) {
		ArrayList<City> temp = this.getCities();
		temp.add(City.getInstance(cityId));
		this.setCities(temp);
	}

	/**
	 * Adds an Ally to this Player's allies list
	 * 
	 * @param player
	 *            the Player to be added as an Ally
	 */
	private void addAlly(Player player) {
		ArrayList<Player> temp = this.getAllies();
		temp.add(player);
		this.setAllies(temp);
	}

	/**
	 * Adds the Integer values of the Resource.amount to this Player's resource
	 * surplus.
	 * 
	 * @param amount
	 *            ArrayList of Resource which will be added in a one-to-one
	 *            fashion to this Player's resource surplus.
	 * @return
	 */
	public ArrayList<Resource> addToSurplus(ArrayList<Resource> amount) {
		ArrayList<Resource> result = this.getResourceSurplus();
		if (this.getResourceSurplus() == null || this.getResourceSurplus().isEmpty()
				|| this.getResourceSurplus().size() == 0) {
			this.setResourceSurplus(amount);
		} else if (amount.size() != this.getResourceSurplus().size()) {
			// throw new SurplusManagementException("Add to Surplus: invalid ArrayList size.");
		} else {
			for (int i = 0; i < result.size(); i++) {
				Resource r = result.get(i);
				int temp = r.getAmount() + amount.get(i).getAmount();
				r.setAmount(temp);
				result.set(i, r);
			}
		}
		return result;
	}

	/* (non-Javadoc)
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
