import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;


/**
 * Pass information between the model and the gui
 */
public class GameController implements MouseListener {

    private ViewDelegate viewDelegate;
    private GameModel model;
    private int turn;
    boolean gameOver;

    public final static int WHITE = -1;
    public final static int BLACK = 1;

    public interface ViewDelegate {
        void displayTurn(String clr);
        void setColor(int row, int col, int clr);
        void listen(MouseListener ml);
        Optional<Point> getHexDim(int y, int x);
        void setTime(double ms);
        void setWinner(String winner);
    }

    public GameController () {
        turn = WHITE;
        gameOver = false;
    }

    /**
     * Change the player turn
     */
    public void toggleTurn() {
        turn = turn * -1;
        viewDelegate.displayTurn(turn == BLACK ? "Black": "White");
    }

    /**
     * Set the size of the game
     * @param size
     */
    public void setSize(int size) {
        model = new GameModel(size);
    }

    /*
        Where does the gui want updates?
     */
    public void setDelegate(ViewDelegate vd) {
        viewDelegate = vd;
        vd.displayTurn("White");
        vd.listen(this);
    }

    /*
        Play a piece at the given Point
     */
    public void makeMove(Point p) {
        int col = p.x;
        int row = p.y;
        System.out.println("click row:" + row + " col:" + col);

        if(model.canPlay(row, col)) {
            viewDelegate.setColor(row, col, turn);
            long startTime = System.nanoTime();
            if(model.makePlay(row, col, turn)) {
                System.out.println("winner");
                gameOver = true;
                if(turn == BLACK)
                    viewDelegate.setWinner("BLACK");
                else
                    viewDelegate.setWinner("WHITE");
            }
            else {
                toggleTurn();
            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            viewDelegate.setTime(timeElapsed / 1000000.0);
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!gameOver) {
            Optional<Point> point = viewDelegate.getHexDim(e.getY(), e.getX());
            point.ifPresent(this::makeMove);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
