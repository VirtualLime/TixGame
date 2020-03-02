import javax.swing.*;
import java.awt.*;
import java.util.Optional;


/**
 * Gui Component to draw the shape and pieces of the game board
 */
public class HexGrid extends JComponent{

    int size;
    Hexagon[][] hexagons;

    /**
     * Create a Hex Grid with size num x num
     * @param num
     */
    public HexGrid(int num) {
        setPreferredSize(new Dimension(600,700));
        size = num;
        hexagons = new Hexagon[size][size];
        makeHexagons();
    }

    /**
     * create the spots that you can play into
     */
    private void makeHexagons() {

        int startY = (int) (Game.BOARD_WIDTH/ 2.0 - size* (Hexagon.hexagonLength-2));
        int startX = (int) (Game.BOARD_HEIGHT/ 2.0 - size* (Hexagon.hexagonLength+9));
        int y;
        int x;

        for(int row = 0; row < size; row++) {
            y = startY + row * (Hexagon.hexagonLength-2) * 2;
            x = startX + row * (Hexagon.hexagonLength -2);
            for(int col = 0; col < size; col++) {
                int newX = x + col * (Hexagon.hexagonLength) * 2;
                //the x's and y's got a bit twisted.
                //fix for readability in the future
                hexagons[col][row] = new Hexagon(y, newX, Color.LIGHT_GRAY);
            }
        }
    }

    /**
     * set the color of a playing piece
     * @param row
     * @param col
     * @param color
     */
    public void setColor(int row, int col, int color) {
        if (color == GameController.WHITE) {
            hexagons[row][col].setColor(Color.WHITE);
        }
        else
            hexagons[row][col].setColor(Color.BLACK);
        repaint();
    }

    /**
     * Return the array position of the hex corresponding to the given mouse click location
     * @param y mouse y (row)
     * @param x mouse x coordinate (column)
     * @return the corresponding location of the hexagon in the board
     */
    public Optional<Point> getHexDim(int y, int x) {
        for(int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(hexagons[row][col].getPolygon().contains(x, y) ){
                    return Optional.of(new Point(col, row));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void paintComponent(Graphics g) {
        for(Hexagon[] hs : hexagons) {
            for(Hexagon h: hs) {
                h.draw(g);
            }
        }
    }

}
