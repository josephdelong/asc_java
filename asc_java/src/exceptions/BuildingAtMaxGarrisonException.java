/**
 * 
 * @author Joseph DeLong
 * 
 */
package exceptions;

import asc_dataTypes.DataType;

/**
 *
 */
public class BuildingAtMaxGarrisonException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 4300434801659796331L;

	public BuildingAtMaxGarrisonException(String message, DataType object) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		builder.append("\n");
		builder.append(object.toString());
		System.out.println(builder.toString());
	}
}
