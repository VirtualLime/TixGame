/**
 * Class to model the play of the game
 *
 * You should implement the public interface methods below
 */
public class GameModel {

    /**
     * Construct a game with given sizexsize
     * @param sz the square size of the board
     */
    public GameModel(int sz) {

    }


    /**
     * Can a play be made at position row, col?
     * @param row the row in question
     * @param col the col in question
     * @return true if row, col is empty, false o.w.
     */
    public boolean canPlay(int row, int col) {

        //placeholder
        return true;
    }

    /**
     * play a piece at the specified spot by the specified player
     * @param row the row where a piece is played
     * @param col the col where a piece is played
     * @param player -1 for white and 1 for black
     * @return true if the game is over and false otherwise
     */
    public boolean makePlay(int row, int col, int player) {

        //placeholder
        return false;
    }


    /**
     * Print the board to console - perhaps you want this for debugging
     * Not required and can be deleted if you want
     */
    private void printBoard() {

    }

}
