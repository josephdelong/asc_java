/**
 * 
 * @author Joseph DeLong
 * 
 */
package exceptions;

/**
 *
 */
public class DataSourceParseException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 3026207346948939578L;

	public DataSourceParseException(String message, Throwable cause) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		builder.append("\n");
		builder.append(cause.toString());
		System.out.println(builder.toString());
	}
}
