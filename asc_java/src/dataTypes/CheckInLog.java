/**
 * @author Joseph DeLong
 * 
 */
package dataTypes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import util.XMLparser;
import exceptions.ASCException;
import exceptions.DataSourceParseException;
import exceptions.InvalidCheckInLogRemainderException;

/**
 * 
 */
public class CheckInLog extends DataType {

	/**
	 * Private Data Members
	 */
	private int id;
	private int playerId;
	private Date checkInDateTime;
	private int cityId;
	private ArrayList<Float> remainders;

	/**
	 * Default constructor which initializes all fields to unusable defaults.
	 */
	public CheckInLog() {
		this.setId(0);
		this.setPlayerId(0);
		this.setCheckInDateTime(null);
		this.setCityId(0);
		this.setRemainders(null);
	}
	
	/**
	 * Constructor which sets up a ChekInLog for a particular Player and City.
	 * @param playerId
	 * @param cityId
	 */
	public CheckInLog(Integer playerId, Integer cityId) {
		CheckInLog lastCheckIn = null;
		try {
			lastCheckIn = CheckInLog.getLastInstance(playerId, cityId);
		} catch (DataSourceParseException e) {
			// do something?
		}
		this.setId(lastCheckIn.getId() + 1);
		this.setPlayerId(playerId);
		this.setCheckInDateTime(Date.from(Instant.now()));
		this.setCityId(cityId);
		this.setRemainders(null);
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
		} else if (fieldName.equalsIgnoreCase("playerId")) {
			this.setPlayerId(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("checkInDateTime")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setCheckInDateTime(sdf.parse(value));
			} catch (ParseException e) {
				throw new DataSourceParseException("Couldn't parse " + " into a valid SimpleDateFormat.", e);
			}
		} else if (fieldName.equalsIgnoreCase("cityId")) {
			this.setCityId(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("remainders")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("remainder")) {
			this.setRemainderValue(Integer.parseInt(attribute), Float.parseFloat(value));
		}
	}

	/**
	 * Method which returns an instance of CheckInLog based on a unique instance ID,
	 * if found. Otherwise returns null.
	 * 
	 * @param instanceId
	 *            The unique identifier for the instance of CheckInLog you are
	 *            looking for.
	 * @return CheckInLog associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public CheckInLog getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> checkInLogs = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();

		try {
			checkInLogs = parser.parse("src/datastore/checkInLogs.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get CheckInLog instance lookup: " + instanceId, e);
		}

		Iterator<DataType> it = checkInLogs.iterator();
		if (it.hasNext()) {
			return (CheckInLog) it.next();
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the last instance of CheckInLog for the specified playerId and cityId.
	 * @param playerId
	 * @param cityId
	 * @return the last instance of CheckInLog for the specified playerId and cityId.
	 * @throws DataSourceParseException 
	 */
	public static CheckInLog getLastInstance(Integer playerId, Integer cityId) throws DataSourceParseException {
		ArrayList<DataType> checkInLogs = new ArrayList<DataType>();
		XMLparser parser = new XMLparser();
		try {
			checkInLogs = parser.parse("src/datastore/checkInLogs.xml", null, null);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get last instance of CheckInLog for Player with ID of " + playerId + " and City with ID of " + cityId + ".", e);
		}
		CheckInLog returnLog = null;
		// only get the CheckInLogs for the specified Player and City
		for(DataType d: checkInLogs) {
			CheckInLog c = (CheckInLog) d;
			if(c.getPlayerId() == playerId && c.getCityId() == cityId) {
				// always overwrite the previous instance of CheckInLog: we want the last one
				returnLog = c;
			}
		}
		return returnLog;
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
		fields.add("playerId");
		fields.add("checkInDateTime");
		fields.add("cityId");
		fields.add("remainders");
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
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @return the checkInDateTime
	 */
	public Date getCheckInDateTime() {
		return checkInDateTime;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @return the remainders
	 */
	public ArrayList<Float> getRemainders() {
		return remainders;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @param checkInDateTime the checkInDateTime to set
	 */
	public void setCheckInDateTime(Date checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @param remainders the remainders to set
	 */
	public void setRemainders(ArrayList<Float> remainders) {
		this.remainders = remainders;
	}
	
	/**
	 * Sets a specific index of the remainders ArrayList to the specified value.
	 * @param index
	 * @param value
	 * @throws InvalidCheckInLogRemainderException 
	 */
	public void setRemainderValue(int index, float value) throws InvalidCheckInLogRemainderException {
		if(index == (Integer) null || index < 1 || index > 9) {
			throw new InvalidCheckInLogRemainderException("Cannot set index " + index + " of Resource remainders to value of " + value + ".");
		}
		ArrayList<Float> temp = this.getRemainders();
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			// set up ArrayList to insert this new value at the proper index
			temp = new ArrayList<Float>();
			for(int i = 0; i > 9; i++) {
				temp.add(0f);
			}
		}
		temp.set(index - 1, value); // arrays start at index 0, yo!
		this.setRemainders(temp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CheckInLog:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("playerId=");
		builder.append(playerId);
		builder.append("\n\t");
		builder.append("checkInDateTime=");
		builder.append(checkInDateTime);
		builder.append("\n\t");
		builder.append("cityId=");
		builder.append(cityId);
		builder.append("\n\t");
		builder.append("remainders=");
		builder.append(remainders);
		return builder.toString();
	}

}
