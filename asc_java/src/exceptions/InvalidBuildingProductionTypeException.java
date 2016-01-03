/**
 * 
 * @author Joseph DeLong
 * 
 */
package exceptions;

/**
 *
 */
public class InvalidBuildingProductionTypeException extends ASCException {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 9111771758638584087L;

	public InvalidBuildingProductionTypeException(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append(message);
		System.out.println(builder.toString());
	}
}
