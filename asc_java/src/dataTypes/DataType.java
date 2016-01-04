/**
 * @author Joseph DeLong
 *
 */
package dataTypes;

import java.util.ArrayList;

import exceptions.ASCException;

public abstract class DataType {
	
	/**
	 * Parse method which sets the data members of this class to values parsed from input.
	 * @throws ASCException 
	 */
	public abstract void parse(String fieldName, String attribute, String value) throws ASCException;
	
	/**
	 * Method which returns this Data Type's fields.
	 * @return ArrayList of Strings containing the names of this Data Type's fields. 
	 */
	public abstract ArrayList<String> getFields();
	
	/**
	 * Method which gets the unique ID of this instance of DataType.
	 * @return
	 */
	public abstract int getId();
	
	/**
	 * Every child class should have a toString() method.
	 */
	@Override
	public abstract String toString();
	
}