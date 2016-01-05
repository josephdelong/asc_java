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
import util.XMLparser;

/**
 *
 */
public class BattleLog extends DataType {

	/**
	 * Private data members
	 */
	private int id;
	private int battleId;
	private int turnNumber;
	private int playerId;
	private int turnAction;
	private Integer locationStart;
	private Integer locationEnd;
	private Integer target;
	private Integer targetLocation;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public BattleLog() {
		this.setId(0);
		this.setBattleId(0);
		this.setTurnNumber(0);
		this.setPlayerId(0);
		this.setTurnAction(0);
		this.setLocationStart(null);
		this.setLocationEnd(null);
		this.setTarget(null);
		this.setTargetLocation(null);
	}
	
	/**
	 * Constructor which sets up this BattleLog with the specified Battle, Player, and turn number.
	 * @param battleId
	 * @param playerId
	 * @param turnNumber
	 */
	public BattleLog(int battleId, int playerId, int turnNumber) {
		new BattleLog();
		this.setBattleId(battleId);
		this.setPlayerId(playerId);
		this.setTurnNumber(turnNumber);
	}
	
	/**
	 * Parse method which sets the data members of this class to values parsed from input
	 * @throws ASCException 
	 */
	@Override
	public void parse(String fieldName, String attribute, String value) throws ASCException {
		if(fieldName == null || fieldName.equals(null) || fieldName.isEmpty() || fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("battleId")) {
			this.setBattleId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("turnNumber")) {
			this.setTurnNumber(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("playerId")) {
			this.setPlayerId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("turnAction")) {
			this.setTurnAction(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("locationStart")) {
			this.setLocationStart(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("locationEnd")) {
			this.setLocationEnd(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("target")) {
			this.setTarget(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("targetLocation")) {
			this.setTargetLocation(Integer.parseInt(value));
		}
	}

	/**
	 * Method which returns an instance of BattleLog based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of BattleLog you are looking for.
	 * @return BattleLog associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public static BattleLog getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> battles = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			battles = parser.parse("src/datastore/battleLogs.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get BattleLog instance lookup: " + instanceId, e);
		}
		
		Iterator<DataType> it = battles.iterator();
		if(it.hasNext()) {
			return (BattleLog)it.next();
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
		fields.add("battleId");
		fields.add("turnNumber");
		fields.add("playerId");
		fields.add("turnAction");
		fields.add("locationStart");
		fields.add("locationEnd");
		fields.add("target");
		fields.add("targetLocation");
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
	 * @return the battleId
	 */
	public int getBattleId() {
		return battleId;
	}

	/**
	 * @return the turnNumber
	 */
	public int getTurnNumber() {
		return turnNumber;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @return the turnAction
	 */
	public int getTurnAction() {
		return turnAction;
	}

	/**
	 * @return the locationStart
	 */
	public Integer getLocationStart() {
		return locationStart;
	}

	/**
	 * @return the locationEnd
	 */
	public Integer getLocationEnd() {
		return locationEnd;
	}

	/**
	 * @return the target
	 */
	public Integer getTarget() {
		return target;
	}

	/**
	 * @return the targetLocation
	 */
	public Integer getTargetLocation() {
		return targetLocation;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param battleId the battleId to set
	 */
	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}

	/**
	 * @param turnNumber the turnNumber to set
	 */
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @param turnAction the turnAction to set
	 */
	public void setTurnAction(int turnAction) {
		this.turnAction = turnAction;
	}

	/**
	 * @param locationStart the locationStart to set
	 */
	public void setLocationStart(Integer locationStart) {
		this.locationStart = locationStart;
	}

	/**
	 * @param locationEnd the locationEnd to set
	 */
	public void setLocationEnd(Integer locationEnd) {
		this.locationEnd = locationEnd;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Integer target) {
		this.target = target;
	}

	/**
	 * @param targetLocation the targetLocation to set
	 */
	public void setTargetLocation(Integer targetLocation) {
		this.targetLocation = targetLocation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BattleLog");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("battleId=");
		builder.append(battleId);
		builder.append("\n\t");
		builder.append("turnNumber=");
		builder.append(turnNumber);
		builder.append("\n\t");
		builder.append("playerId=");
		builder.append(playerId);
		builder.append("\n\t");
		builder.append("turnAction=");
		builder.append(turnAction);
		builder.append("\n\t");
		builder.append("locationStart=");
		builder.append(locationStart);
		builder.append("\n\t");
		builder.append("locationEnd=");
		builder.append(locationEnd);
		builder.append("\n\t");
		builder.append("target=");
		builder.append(target);
		builder.append("\n\t");
		builder.append("targetLocation=");
		builder.append(targetLocation);
		return builder.toString();
	}

}
