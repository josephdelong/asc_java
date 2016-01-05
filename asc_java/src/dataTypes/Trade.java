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
import exceptions.InvalidTradeOfferException;
import util.XMLparser;

/**
 *
 */
public class Trade extends DataType {

	/**
	 * Private data members
	 */
	private int id;
	private int transactionType;
	private int player1;
	private int player2;
	private ArrayList<Integer> player1Offer;
	private ArrayList<Integer> player2Offer;
	private double player1Worth;
	private double player2Worth;
	private ArrayList<String> player1Comments;
	private ArrayList<String> player2Comments;
	private Date offerDate;
	private Date modifiedDate;
	private Date acceptedDate;
	private String status;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Trade() {
		this.setId(0);
		this.setTransactionType(0);
		this.setPlayer1(0);
		this.setPlayer2(0);
		this.setPlayer1Offer(null);
		this.setPlayer2Offer(null);
		this.setPlayer1Worth(0);
		this.setPlayer2Worth(0);
		this.setPlayer1Comments(null);
		this.setPlayer2Comments(null);
		this.setOfferDate(null);
		this.setModifiedDate(null);
		this.setAcceptedDate(null);
		this.setStatus("");
	}
	
	/**
	 * Constructor which sets up an instance of Trade for 2 Players
	 * @param player1
	 * @param player2
	 */
	public Trade(Integer player1, Integer player2) {
		new Trade();
		this.setPlayer1(player1);
		this.setPlayer2(player2);
		ArrayList<Integer> p1Offer = new ArrayList<Integer>();
		for(int i = 0; i < 8; i++) {
			p1Offer.add(0);
		}
		this.setPlayer1Offer(p1Offer);
		ArrayList<Integer> p2Offer = new ArrayList<Integer>();
		for(int i = 0; i < 8; i++) {
			p2Offer.add(0);
		}
		this.setPlayer2Offer(p2Offer);
		this.setPlayer1Comments(new ArrayList<String>());
		this.setPlayer2Comments(new ArrayList<String>());
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
		} else if(fieldName.equalsIgnoreCase("transactionType")) {
			this.setTransactionType(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("player1")) {
			this.setPlayer1(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("player2")) {
			this.setPlayer2(Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("player1Offer")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("player1OfferValue")) {
			this.setPlayerOfferValue(1, Integer.parseInt(attribute), Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("player2Offer")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("player2OfferValue")) {
			this.setPlayerOfferValue(2, Integer.parseInt(attribute), Integer.parseInt(value));
		} else if(fieldName.equalsIgnoreCase("player1Comments")) {
			// do nothing;
		} else if(fieldName.equalsIgnoreCase("player1Comment")) {
			this.addPlayerComment(1, value);
		} else if(fieldName.equalsIgnoreCase("player2Comments")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("player2Comment")) {
			this.addPlayerComment(2, value);
		} else if(fieldName.equalsIgnoreCase("offerDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setOfferDate(sdf.parse(value));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(fieldName.equalsIgnoreCase("modifiedDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setModifiedDate(sdf.parse(value));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(fieldName.equalsIgnoreCase("acceptedDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setAcceptedDate(sdf.parse(value));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(fieldName.equalsIgnoreCase("status")) {
			this.setStatus(value);
		}
	}

	/**
	 * Method which returns an instance of Trade based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of Trade you are looking for.
	 * @return Trade associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public static Trade getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> trades = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			trades = parser.parse("src/datastore/trades.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new DataSourceParseException("Get Trade instance lookup: " + instanceId, e);
		}
		
		Iterator<DataType> it = trades.iterator();
		if(it.hasNext()) {
			return (Trade)it.next();
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
		fields.add("transactionType");
		fields.add("player1");
		fields.add("player2");
		fields.add("player1Offer");
		fields.add("player2Offer");
		fields.add("player1Value");
		fields.add("player2Value");
		fields.add("player1Comments");
		fields.add("player2Comments");
		fields.add("offerDate");
		fields.add("modifiedDate");
		fields.add("acceptedDate");
		fields.add("status");
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
	 * @return the transactionType
	 */
	public int getTransactionType() {
		return transactionType;
	}

	/**
	 * @return the player1
	 */
	public int getPlayer1() {
		return player1;
	}

	/**
	 * @return the player2
	 */
	public int getPlayer2() {
		return player2;
	}

	/**
	 * @return the player1Offer
	 */
	public ArrayList<Integer> getPlayer1Offer() {
		return player1Offer;
	}

	/**
	 * @return the player2Offer
	 */
	public ArrayList<Integer> getPlayer2Offer() {
		return player2Offer;
	}

	/**
	 * @return the player1WOrth
	 */
	public double getPlayer1Worth() {
		return player1Worth;
	}

	/**
	 * @return the player2Worth
	 */
	public double getPlayer2Worth() {
		return player2Worth;
	}

	/**
	 * @return the player1Comments
	 */
	public ArrayList<String> getPlayer1Comments() {
		return player1Comments;
	}

	/**
	 * @return the player2Comments
	 */
	public ArrayList<String> getPlayer2Comments() {
		return player2Comments;
	}

	/**
	 * @return the offerDate
	 */
	public Date getOfferDate() {
		return offerDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @return the acceptedDate
	 */
	public Date getAcceptedDate() {
		return acceptedDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	/**
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(int player2) {
		this.player2 = player2;
	}

	/**
	 * @param player1Offer the player1Offer to set
	 */
	public void setPlayer1Offer(ArrayList<Integer> player1Offer) {
		this.player1Offer = player1Offer;
	}

	/**
	 * @param player2Offer the player2Offer to set
	 */
	public void setPlayer2Offer(ArrayList<Integer> player2Offer) {
		this.player2Offer = player2Offer;
	}

	/**
	 * @param player1wOrth the player1Worth to set
	 */
	public void setPlayer1Worth(double player1Worth) {
		this.player1Worth = player1Worth;
	}

	/**
	 * @param player2Worth the player2Worth to set
	 */
	public void setPlayer2Worth(double player2Worth) {
		this.player2Worth = player2Worth;
	}

	/**
	 * @param player1Comments the player1Comments to set
	 */
	public void setPlayer1Comments(ArrayList<String> player1Comments) {
		this.player1Comments = player1Comments;
	}

	/**
	 * @param player2Comments the player2Comments to set
	 */
	public void setPlayer2Comments(ArrayList<String> player2Comments) {
		this.player2Comments = player2Comments;
	}

	/**
	 * @param offerDate the offerDate to set
	 */
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @param acceptedDate the acceptedDate to set
	 */
	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Sets the Player offer for the specified Player at the specified offer index to the specified value.
	 * @param player
	 * @param offerIndex
	 * @param value
	 * @throws InvalidTradeOfferException 
	 */
	public void setPlayerOfferValue(int player, int offerIndex, int value) throws InvalidTradeOfferException {
		if(player == (Integer) null || player < 1 || player > 2 || 
			offerIndex == (Integer) null || offerIndex < 1 || offerIndex > 8 || 
			value == (Integer) null || value < 1) {
			throw new InvalidTradeOfferException("Unable to modify Trade offer for Player " + player + 
					" at index " + offerIndex + " to value of " + value + ".");
		}
		ArrayList<Integer> offer = null;
		if(player == 1) {
			offer = this.getPlayer1Offer();
		} else {
			offer = this.getPlayer2Offer();
		}
		if(offer == null || offer.equals(null) || offer.isEmpty() || offer.size() == 0) {
			offer = new ArrayList<Integer>();
			for(int i = 0; i < 8; i++) {
				offer.add(0);
			}
		}
		offer.set(offerIndex, value);
		if(player == 1) {
			this.setPlayer1Offer(offer);
		} else {
			this.setPlayer2Offer(offer);
		}
	}

	/**
	 * Adds the specified comment to the specified Player's comments ArrayList for this Trade instance.
	 * @param player
	 * @param comment
	 * @throws InvalidTradeOfferException 
	 */
	public void addPlayerComment(int player, String comment) throws InvalidTradeOfferException {
		if(player == (Integer) null || player < 1 || player > 2 || 
				comment == null || comment.equals(null) || comment.equals("") || comment.length() == 0) {
			throw new InvalidTradeOfferException("Unable to add comment of \"" + comment + "\" for Player " + player + ".");
		}
		ArrayList<String> temp;
		if(player == 1) {
			temp = this.getPlayer1Comments();
		} else {
			temp = this.getPlayer2Comments();
		}
		if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
			temp = new ArrayList<String>();
		}
		temp.add(comment);
		if(player == 1) {
			this.setPlayer1Comments(temp);
		} else {
			this.setPlayer2Comments(temp);
		}
	}
	
	/**
	 * Sends this Trade for review by the specified Player.
	 * @param trade
	 */
	public Trade offerTrade(int player) {
		// TODO: fill this out
		return this;
	}
	
	/**
	 * Modifies this Trade to the specified values.
	 * @param player
	 * @return
	 */
	public Trade modifyTrade(int player, ArrayList<Integer> offer) {
		// TODO: fill this out
		return this;
	}
	
	/**
	 * Accepts this Trade.
	 * @return
	 */
	public Trade acceptOffer() {
		// TODO: fill this out
		return this;
	}
	
	/**
	 * Cancels this Trade.
	 * @return
	 */
	public Trade cancelTrade() {
		// TODO: fill this out
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("transactionType=");
		builder.append(transactionType);
		builder.append("\n\t");
		builder.append("player1=");
		builder.append(player1);
		builder.append("\n\t");
		builder.append("player2=");
		builder.append(player2);
		builder.append("\n\t");
		builder.append("player1Offer=");
		builder.append(player1Offer);
		builder.append("\n\t");
		builder.append("player2Offer=");
		builder.append(player2Offer);
		builder.append("\n\t");
		builder.append("player1Worth=");
		builder.append(player1Worth);
		builder.append("\n\t");
		builder.append("player2Worth=");
		builder.append(player2Worth);
		builder.append("\n\t");
		builder.append("player1Comments=");
		builder.append(player1Comments);
		builder.append("\n\t");
		builder.append("player2Comments=");
		builder.append(player2Comments);
		builder.append("\n\t");
		builder.append("offerDate=");
		builder.append(offerDate);
		builder.append("\n\t");
		builder.append("modifiedDate=");
		builder.append(modifiedDate);
		builder.append("\n\t");
		builder.append("acceptedDate=");
		builder.append(acceptedDate);
		builder.append("\n\t");
		builder.append("status=");
		builder.append(status);
		return builder.toString();
	}

}
