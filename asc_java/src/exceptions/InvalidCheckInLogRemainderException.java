/**
 * 
 * @author Joseph DeLong
 * 
 */
package exceptions;

/**
 *
 */
public class InvalidCheckInLogRemainderException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -8494934032291770960L;

	public InvalidCheckInLogRemainderException(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		System.out.println(builder.toString());
	}
}
