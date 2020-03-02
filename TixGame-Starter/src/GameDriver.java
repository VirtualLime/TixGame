import javax.swing.*;
import java.awt.*;


/**
 * Run this class to play the game
 */
public class GameDriver {

    public static void main(String [] args) {


        JFrame frame = new JFrame("Grid Game");

        Game game = new Game();
        frame.add(game);

        frame.setSize(new Dimension(game.BOARD_WIDTH,game.BOARD_HEIGHT));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
