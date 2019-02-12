/**
 * 
 */

/**
 * @author fmahb A record representing a configuration of the TTT board, the
 *         value of a move, and the level which describes how many turns in the
 *         future the move being considered is)
 * 
 *         Includes get methods for config, score, and level
 */
public class TTTRecord {

	/**
	 * config sequence of x, o, b, and spaces that the board is made of
	 * score value of move
	 * level how many levels in the future the move being considered is
	 */
	String config;
	int score;
	int level;

	/**
	 * 
	 * Constructor which returns a new TTTRecord with the specified
	 * configuration, score, and level.
	 * 
	 * @param config sequence of x, o, b, and spaces that the board is made of
	 * @param score value of move
	 * @param level how many levels in the future the move being considered is
	 */
	public TTTRecord(String config, int score, int level) {

		this.config = config;
		this.score = score;
		this.level = level;

	}

	/**
	 * @return current configuration
	 */
	public String getConfiguration() {
		return this.config;
	}

	/**
	 * @return current level being considered
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * @return value of current move
	 */
	public int getScore() {

		return this.score;
	}

}
