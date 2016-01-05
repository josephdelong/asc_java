/**
 * 
 * @author joseph_delong
 * 
 */
package exceptions;

/**
 *
 */
public class InvalidTicketModificationException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -1617072819111677667L;

	public InvalidTicketModificationException(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		System.out.println(builder.toString());
	}
}
