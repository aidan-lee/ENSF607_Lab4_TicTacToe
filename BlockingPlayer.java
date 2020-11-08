import javafx.util.Pair;

public class BlockingPlayer extends RandomPlayer {

	protected BlockingPlayer(String name, char mark) {
		super(name, mark);
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

	protected void makeMove() {
		System.out.println(name + " is moving...");
		Pair<Integer, Integer> blockingSpace = getBlockingSpace();

		Integer row = blockingSpace.getKey();
		Integer column = blockingSpace.getValue();

		if (row == -1 && column == -1) {
			makeRandomMove();
		}
		else {
			board.addMark(row, column, mark);
		}
	}

    protected Pair<Integer, Integer> getBlockingSpace() {
		Pair<Integer, Integer> result = new Pair(-1, -1);

		for (int i = 0; i < Constants.ROW_MAX; i++) {
			for (int j = 0; j < Constants.COL_MAX; j++) {
				if(board.getMark(i,j) == Constants.SPACE_CHAR) {
					if(testForBlocking(i,j)) {
						result = new Pair(i, j);
						return result;
					}
				}
			}
		}

		return result;
	}

	protected boolean testForBlocking(int row, int col) {
		board.addMark(row, col, this.opponent.mark);
		if (board.checkWinner(this.opponent.mark) == 1) {
			board.removeMark(row, col);
			return true;
		}
		board.removeMark(row, col);
		return false;
	}
	
}
