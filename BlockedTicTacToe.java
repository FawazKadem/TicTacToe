
/**
 * @author fmahb
 *
 */
public class BlockedTicTacToe {

	/**
	 * width of board
	 */
	private int board_size;
	/**
	 * numbers of symbols in a row/col/diag needed to win
	 */
	private int inline;
	/**
	 * max depth of future moves computer will consider
	 */
	private int max_levels;
	/**
	 * 2d array representing game board
	 */
	private char[][] gameBoard;

	/**
	 * symbol representing computer move
	 */
	private final char COMPUTER = 'o';
	/**
	 * symbol rep human move
	 */
	private final char HUMAN = 'x';
	/**
	 * symbol rep blocked square
	 */
	private final char BLOCKED = 'b';
	/**
	 * symbol rep blank square
	 */
	private final char SPACE = ' ';

	/**
	 * initializes game board and inline, max_levels rules
	 * Also fills board with empty spaces
	 * @param board_size
	 * @param inline
	 * @param max_levels
	 */
	public BlockedTicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		this.gameBoard = new char[board_size][board_size];

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				gameBoard[i][j] = ' ';
			}
		}

	}

	/**
	 * @return new empty dictionary of size 4999
	 */
	public TTTDictionary createDictionary() {
		TTTDictionary dict = new TTTDictionary(4999);
		return dict;
	}

	/**
	 * creates string representing game board and checks if its present
	 * @param configuration
	 * @return -1 if config doesn't exist in dict, score of config if it does
	 */
	public int repeatedConfig(TTTDictionary configuration) {

		String gameConfig = "";
		int result;

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (gameBoard[i][j] == COMPUTER) {
					gameConfig += 'o';

				} else if (gameBoard[i][j] == HUMAN) {
					gameConfig += 'x';

				} else if (gameBoard[i][j] == BLOCKED) {
					gameConfig += 'b';

				} else {
					gameConfig += ' ';

				}
			}
		}
		System.out.println(gameConfig);

		if (configuration.get(gameConfig) == null) {
			System.out.println("gameConfig null");
			result = -1;
		} else {
			result = configuration.get(gameConfig).getScore();
		}

		return result;

	}

	/**
	 * creates and inserts new record into dictionary
	 * @param configurations config of new record
	 * @param score of new record
	 * @param level of new record
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level) {

		String gameConfig = "";
		TTTRecord newCon;

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {

				if (gameBoard[i][j] == COMPUTER) {
					gameConfig += 'o';

				} else if (gameBoard[i][j] == HUMAN) {
					gameConfig += 'x';

				} else if (gameBoard[i][j] == BLOCKED) {
					gameConfig += 'b';

				} else {
					gameConfig += ' ';

				}
			}
		}

		newCon = new TTTRecord(gameConfig, score, level);

		try {
			configurations.put(newCon);
		} catch (DuplicatedKeyException e) {
			System.out.println("dupl key");
		}

	}

	/**
	 * stores a play on board
	 * 
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}

	/**
	 * @param row
	 * @param col
	 * @return whether square given is empty
	 */
	public boolean squareIsEmpty(int row, int col) {

		return (gameBoard[row][col] == ' ');

	}

	/**
	 * @param symbol
	 * @return whether the given symbol is present the necessary amount of times
	 *         in a row, column, or diagonal
	 */
	public boolean wins(char symbol) {

		/**
		 * inLineCount = keeps track of the size of the line a symbol is present
		 * in mi adds to i index to track lines/position mj adds to j index to
		 * track lines/position
		 */
		int inLineCount = 0;
		int mi;
		int mj;
		mj = 0; // vertical
		mi = 0; // horizontal

		// for loop which iterates over whole board and checks for lines by
		// increasing the index to either create a line to the right, a line
		// downwards, or a diagonal live

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				mi = 0;
				mj = 0;
				inLineCount = 0;

				// check right
				while (true) {

					if ((i + mi) >= board_size)
						break;
					if (gameBoard[i + mi][j] == symbol) {
						inLineCount++;
						mi++;
						if (inLineCount == inline) {

							return true;
						}
					} else {
						break;
					}

				}
				inLineCount = 0;
				// check down
				while (true) {
					if ((j + mj) >= board_size)
						break;
					if (gameBoard[i][j + mj] == symbol) {
						inLineCount++;
						mj++;
						if (inLineCount == inline) {

							return true;
						}
					} else {
						break;
					}

				}
				inLineCount = 0;
				// reset mi mj
				mi = 0;
				mj = 0;
				// check downright Diagonal

				while (true) {
					if ((j + mj) >= board_size)
						break;
					if ((i + mi) >= board_size)
						break;
					if (gameBoard[i + mi][j + mj] == symbol) {
						inLineCount++;
						mj++;
						mi++;
						if (inLineCount == inline) {

							return true;
						}
					} else {
						break;
					}
				}
				inLineCount = 0;

				// reset mi mj
				mi = 0;
				mj = 0;
				// check upright Diagonal
				while (true) {
					if ((j - mj) < 0) {
						// System.out.println("bounds j");
						break;
					}
					if ((i + mi) >= board_size) {
						// System.out.println("bounds i");

						break;
					}

					if (gameBoard[i + mi][j - mj] == symbol) {
						inLineCount++;
						mj++;
						mi++;
						if (inLineCount == inline) {

							;
							return true;
						}
					} else {
						break;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @return whether game is draw
	 */
	public boolean isDraw() {

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (gameBoard[i][j] == SPACE) {
					return false;
				}
			}

			if (wins(HUMAN) | wins(COMPUTER)) {
				return false;
			}

		}
		return true;

	}

	/**
	 * @return what the result of the current board is
	 */
	public int evalBoard() {

		if (wins(COMPUTER))
			return 3;
		if (wins(HUMAN))
			return 0;
		if (isDraw())
			return 1;
		return 2;

	}

}
