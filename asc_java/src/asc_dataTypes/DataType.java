/**
 * @author Joseph DeLong
 *
 */
package asc_dataTypes;

import java.util.ArrayList;

public abstract class DataType {
	
	/**
	 * Parse method which sets the data members of this class to values parsed from input.
	 */
	public abstract void parse(String fieldName, String value);
	
	/**
	 * Method which returns this Data Type's fields.
	 * @return ArrayList of Strings containing the names of this Data Type's fields. 
	 */
	public abstract ArrayList<String> getFields();
	
	/**
	 * Method which gets the unique ID of this instance of DataType
	 * @return
	 */
	public abstract int getId();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
	
}