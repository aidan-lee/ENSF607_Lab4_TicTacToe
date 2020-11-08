
/**
 * Defines all the attributes and capabilities of a Player.
 */

abstract public class Player {
    /**
     * The name of the player
     */
    protected String name;
    /**
     * An object of type Board
     */
    protected Board board;
    /**
     * An object of type Player, the opponent
     */
    protected Player opponent;
    /**
     * The player's mark (an X or an O)
     */
    protected char mark;

    /**
     * A constructor for Player objects
     * @param name The player's name
     * @param mark The player's mark
     */
    protected Player(String name, char mark) {
        this.name = name;
        this.board = null;
        this.opponent = null;
        this.mark = mark;
    }

    
    abstract protected void play();
    
    abstract protected void makeMove();


    /**
     * Sets the Player object's opponent field
     * @param opponent The opponent Player object
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Sets the Player object's board field
     * @param theBoard A Board object
     */
    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }



}
