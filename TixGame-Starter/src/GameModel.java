/**
 * Class to model the play of the game
 *
 * You should implement the public interface methods below
 *
 * This is the modified GameModel Class. A number of instance variables have been
 * added, and certain methods (canPlay, makePlay, printBoard) have been completed.
 */
public class GameModel {
    private Pair[][] board;
    private int length;
    private FullLineByTree fullLineByTree;

    /**
     * Construct a game with given sizexsize
     * @param sz the square size of the board
     */
    public GameModel(int sz) {
        length  = sz;
        fullLineByTree = new FullLineByTree(length);
        board = fullLineByTree.getArray();
    }

    /**
     * Can a play be made at position row, col? This checks the 2-D array (play board)
     * and checks to see what the player number is. If it is 1 or -1, a player has
     * already used the spot, and it returns false. If the the spot has a 0, it is
     * free, and the method returns true.
     * @param row the row in question
     * @param col the col in question
     * @return true if row, col is empty, false o.w.
     */
    public boolean canPlay(int row, int col) {
        int spot = board[row][col].getPlayer();
        if(spot == 0){return true;}
        return false;
    }

    /**
     * play a piece at the specified spot by the specified player. This Method
     * simply sends the information to the appropriate method in
     * FullLineByTree. That method makes the appropriate adjustments to the values
     * and returns true if the move resulted in a winning solution, false otherwise.
     * That boolean is then returned from this method.
     * @param row the row where a piece is played
     * @param col the col where a piece is played
     * @param player -1 for white and 1 for black
     * @return true if the game is over and false otherwise
     */
    public boolean makePlay(int row, int col, int player) {
        return fullLineByTree.makeMove(row, col, player);
    }


    /**
     * Print the board to console - perhaps you want this for debugging
     * Not required and can be deleted if you want.
     * This method is not currently in use. Similars ones in other classes
     * were used in debugging
     */
    private void printBoard() {
        for(int i = 0; i < length; i++){
            for(int m = 0; m < length; m++){
                Pair p = board[i][m];
                System.out.print(p + " ");
            }
            System.out.println();
        }

    }

}
