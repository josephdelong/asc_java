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
	private int unitId;
	private int turnAction; // 0 = NO ACTION, 1 = MOVE, 2 = ATTACK/HEAL, 3 = DEFEND
	private Integer locationStart;
	private Integer locationEnd;
	private Integer target;
	private Integer targetLocation;
	private int damageGiven;
	private int damageTaken;

	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public BattleLog() {
		this.setId(0);
		this.setBattleId(0);
		this.setTurnNumber(0);
		this.setUnitId(0);
		this.setTurnAction(0);
		this.setLocationStart(null);
		this.setLocationEnd(null);
		this.setTarget(null);
		this.setTargetLocation(null);
		this.setDamageGiven(0);
		this.setDamageTaken(0);
	}

	/**
	 * Constructor which sets up this BattleLog with the specified input values.
	 *   NOTE: All parameters must NOT be null. Pass 0 (ZERO) for a parameter
	 *   which should be considered null.
	 * @param id
	 * @param battleId
	 * @param unitId
	 * @param turnNumber
	 * @param turnAction
	 * @param locationStart
	 * @param locationEnd
	 * @param target
	 * @param targetLocation
	 * @param damageGiven
	 * @param damageTaken
	 */
	public BattleLog(int id, int battleId, int unitId, int turnNumber,
			int turnAction, int locationStart, int locationEnd, int target,
			int targetLocation, int damageGiven, int damageTaken) {
		this.setId(id);
		this.setBattleId(battleId);
		this.setUnitId(unitId);
		this.setTurnNumber(turnNumber);
		this.setTurnAction(turnAction);
		this.setLocationStart(0);
		this.setLocationEnd(0);
		this.setTarget(0);
		this.setTargetLocation(0);
		this.setDamageGiven(0);
		this.setDamageTaken(0);
	}

	/**
	 * Parse method which sets the data members of this class to values parsed from input
	 * @throws ASCException
	 */
	@Override
	public void parse(String fieldName, String attribute, String value)
			throws ASCException {
		if (fieldName == null || fieldName.equals(null) || fieldName.isEmpty()
				|| fieldName.equalsIgnoreCase("")) {
			// do nothing
		} else if (fieldName.equalsIgnoreCase("id")) {
			this.setId(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("battleId")) {
			this.setBattleId(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("turnNumber")) {
			this.setTurnNumber(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("unitId")) {
			this.setUnitId(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("turnAction")) {
			this.setTurnAction(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("locationStart")) {
			this.setLocationStart(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("locationEnd")) {
			this.setLocationEnd(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("target")) {
			this.setTarget(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("targetLocation")) {
			this.setTargetLocation(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("damageGiven")) {
			this.setDamageGiven(Integer.parseInt(value));
		} else if (fieldName.equalsIgnoreCase("damageTaken")) {
			this.setDamageTaken(Integer.parseInt(value));
		}
	}

	/**
	 * Method which returns an instance of BattleLog based on a unique instance ID, if found. Otherwise returns null.
	 * @param instanceId
	 *            The unique identifier for the instance of BattleLog you are
	 *            looking for.
	 * @return BattleLog associated with instanceId, or null.
	 * @throws DataSourceParseException
	 */
	public static BattleLog getInstance(Integer instanceId)
			throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> battles = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();

		try {
			battles = parser.parse("src/datastore/battleLogs.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			// throw new DataSourceParseException("Get BattleLog instance lookup: " + instanceId, e);
			return null;
		}

		Iterator<DataType> it = battles.iterator();
		if (it.hasNext()) {
			return (BattleLog) it.next();
		} else {
			return null;
		}
	}

	/**
	 * Method which returns this Data Type's fields.
	 * @return ArrayList of Strings containing the names of this Data Type's
	 *         fields.
	 */
	@Override
	public ArrayList<String> getFields() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("battleId");
		fields.add("turnNumber");
		fields.add("unitId");
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
	 * @return the unitId
	 */
	public int getUnitId() {
		return unitId;
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
	 * @return the damageGiven
	 */
	public int getDamageGiven() {
		return damageGiven;
	}

	/**
	 * @return the damageTaken
	 */
	public int getDamageTaken() {
		return damageTaken;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param battleId
	 *            the battleId to set
	 */
	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}

	/**
	 * @param turnNumber
	 *            the turnNumber to set
	 */
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	/**
	 * @param unitId
	 *            the unitId to set
	 */
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	/**
	 * @param turnAction
	 *            the turnAction to set
	 */
	public void setTurnAction(int turnAction) {
		this.turnAction = turnAction;
	}

	/**
	 * @param locationStart
	 *            the locationStart to set
	 */
	public void setLocationStart(Integer locationStart) {
		this.locationStart = locationStart;
	}

	/**
	 * @param locationEnd
	 *            the locationEnd to set
	 */
	public void setLocationEnd(Integer locationEnd) {
		this.locationEnd = locationEnd;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(Integer target) {
		this.target = target;
	}

	/**
	 * @param targetLocation
	 *            the targetLocation to set
	 */
	public void setTargetLocation(Integer targetLocation) {
		this.targetLocation = targetLocation;
	}

	/**
	 * @param damageGiven
	 *            the damageGiven to set
	 */
	public void setDamageGiven(int damageGiven) {
		this.damageGiven = damageGiven;
	}

	/**
	 * @param damageTaken
	 *            the damageTaken to set
	 */
	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}

	/**
	 * Method which returns the contents of this BattleLog in story-like,
	 * human-readable format.
	 * 
	 * @return
	 * @throws DataSourceParseException
	 */
	public String printTurn() throws DataSourceParseException {
		String r = "";
		Unit u = Unit.getInstance(this.getUnitId());
		UnitType ut = UnitType.getInstance(u.getUnitId());

		r = "Unit #" + u.getId() + "(" + ut.getName() + "): " + u.getName()
				+ " ";
		// ACTION TAKEN
		switch (this.getTurnAction()) {
		case 0:
			r += "did NOTHING this turn.";
			break;
		case 1:
			r += "MOVED from [TILE INDEX " + this.getLocationStart()
					+ "] to [TILE INDEX " + this.getLocationEnd() + "].";
			break;
		case 2:
			if (this.getDamageGiven() == 0) { // no DAMAGE given
				r += "DEALT NO DAMAGE to ";
			} else if (this.getDamageGiven() > 0) { // gave positive DAMAGE
				r += "DEALT " + this.getDamageGiven() + " points of DAMAGE to ";
			} else { // gave negative DAMAGE
				r += "HEALED "
						+ Integer.divideUnsigned(this.getDamageGiven(), 1)
						+ " points of DAMAGE on ";
			}
			break;
		case 3:
			r += "FORTIFIED their location: [TILE INDEX "
					+ this.getLocationStart() + "].";
			break;
		default:
			break;
		}
		// ACTION TARGET
		if (this.getTarget() > 0) { // ACTION had a TARGET
			Unit u_target = Unit.getInstance(target);
			UnitType ut_target = UnitType.getInstance(u_target.getUnitId());
			r += "Unit #" + u_target.getId() + "(" + ut_target.getName()
					+ "): " + u_target.getName();
		}
		// TARGET LOCATION
		if (this.getTarget() > 0 && this.getTargetLocation() > 0) { // TARGET had a LOCATION
			r += " at location [TILE INDEX " + this.getTargetLocation() + "].";
		} else if (this.getTarget() > 0) {
			r += ".";
		}
		// ACTION RESULT
		if (this.getDamageTaken() > 0) {
			r += "\nUnit #" + u.getId() + "(" + ut.getName() + "): "
					+ u.getName() + " took " + this.getDamageTaken()
					+ " points of DAMAGE.";
		} else if (this.getDamageTaken() < 0) {
			r += "\nUnit #" + u.getId() + "(" + ut.getName() + "): "
					+ u.getName() + " was HEALED " + this.getDamageTaken()
					+ " points of DAMAGE.";
		}
		// STATUS UPDATE
		if (u.getDefense() + u.getCurrentHealth() - this.getDamageTaken() <= 0) { // if we've taken enough DAMAGE to be destroyed
			r += "\nUnit #" + u.getId() + "(" + ut.getName() + "): "
					+ u.getName() + " DIED.";
		}
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		builder.append(unitId);
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
