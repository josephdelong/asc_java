/**
 * 
 * @author Joseph DeLong
 * 
 */
package exceptions;

/**
 *
 */
public class SurplusManagementException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 3851465638762807227L;

	public SurplusManagementException(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		System.out.println(builder.toString());
	}
}
