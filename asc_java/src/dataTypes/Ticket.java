/**
 * @author Joseph DeLong
 *
 */
package dataTypes;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import exceptions.ASCException;
import exceptions.DataSourceParseException;
import exceptions.InvalidTicketModificationException;
import util.XMLparser;

/**
 *
 */
public class Ticket extends DataType {

	/**
	 * Private data members
	 */
	private int id;
	private String issuerName;
	private String ticketType;
	private String ticketSubtype;
	private String ticketDescription;
	private String status;
	private Date submitDate;
	private Date reviewDate;
	private Date resolveDate;
	private String reviewerName;
	private String reviewerComment;
	private ArrayList<File> supportingDocuments;
	
	/**
	 * Default Constructor which initializes all fields to unusable defaults.
	 */
	public Ticket() {
		this.setId(0);
		this.setIssuerName("");
		this.setTicketType("");
		this.setTicketSubtype("");
		this.setTicketDescription("");
		this.setStatus("");
		this.setSubmitDate(null);
		this.setReviewDate(null);
		this.setResolveDate(null);
		this.setReviewerName("");
		this.setReviewerComment("");
		this.setSupportingDocuments(null);
	}
	
	/**
	 * Constructor which sets up an instance of Ticket for a particular Player.
	 * @param playerId
	 * @throws DataSourceParseException 
	 */
	public Ticket(int playerId) throws DataSourceParseException {
		new Ticket();
		Player p = Player.getInstance(playerId);
		this.setIssuerName(p.getName());
		this.setStatus("Issued");
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
		} else if(fieldName.equalsIgnoreCase("issuerName")) {
			this.setIssuerName(value);
		} else if(fieldName.equalsIgnoreCase("ticketType")) {
			this.setTicketType(value);
		} else if(fieldName.equalsIgnoreCase("ticketSubtype")) {
			this.setTicketSubtype(value);
		} else if(fieldName.equalsIgnoreCase("ticketDescription")) {
			this.setTicketDescription(value);
		} else if(fieldName.equalsIgnoreCase("status")) {
			this.setStatus(value);
		} else if(fieldName.equalsIgnoreCase("submitDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setSubmitDate(sdf.parse(value));
			} catch (ParseException e) {
				throw new DataSourceParseException("Couldn't parse " + value + " into a valid SimpleDateFormat.", e);
			}
		} else if(fieldName.equalsIgnoreCase("reviewDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setReviewDate(sdf.parse(value));
			} catch (ParseException e) {
				throw new DataSourceParseException("Couldn't parse " + value + " into a valid SimpleDateFormat.", e);
			}
		} else if(fieldName.equalsIgnoreCase("resolveDate")) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				this.setResolveDate(sdf.parse(value));
			} catch (ParseException e) {
				throw new DataSourceParseException("Couldn't parse " + value + " into a valid SimpleDateFormat.", e);
			}
		} else if(fieldName.equalsIgnoreCase("reviewerName")) {
			this.setReviewerName(value);
		} else if(fieldName.equalsIgnoreCase("reviewerComment")) {
			this.setReviewerComment(value);
		} else if(fieldName.equalsIgnoreCase("supportingDocuments")) {
			// do nothing
		} else if(fieldName.equalsIgnoreCase("supportingDocument")) {
			ArrayList<File> temp = this.getSupportingDocuments();
			if(temp == null || temp.equals(null) || temp.isEmpty() || temp.size() == 0) {
				temp = new ArrayList<File>();
			}
			temp.add(new File(value));
			this.setSupportingDocuments(temp);
		}
	}

	/**
	 * Method which returns an instance of Ticket based on a unique instance ID, if found.
	 *   Otherwise returns null.
	 * @param instanceId The unique identifier for the instance of Ticket you are looking for.
	 * @return Ticket associated with instanceId, or null.
	 * @throws DataSourceParseException 
	 */
	public static Ticket getInstance(Integer instanceId) throws DataSourceParseException {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<DataType> tickets = new ArrayList<DataType>();
		ids.add(instanceId);
		XMLparser parser = new XMLparser();
		
		try {
			tickets = parser.parse("src/datastore/tickets.xml", null, ids);
		} catch (IOException | SAXException | ParserConfigurationException e) {
//			throw new DataSourceParseException("Get Ticket instance lookup: " + instanceId, e);
			return null;
		}
		
		Iterator<DataType> it = tickets.iterator();
		if(it.hasNext()) {
			return (Ticket)it.next();
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
		fields.add("issuerName");
		fields.add("ticketType");
		fields.add("ticketSubtype");
		fields.add("ticketDescription");
		fields.add("status");
		fields.add("submitDate");
		fields.add("reviewDate");
		fields.add("resolveDate");
		fields.add("reviewerName");
		fields.add("reviewerComments");
		fields.add("supportingDocuments");
		return fields;
	}
	
	/**
	 * Modifies this Ticket to "Reviewed" status, and populates reviewerName, reviewerComment, and reviewedDate fields.
	 * @param reviewerName
	 * @param reviewerComments
	 * @throws InvalidTicketModificationException 
	 */
	public void setReviewedTicket(String reviewerName, String reviewerComments) throws InvalidTicketModificationException {
		if(reviewerName == null || reviewerComments == null || 
				reviewerName.equals(null) || reviewerComments.equals(null) ||
				reviewerName.length() < 1 || reviewerComments.length() < 1) {
			throw new InvalidTicketModificationException("Cannot modify Ticket to \"Reviewed\" status with reviewerName: " + reviewerName + " and reviewerComments: " + reviewerComments);
		}
		this.setReviewDate(Date.from(Instant.now()));
		this.setReviewerName(reviewerName);
		this.setReviewerComment(reviewerComments);
		this.setStatus("Reviewed");
	}
	
	/**
	 * Modifies this Ticket to "Resolved" status, and populates reviewerName, reviewerComment, and resolveDate fields.
	 * @param reviewerName
	 * @param reviewerComments
	 * @throws InvalidTicketModificationException 
	 */
	public void setResolvedTicket(String reviewerName, String reviewerComments) throws InvalidTicketModificationException {
		if(reviewerName == null || reviewerComments == null || 
				reviewerName.equals(null) || reviewerComments.equals(null) ||
				reviewerName.length() < 1 || reviewerComments.length() < 1) {
			throw new InvalidTicketModificationException("Cannot modify Ticket to \"Resolved\" status with reviewerName: " + reviewerName + " and reviewerComments: " + reviewerComments);
		}
		this.setReviewDate(Date.from(Instant.now()));
		this.setReviewerName(reviewerName);
		this.setReviewerComment(reviewerComments);
		this.setStatus("Resolved");
	}

	/**
	 * @return the id
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * @return the issuerName
	 */
	public String getIssuerName() {
		return issuerName;
	}

	/**
	 * @return the ticketType
	 */
	public String getTicketType() {
		return ticketType;
	}

	/**
	 * @return the ticketSubtype
	 */
	public String getTicketSubtype() {
		return ticketSubtype;
	}

	/**
	 * @return the ticketDescription
	 */
	public String getTicketDescription() {
		return ticketDescription;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * @return the reviewDate
	 */
	public Date getReviewDate() {
		return reviewDate;
	}

	/**
	 * @return the resolveDate
	 */
	public Date getResolveDate() {
		return resolveDate;
	}

	/**
	 * @return the reviewerName
	 */
	public String getReviewerName() {
		return reviewerName;
	}

	/**
	 * @return the reviewerComment
	 */
	public String getReviewerComment() {
		return reviewerComment;
	}

	/**
	 * @return the supportingDocuments
	 */
	public ArrayList<File> getSupportingDocuments() {
		return supportingDocuments;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param issuerName the issuerName to set
	 */
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	/**
	 * @param ticketType the ticketType to set
	 */
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	/**
	 * @param ticketSubtype the ticketSubtype to set
	 */
	public void setTicketSubtype(String ticketSubtype) {
		this.ticketSubtype = ticketSubtype;
	}

	/**
	 * @param ticketDescription the ticketDescription to set
	 */
	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * @param reviewDate the reviewDate to set
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * @param resolveDate the resolveDate to set
	 */
	public void setResolveDate(Date resolveDate) {
		this.resolveDate = resolveDate;
	}

	/**
	 * @param reviewerName the reviewerName to set
	 */
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	/**
	 * @param reviewerComment the reviewerComment to set
	 */
	public void setReviewerComment(String reviewerComment) {
		this.reviewerComment = reviewerComment;
	}

	/**
	 * @param supportingDocuments the supportingDocuments to set
	 */
	public void setSupportingDocuments(ArrayList<File> supportingDocuments) {
		this.supportingDocuments = supportingDocuments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket:");
		builder.append("\n\t");
		builder.append("id=");
		builder.append(id);
		builder.append("\n\t");
		builder.append("issuerName=");
		builder.append(issuerName);
		builder.append("\n\t");
		builder.append("ticketType=");
		builder.append(ticketType);
		builder.append("\n\t");
		builder.append("ticketSubtype=");
		builder.append(ticketSubtype);
		builder.append("\n\t");
		builder.append("ticketDescription=");
		builder.append(ticketDescription);
		builder.append("\n\t");
		builder.append("status=");
		builder.append(status);
		builder.append("\n\t");
		builder.append("submitDate=");
		builder.append(submitDate);
		builder.append("\n\t");
		builder.append("reviewDate=");
		builder.append(reviewDate);
		builder.append("\n\t");
		builder.append("resolveDate=");
		builder.append(resolveDate);
		builder.append("\n\t");
		builder.append("reviewerName=");
		builder.append(reviewerName);
		builder.append("\n\t");
		builder.append("reviewerComment=");
		builder.append(reviewerComment);
		builder.append("\n\t");
		builder.append("supportingDocuments=");
		builder.append(supportingDocuments);
		return builder.toString();
	}

}
