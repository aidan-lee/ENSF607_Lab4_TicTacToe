import javafx.util.Pair;

public class SmartPlayer extends BlockingPlayer{

	protected SmartPlayer(String name, char mark) {
		super(name, mark);
	}
	
	protected void makeMove() {
		System.out.println(name + " is moving...");
		Pair<Integer, Integer> winningSpace = getWinningSpace();

		Integer row = winningSpace.getKey();
		Integer column = winningSpace.getValue();

		// If there is no winning space, check for blocking space
		if (row == -1 && column == -1) {
			Pair<Integer, Integer> blockingSpace = getBlockingSpace();
			row = blockingSpace.getKey();
			column = blockingSpace.getValue();

			// If there is no blocking space, make a random move
			if (row == -1 && column == -1) {
				makeRandomMove();
			}
			else {
				board.addMark(row, column, mark);
			}
		}
		else {
			board.addMark(row, column, mark);
		}
    }
	
	 protected void play() {
	 	makeMove();

        board.display();

        if (board.isFull()) {
            System.out.println("The board is full, the game is over. It was a tie!");
        }
        else if (board.oWins() || board.xWins()) {
            System.out.println("The game is over.  " + this.name + " is the winner!");
        }
        else {
            opponent.play();
        }
    }

	protected Pair<Integer, Integer> getWinningSpace() {
		Pair<Integer, Integer> result = new Pair(-1, -1);

		for (int i = 0; i < Constants.ROW_MAX; i++) {
			for (int j = 0; j < Constants.COL_MAX; j++) {
				if(board.getMark(i,j) == Constants.SPACE_CHAR) {
					if(testForWinning(i,j)) {
						result = new Pair(i, j);
						return result;
					}
				}
			}
		}

		return result;
	}
	
	protected boolean testForWinning(int row, int col) {
		board.addMark(row, col, this.mark);
		if (board.checkWinner(this.mark) == 1) {
			board.removeMark(row, col);
			return true;
		}
		board.removeMark(row, col);
		return false;
	}
	
}
