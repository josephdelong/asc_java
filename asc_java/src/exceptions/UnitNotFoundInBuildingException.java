/**
 * 
 * @author Joseph DeLong
 * 
 */
package exceptions;

import dataTypes.DataType;

/**
 *
 */
public class UnitNotFoundInBuildingException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -2142153531090064495L;

	public UnitNotFoundInBuildingException(String message, DataType object) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		builder.append("\n");
		builder.append(object.toString());
		System.out.println(builder.toString());
	}
}
