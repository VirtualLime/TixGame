/**
 * Class to model the play of the game
 *
 * You should implement the public interface methods below
 */
public class GameModel {
    private int[][] board;
    private int length;
    private FullLine fullLine;

    /**
     * Construct a game with given sizexsize
     * @param sz the square size of the board
     */
    public GameModel(int sz) {
        length  = sz;
        board = new int [sz][sz];
        emptyBoard(sz);
        fullLine = new FullLine(length);

    }

    private void emptyBoard(int size){
        for(int i = 0; i<size; i++){
            for(int m = 0; m < size; m++){
                board[i][m] = 0;
            }
        }
        printBoard();
    }


    /**
     * Can a play be made at position row, col?
     * @param row the row in question
     * @param col the col in question
     * @return true if row, col is empty, false o.w.
     */
    public boolean canPlay(int row, int col) {
        int spot = board[row][col];
        if(spot == 0){return true;}

        //placeholder
        return false;
    }

    /**
     * play a piece at the specified spot by the specified player
     * @param row the row where a piece is played
     * @param col the col where a piece is played
     * @param player -1 for white and 1 for black
     * @return true if the game is over and false otherwise
     */
    public boolean makePlay(int row, int col, int player) {
        board[row][col] = player;
        printBoard();

        return fullLine.makeMove(player, col, row);

        //placeholder
        //return false;
    }


    /**
     * Print the board to console - perhaps you want this for debugging
     * Not required and can be deleted if you want
     */
    private void printBoard() {
        for(int i = 0; i < length; i++){
            for(int m = 0; m < length; m++){
                int spot = board[i][m];
                System.out.print(spot + " ");
            }
            System.out.println();
        }

    }

}
