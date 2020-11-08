public class RandomPlayer extends Player {

    RandomGenerator rand;

    protected RandomPlayer(String name, char mark) {
		super(name, mark);
        rand = new RandomGenerator();
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
	
	/**
     * Randomly selects a spot on the board to place the player's character.
     * If the space is open, it calls the board's addMark function.
     * If the space is not open or the player's input was invalid, the random player will try a new random position until it 
     * is able to place its character.
     */
    protected void makeMove() {
        System.out.println(name + " is moving...");

        makeRandomMove();
    }

    protected void makeRandomMove() {
        Integer row;
        Integer col;

        boolean invalidResponse = true;
        while (invalidResponse) {
            row = rand.discrete(0, Constants.ROW_MAX - 1);
            col = rand.discrete(0, Constants.COL_MAX - 1);

            if (row < Constants.ROW_MAX && col < Constants.COL_MAX) {
                boolean isSpaceFilled = board.getMark(row, col) == Constants.SPACE_CHAR ? false : true;
                if (!isSpaceFilled) {
                    board.addMark(row, col, this.mark);
                    invalidResponse = false;
                }
            }
        }
    }
}
