/**
 * 
 * @author Joseph DeLong
 * 
 */
package exceptions;

/**
 *
 */
public class InvalidMarketLogResourceValueException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 7321562815022450132L;

	public InvalidMarketLogResourceValueException(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		System.out.println(builder.toString());
	}
}
