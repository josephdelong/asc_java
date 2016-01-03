/**
 * 
 */
package exceptions;

import asc_dataTypes.DataType;

/**
 * @author Joseph
 *
 */
public class UnitNotFoundException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -7303778956245938554L;

	public UnitNotFoundException(String message, DataType object) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		builder.append("\n");
		builder.append(object.toString());
		System.out.println(builder.toString());
	}
}
