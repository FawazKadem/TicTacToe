
/**
 * @author fmahb Exception which occurs when user tries to add a key to the
 *         dictionary which already exists within it
 *
 */
public class DuplicatedKeyException extends Exception {

	/**
	 * @param message
	 */
	public DuplicatedKeyException(String message) {
		super(message);
	}

}
