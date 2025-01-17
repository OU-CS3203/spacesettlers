package spacesettlers.game;

public class TicTacToe3DBoard extends AbstractGameBoard {
	int[][][] board;
	static int empty = 0;
	static int board_size = 3;

	public TicTacToe3DBoard() {
		board = new int[board_size][board_size][board_size];
	}

	public TicTacToe3DBoard deepClone() {
		TicTacToe3DBoard newBoard = new TicTacToe3DBoard();

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				for (int k = 0; k < board_size; k++) {
					newBoard.board[i][j][k] = board[i][j][k];
				}
			}
		}
		return newBoard;
	}

	public void setBoard(int [][][]newBoard) {
		this.board = newBoard;
	}

	public int getWinningPlayer() {
		// check in the 2D boards at each depth
		for (int dep = 0; dep < board_size; dep++) {

			// check across the rows
			for (int row = 0; row < board_size; row++) {
				int num_in_row = 1;
				int player = board[row][0][dep]; 
				if (player != empty) {
					for (int col = 1; col < board_size; col++) {
						if (board[row][col][dep] == player) {
							num_in_row++;
						} else {
							break;
						}
						if (num_in_row == board_size) {
							return player;
						}
					}
				}
			}

			// check down the columns
			for (int col = 0; col < board_size; col++) {
				int num_in_row = 1;
				int player = board[0][col][dep];  

				if (player != empty) {
					for (int row = 1; row < board_size; row++) {
						if (board[row][col][dep] == player) {
							num_in_row++;
						} else {
							break;
						}
						if (num_in_row == board_size) {
							return player;
						}
					}
				}
			}

			// check the diagonals
			int player = board[0][0][dep];
			int num_in_row = 1;
			if (player != empty) {
				for (int row = 1; row < board_size; row++) {
					if (board[row][row][dep] == player) {
						num_in_row++;
					} else {
						break;
					}
				}
				if (num_in_row == board_size) {
					return player;
				}
			}

			player = board[0][board_size-1][dep];  
			num_in_row = 1;
			if (player != empty) {
				for (int row = 1; row < board_size; row++) {
					if (board[row][board_size-row-1][dep] == player) {
						num_in_row++;
					} else {
						break;
					}
				}
				if (num_in_row == board_size) {
					return player;
				}
			}
		}


		// check rows across depth
		for (int row = 0; row < board_size; row++) {

			// check across the rows
			for (int dep = 0; dep < board_size; dep++) {
				int num_in_row = 1;
				int player = board[row][0][dep];  
				if (player != empty) {
					for (int col = 1; col < board_size; col++) {
						if (board[row][col][dep] == player) {
							num_in_row++;
						} else {
							break;
						}
						if (num_in_row == board_size) {
							return player;
						}
					}
				}
			}

			// check down the columns
			for (int dep = 0; dep < board_size; dep++) {
				int num_in_row = 1;
				int player = board[row][0][dep];  

				for (int col = 1; col < board_size; col++) {
					if (player != empty) {
						if (board[row][col][dep] == player) {
							num_in_row++;
						} else {
							break;
						}
						if (num_in_row == board_size) {
							return player;
						}
					}
				}
			}
		}

		// check the 3D diagonals
		if ((board[0][0][0] == board[1][1][1]) && (board[2][2][2] == board[1][1][1]) && 
				(board[0][0][0] != empty)) {
			return board[0][0][0];
		}

		if ((board[2][0][0] == board[1][1][1]) && (board[0][2][2] == board[1][1][1]) && 
				(board[2][0][0] != empty)) {
			return board[2][0][0];
		}

		if ((board[0][0][2] == board[1][1][1]) && (board[2][2][0] == board[1][1][1]) && 
				(board[0][0][2] != empty)) {
			return board[0][0][2];
		}

		if ((board[0][2][0] == board[1][1][1]) && (board[2][0][2] == board[1][1][1]) && 
				(board[0][2][0] != empty)) {
			return board[0][2][0];
		}


		return empty;
	}

	/**
	 * Makes the move - if the spot is not empty, the move is ignored (turn lost)
	 * 
	 * @param TTTAction
	 * @param player
	 */
	public void makeMove(TicTacToe3DAction TTTAction, int player) {
		if (board[TTTAction.row][TTTAction.col][TTTAction.depth] == empty) {
			board[TTTAction.row][TTTAction.col][TTTAction.depth] = player;
		}
	}
}
