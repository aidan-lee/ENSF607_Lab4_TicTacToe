import java.util.Scanner;

public class HumanPlayer extends Player{
	
	
	protected HumanPlayer(String name, char mark) {
		super(name, mark);
	}

    /**
     * Calls makeMove and displays the tic-tac-toe board.
     * After each turn, it checks if the board is full or a player has won.
     * If neither have occurred, it passes the turn to the opposing player.
     */
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
     * Prompts the player to enter a row and column number.
     * If the space is open, it calls the board's addMark function.
     * If the space is not open or the player's input was invalid, the player is prompted to re-enter the row
     * and column numbers.
     */
    protected void makeMove() {
        Integer row;
        Integer col;

        boolean invalidResponse = true;

        Scanner scanner = new Scanner(System.in);

        while (invalidResponse) {
            row = getValidInput(scanner, "row", Constants.ROW_MAX);
            col = getValidInput(scanner, "column", Constants.COL_MAX);
            
            if (row < Constants.ROW_MAX && col < Constants.COL_MAX) {
                boolean isSpaceFilled = board.getMark(row, col) == Constants.SPACE_CHAR ? false : true;
                if (!isSpaceFilled) {
                    board.addMark(row, col, this.mark);
                    invalidResponse = false;
                }
                else {
                    System.out.println("That space is occupied. Please try again, " + this.name + ".");
                }
            }
            else {
                System.out.println("Invalid input. Please try again, " + this.name + ".");
            }
        }
    }

    private int getValidInput(Scanner scanner, String identifier, int maxValue) {
        boolean validResponse = false;
        String responseString = "";
        int response = -1;

        while (!validResponse) {
            System.out.println(this.name + ", what " + identifier + " should your next " + this.mark + " be placed in?");
            responseString = scanner.nextLine();
            response = getInput(responseString);

            if (response == -1) {
                System.out.println("Invalid entry. Please try again.");
            }
            else if (response < 0 || response > maxValue - 1) {
                System.out.println("Invalid response. Entry must be between 0 and " + (maxValue - 1) + ".");
            }
            else {
                validResponse = true;
            }
        }

        return response;
    }

    private int getInput (String string) {
    	int result = -1;
    	try {
    		result = Integer.parseInt(string);
    		return result;
    	}
    	catch (Exception e){
    		return result;
    	}
    }
}
