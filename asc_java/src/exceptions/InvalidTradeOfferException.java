/**
 * 
 * @author joseph_delong
 * 
 */
package exceptions;

/**
 *
 */
public class InvalidTradeOfferException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 4871352404689349136L;

	public InvalidTradeOfferException(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		System.out.println(builder.toString());
	}
}
