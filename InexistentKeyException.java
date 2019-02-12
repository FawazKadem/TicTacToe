
/**
 * @author fmoham26 Exception which occurs when user tries to reeive a key that
 *         doesn't exist
 *
 */
public class InexistentKeyException extends Exception {

	/**
	 * @param message
	 *            message give to the user when exception occurs
	 */
	public InexistentKeyException(String message) {
		super(message);
	}

}
