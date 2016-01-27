/**
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

import exceptions.ASCException;
import exceptions.DataSourceParseException;
import exceptions.UnitNotFoundException;
import util.XMLparser;

/**
 *
 */
public class Battle extends DataType {

	/**
	 * Private data members
	 */
	private int id;
	private Date startDate;
	private Date endDate;
	private Player attackingPlayer;
	private Player defendingPlayer;
	private ArrayList<Unit> attackingUnits;
	private ArrayList<Unit> defendingUnits;
	private double attackingStrength;
	private double defendingStrength;
	private ArrayList<Resource> attackingPlayerResources;
	private ArrayList<Resource> defendingPlayerResources;
	//private ArrayList<Resource> wager?;
	private ArrayList<BattleLog> turns;
	private String result;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Battle() {
		
	}
	
	public Battle(int player1, int player2) {
		
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
		} else if(fieldName.equalsIgnoreCase("startDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setStartDate(sdf.parse(value));
			} catch (ParseException e) {
				throw new DataSourceParseException("Couldn't parse " + value + " into a valid SimpleDateFormat.", e);
			}
		} else if(fieldName.equalsIgnoreCase("endDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setEndDate(sdf.parse(value));
			} catch (ParseException e) {
				throw new DataSourceParseException("Couldn't parse " + value + " into a valid SimpleDateFormat.", e);
			}
		} else if(fieldName.equalsIgnoreCase("attackingPlayer")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("defendingPlayer")) {
			this.setId(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("attackingUnits")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("attackingUnit")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				this.addAttackingUnit(Integer.parseInt(s));
			}
		} else if(fieldName.equalsIgnoreCase("defendingUnits")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("defendingUnit")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				this.addDefendingUnit(Integer.parseInt(s));
			}
		} else if(fieldName.equalsIgnoreCase("attackingStrength")) {
			this.setAttackingStrength(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("defendingStrength")) {
			this.setDefendingStrength(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("attackingPlayerResources")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("attackingPlayerResource")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				this.addAttackingPlayerResource(Integer.parseInt(attribute), Integer.parseInt(s));
			}
		} else if(fieldName.equalsIgnoreCase("defendingPlayerResources")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("defendingPlayerResource")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				this.addDefendingPlayerResource(Integer.parseInt(attribute), Integer.parseInt(s));
			}
		} else if(fieldName.equalsIgnoreCase("turns")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("turn")) {
			String s = value.trim();
			if(s == null || s.equals(null) || s.isEmpty() || s.equalsIgnoreCase("")) {
				// do nothing
			} else {
				this.addTurn(Integer.parseInt(attribute), Integer.parseInt(s));
			}
		} else if(fieldName.equalsIgnoreCase("result")) {
			this.setResult(value);
		}
	}

	/**
	 * Method which returns an instance of Battle based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of Battle you are looking for.
	 * @return Battle associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public static Battle getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> battles = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			battles = parser.parse("src/datastore/battles.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
//			throw new DataSourceParseException("Get Battle instance lookup: " + instanceId, e);
			return null;
		}
		
		Iterator<DataType> it = battles.iterator();
		if(it.hasNext()) {
			return (Battle)it.next();
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
		fields.add("startDate");
		fields.add("endDate");
		fields.add("attackingPlayer");
		fields.add("defendingPlayer");
		fields.add("attackingUnits");
		fields.add("defendingUnits");
		fields.add("attackingStrength");
		fields.add("defendingStrength");
		fields.add("attackingPlayerResources");
		fields.add("defendingPlayerResources");
		fields.add("turns");
		fields.add("result");
		return fields;
	}
	
	/**
	 * Method which returns a String representation of one of this Data Type's fields, 
	 *   indicated by the fieldName parameter.
	 * @param fieldName The name of the field for which we are retrieving its data value.
	 * @return THe value of the field specified by fieldName.
	 * @throws DataSourceParseException 
	 */
	public String getFieldValue(String fieldName) throws DataSourceParseException {
		ArrayList<String> fields = getFields();
		String r = null;
		Iterator<Unit> it_u;
		Iterator<Resource> it_r;
		Iterator<BattleLog> it_b;
		if(fields.contains(fieldName)) {
			switch (fieldName) {
			case "id":
				r = String.valueOf(this.getId());
				break;
			case "startDate":
				r = this.getStartDate().toString();
				break;
			case "endDate":
				r = this.getEndDate().toString();
				break;
			case "attackingPlayer":
				r = this.getAttackingPlayer().getName();
				break;
			case "defendingPlayer":
				r = this.getDefendingPlayer().getName();
				break;
			case "attackingUnits":
				r = "[";
				it_u = getAttackingUnits().iterator();
				while(it_u.hasNext()) {
					r += it_u.next().getId();
					if(it_u.hasNext()) {
						r += ", ";
					}
				}
				r += "]";
				break;
			case "defendingUnits":
				r = "[";
				it_u = this.getDefendingUnits().iterator();
				while(it_u.hasNext()) {
					r += it_u.next().getId();
					if(it_u.hasNext()) {
						r += ", ";
					}
				}
				r += "]";
				break;
			case "attackingStrength":
				r = String.valueOf(this.getAttackingStrength());
				break;
			case "defendingStrength":
				r = String.valueOf(this.getDefendingStrength());
				break;
			case "attackingPlayerResources":
				r = "[";
				it_r = this.getAttackingPlayerResources().iterator();
				while(it_r.hasNext()) {
					r += it_r.next().getAmount();
					if(it_r.hasNext()) {
						r += ", ";
					}
				}
				r += "]";
				break;
			case "defendingPlayerResources":
				r = "[";
				it_r = this.getDefendingPlayerResources().iterator();
				while(it_r.hasNext()) {
					r += it_r.next().getAmount();
					if(it_r.hasNext()) {
						r += ", ";
					}
				}
				r += "]";
				break;
			case "wager":
				//r = this.getWager();
				break;
			case "turns":
				r = "[";
				it_b = this.getTurns().iterator();
				while(it_b.hasNext()) {
					r += it_b.next().printTurn();
					if(it_b.hasNext()) {
						r += ",\n";
					}
				}
				r += "]";
				break;
			case "result":
				r = this.getResult();
				break;
			default:
				break;
			}
		}
		return r;
	}

	/**
	 * @return the id
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the attackingPlayer
	 */
	public Player getAttackingPlayer() {
		return attackingPlayer;
	}

	/**
	 * @return the defendingPlayer
	 */
	public Player getDefendingPlayer() {
		return defendingPlayer;
	}

	/**
	 * @return the attackingUnits
	 */
	public ArrayList<Unit> getAttackingUnits() {
		return attackingUnits;
	}

	/**
	 * @return the defendingUnits
	 */
	public ArrayList<Unit> getDefendingUnits() {
		return defendingUnits;
	}

	/**
	 * @return the attackingStrngth
	 */
	public double getAttackingStrength() {
		return attackingStrength;
	}

	/**
	 * @return the defendingStrength
	 */
	public double getDefendingStrength() {
		return defendingStrength;
	}

	/**
	 * @return the attackingPlayerResources
	 */
	public ArrayList<Resource> getAttackingPlayerResources() {
		return attackingPlayerResources;
	}

	/**
	 * @return the defendingPlayerResources
	 */
	public ArrayList<Resource> getDefendingPlayerResources() {
		return defendingPlayerResources;
	}

	/**
	 * @return the turns
	 */
	public ArrayList<BattleLog> getTurns() {
		return turns;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param attackingPlayer the attackingPlayer to set
	 */
	public void setAttackingPlayer(Player attackingPlayer) {
		this.attackingPlayer = attackingPlayer;
	}

	/**
	 * @param defendingPlayer the defendingPlayer to set
	 */
	public void setDefendingPlayer(Player defendingPlayer) {
		this.defendingPlayer = defendingPlayer;
	}

	/**
	 * @param attackingUnits the attackingUnits to set
	 */
	public void setAttackingUnits(ArrayList<Unit> attackingUnits) {
		this.attackingUnits = attackingUnits;
	}

	/**
	 * @param defendingUnits the defendingUnits to set
	 */
	public void setDefendingUnits(ArrayList<Unit> defendingUnits) {
		this.defendingUnits = defendingUnits;
	}

	/**
	 * @param attackingStrngth the attackingStrngth to set
	 */
	public void setAttackingStrength(double attackingStrength) {
		this.attackingStrength = attackingStrength;
	}

	/**
	 * @param defendingStrength the defendingStrength to set
	 */
	public void setDefendingStrength(double defendingStrength) {
		this.defendingStrength = defendingStrength;
	}

	/**
	 * @param attackingPlayerResources the attackingPlayerResources to set
	 */
	public void setAttackingPlayerResources(ArrayList<Resource> attackingPlayerResources) {
		this.attackingPlayerResources = attackingPlayerResources;
	}

	/**
	 * @param defendingPlayerResources the defendingPlayerResources to set
	 */
	public void setDefendingPlayerResources(ArrayList<Resource> defendingPlayerResources) {
		this.defendingPlayerResources = defendingPlayerResources;
	}

	/**
	 * @param turns the turns to set
	 */
	public void setTurns(ArrayList<BattleLog> turns) {
		this.turns = turns;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Adds the specified Unit to this Battle's attacking units ArrayList.
	 * @param unitId
	 * @throws UnitNotFoundException 
	 */
	private void addAttackingUnit(int unitId) throws UnitNotFoundException {
		ArrayList<Unit> temp = this.getAttackingUnits();
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			temp = new ArrayList<Unit>();
		}
		Unit u = Unit.getInstance(unitId);
		if(u == null || u.equals(null)) {
			throw new UnitNotFoundException("Unable to add Unit with id " + unitId + " to Attacking Units.", this);
		}
		temp.add(u);
		this.setAttackingUnits(temp);
	}

	/**
	 * Adds the specified Unit to this Battle's defending units ArrayList.
	 * @param unitId
	 * @throws UnitNotFoundException 
	 */
	private void addDefendingUnit(int unitId) throws UnitNotFoundException {
		ArrayList<Unit> temp = this.getDefendingUnits();
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			temp = new ArrayList<Unit>();
		}
		Unit u = Unit.getInstance(unitId);
		if(u == null || u.equals(null)) {
			throw new UnitNotFoundException("Unable to add Unit with id " + unitId + " to Defending Units.", this);
		}
		temp.add(u);
		this.setDefendingUnits(temp);
	}

	/**
	 * Adds the specified Resource to this Battle's attacking Player Resource pile.
	 * @param resourceType
	 * @param amount
	 * @throws DataSourceParseException 
	 */
	private void addAttackingPlayerResource(int resourceType, int amount) throws DataSourceParseException {
		ArrayList<Resource> temp = this.getAttackingPlayerResources();
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			temp = new ArrayList<Resource>();
			for(int i = 0; i < 8; i++) {
				temp.add(new Resource(i, 0));
			}
		}
		Resource r = temp.get(resourceType);
		r.setAmount(r.getAmount() + amount);
		temp.set(resourceType, r);
		this.setAttackingPlayerResources(temp);
	}

	/**
	 * Adds the specified Resource to this Battle's defending Player Resource pile.
	 * @param resourceType
	 * @param amount
	 * @throws DataSourceParseException 
	 */
	private void addDefendingPlayerResource(int resourceType, int amount) throws DataSourceParseException {
		ArrayList<Resource> temp = this.getDefendingPlayerResources();
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			temp = new ArrayList<Resource>();
			for(int i = 0; i < 8; i++) {
				temp.add(new Resource(i, 0));
			}
		}
		Resource r = temp.get(resourceType);
		r.setAmount(r.getAmount() + amount);
		temp.set(resourceType, r);
		this.setDefendingPlayerResources(temp);
	}

	/**
	 * Adds an instance of BattleLog to this Battle's BattleLog ArrayList.
	 * @param player
	 * @param turnNumber
	 */
	private void addTurn(int player, int turnNumber) {
		ArrayList<BattleLog> temp = this.getTurns();
		if(turns == null || turns.equals(null) || turns.isEmpty() || turns.size() == 0) {
			turns = new ArrayList<BattleLog>();
		}
		BattleLog bl = new BattleLog(this.getId(), player, turnNumber);
		temp.add(bl);
		this.setTurns(temp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Battle");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("startDate=");
		builder.append(startDate);
		builder.append("\n\t");
		builder.append("endDate=");
		builder.append(endDate);
		builder.append("\n\t");
		builder.append("attackingPlayer=");
		builder.append(attackingPlayer);
		builder.append("\n\t");
		builder.append("defendingPlayer=");
		builder.append(defendingPlayer);
		builder.append("\n\t");
		builder.append("attackingUnits=");
		builder.append(attackingUnits);
		builder.append("\n\t");
		builder.append("defendingUnits=");
		builder.append(defendingUnits);
		builder.append("\n\t");
		builder.append("attackingStrength=");
		builder.append(attackingStrength);
		builder.append("\n\t");
		builder.append("defendingStrength=");
		builder.append(defendingStrength);
		builder.append("\n\t");
		builder.append("attackingPlayerResources=");
		builder.append(attackingPlayerResources);
		builder.append("\n\t");
		builder.append("defendingPlayerResources=");
		builder.append(defendingPlayerResources);
		builder.append("\n\t");
		builder.append("turns=");
		builder.append(turns);
		builder.append("\n\t");
		builder.append("result=");
		builder.append(result);
		return builder.toString();
	}

}
