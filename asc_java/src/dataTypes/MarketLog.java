/**
 * 
 * @author Joseph DeLong
 * 
 */
package dataTypes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import util.XMLparser;
import exceptions.ASCException;
import exceptions.DataSourceParseException;
import exceptions.InvalidMarketLogResourceValueException;

/**
 *
 */
public class MarketLog extends DataType {

	/**
	 * Private Data Members
	 */
	private int id;
	private Date dateTime;
	private ArrayList<Integer> resourceTotals;
	private ArrayList<Float> resourceStdDevs;
	private Float marketHealth;
	
	/**
	 * Default constructor which initializes all fields to unusable defaults.
	 */
	public MarketLog() {
		this.setId(0);
		this.setDateTime(null);
		this.setResourceTotals(null);
		this.setResourceStdDevs(null);
		this.setMarketHealth(0f);
	}
	
	/**
	 * Method which sets up a MarketLog based on the last MarketLog index.
	 * @param getNextInstanceId boolean which determines whether we want to 
	 * set up a new instance of MarketLog, with id set to the last 
	 * MarketLog.id + 1.
	 */
	public MarketLog(boolean getNextInstanceId) {
		if(getNextInstanceId) {
			
		} else {
			new MarketLog();
		}
	}
	
	/**
	 * Parse method which sets the data members of this class to values parsed
	 * from input.
	 * @throws DataSourceParseException 
	 */
	@Override
	public void parse(String fieldName, String attribute, String value)
			throws ASCException {
		if (fieldName.equals(null) || fieldName.isEmpty()
				|| fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("dateTime")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setDateTime(sdf.parse(value));
			} catch (ParseException e) {
				throw new DataSourceParseException("Couldn't parse " + " into a valid SimpleDateFormat.", e);
			}
		} else if (fieldName.equalsIgnoreCase("resourceTotals")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("resourceTotal")) {
			this.setResourceTotalValue(Integer.parseInt(attribute), Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("resourceStdDevs")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("resourceStdDev")) {
			this.setResourceStdDevValue(Integer.parseInt(attribute), Float.parseFloat(value));
		}

	}

	/**
	 * Method which returns an instance of MarketLog based on a unique instance ID,
	 * if found. Otherwise returns null.
	 * 
	 * @param instanceId
	 *            The unique identifier for the instance of MarketLog you are
	 *            looking for.
	 * @return MarketLog associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public MarketLog getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> marketLogs = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();

		try {
			marketLogs = parser.parse("src/datastore/marketLogs.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get MarketLog instance lookup: " + instanceId, e);
		}

		Iterator<DataType> it = marketLogs.iterator();
		if (it.hasNext()) {
			return (MarketLog) it.next();
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the last instance of MarketLog.
	 * @return the last instance of MarketLog.
	 * @throws DataSourceParseException 
	 */
	public static MarketLog getLastInstance() throws DataSourceParseException {
		ArrayList<DataType> marketLogs = new ArrayList<DataType>();
		XMLparser parser = new XMLparser();
		try {
			marketLogs = parser.parse("src/datastore/marketLogs.xml", null, null);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get last instance of MarketLog.", e);
		}
		return (MarketLog) marketLogs.get(marketLogs.size() - 1);
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
		fields.add("dateTime");
		fields.add("resourceTotals");
		fields.add("resourceStdDevs");
		fields.add("m");
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
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @return the resourceTotals
	 */
	public ArrayList<Integer> getResourceTotals() {
		return resourceTotals;
	}

	/**
	 * @return the resourceStdDev
	 */
	public ArrayList<Float> getResourceStdDevs() {
		return resourceStdDevs;
	}

	/**
	 * @return the marketHealth
	 */
	public Float getMarketHealth() {
		return marketHealth;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @param resourceTotals the resourceTotals to set
	 */
	public void setResourceTotals(ArrayList<Integer> resourceTotals) {
		this.resourceTotals = resourceTotals;
	}

	/**
	 * Sets a particular index of this.resourceTotals to the specified value.
	 * @param index
	 * @param value
	 * @throws InvalidMarketLogResourceValueException 
	 */
	public void setResourceTotalValue(int index, int value) throws InvalidMarketLogResourceValueException {
		if(index == (Integer) null || index < 1 || index > 9) {
			throw new InvalidMarketLogResourceValueException("Cannot set index " + index + " of Resource Totals to the value of " + value + ".");
		}
		ArrayList<Integer> temp = this.getResourceTotals();
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			// set up ArrayList to insert this new value at the proper index
			temp = new ArrayList<Integer>();
			for(int i = 0; i > 9; i++) {
				temp.add(0);
			}
		}
		temp.set(index - 1, value); // arrays start at index 0, yo!
		this.setResourceTotals(temp);
	}

	/**
	 * @param resourceStdDev the resourceStdDev to set
	 */
	public void setResourceStdDevs(ArrayList<Float> resourceStdDevs) {
		this.resourceStdDevs = resourceStdDevs;
	}
	
	/**
	 * Sets a particular index of this.resourceStdDevs to the specified value.
	 * @param index
	 * @param value
	 * @throws InvalidMarketLogResourceValueException 
	 */
	public void setResourceStdDevValue(int index, float value) throws InvalidMarketLogResourceValueException {
		if(index == (Integer) null || index < 1 || index > 9) {
			throw new InvalidMarketLogResourceValueException("Cannot set index " + index + " of Resource Standard Deviations to the value of " + value + ".");
		}
		ArrayList<Float> temp = this.getResourceStdDevs();
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			// set up ArrayList to insert this new value at the proper index
			temp = new ArrayList<Float>();
			for(int i = 0; i > 9; i++) {
				temp.add(0f);
			}
		}
		temp.set(index - 1, value); // arrays start at index 0, yo!
		this.setResourceStdDevs(temp);
	}

	/**
	 * @param marketHealth the marketHealth to set
	 */
	public void setMarketHealth(Float marketHealth) {
		this.marketHealth = marketHealth;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MarketLog:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("dateTime=");
		builder.append(dateTime);
		builder.append("\n\t");
		builder.append("resourceTotals=");
		builder.append(resourceTotals);
		builder.append("\n\t");
		builder.append("resourceStdDevs=");
		builder.append(resourceStdDevs);
		builder.append("\n\t");
		builder.append("marketHealth=");
		builder.append(marketHealth);
		return builder.toString();
	}

}
