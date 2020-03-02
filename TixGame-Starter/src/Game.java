import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Optional;

public class Game extends JPanel implements GameController.ViewDelegate {

    public final static int BOARD_WIDTH = 600;
    public final static int BOARD_HEIGHT = 700;

    private int gridDimension;

    private HexGrid hexGrid;
    private GameController gc;

    private JLabel turnLabel;
    private JLabel sizeLabel;
    private JRadioButton size3;
    private JRadioButton size5;
    private JRadioButton size7;
    private JRadioButton size9;
    private JRadioButton size11;
    private ButtonGroup group;
    private JLabel timer;
    private JLabel winner;

    public Game() {
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        gridDimension = 7;
        makeLabels();

        initGame();
    }

    private void makeLabels() {
        turnLabel = new JLabel("");

        sizeLabel = new JLabel("Board Size: ");
        group = new ButtonGroup();
        size3 = new JRadioButton("3");
        size3.addActionListener(v->resetGridDimension(3));
        size5 = new JRadioButton("5");
        size5.addActionListener(v->resetGridDimension(5));
        size7 = new JRadioButton("7");
        size7.setSelected(true);
        size7.addActionListener(v->resetGridDimension(7));
        size9 = new JRadioButton("9");
        size9.addActionListener(v->resetGridDimension(9));
        size11 = new JRadioButton("11");
        size11.addActionListener(v->resetGridDimension(11));

        group.add(size3);
        group.add(size5);
        group.add(size7);
        group.add(size9);
        group.add(size11);
        timer = new JLabel("time: ");
        winner = new JLabel("");

        JPanel southPan = new JPanel();
        southPan.add(sizeLabel);
        southPan.add(size3);
        southPan.add(size5);
        southPan.add(size7);
        southPan.add(size9);
        southPan.add(size11);
        southPan.add(timer);
        southPan.add(turnLabel);
        southPan.add(winner);

        add(southPan, BorderLayout.SOUTH);

    }

    private void resetLabel() {
        turnLabel.setText("Turn: White");
        timer.setText("time: ");
        winner.setText("");
    }

    private void initGame() {
        if(gridDimension > 10)
            Hexagon.hexagonLength = 20;
        else if(gridDimension > 6)
            Hexagon.hexagonLength = 25;
        else
            Hexagon.hexagonLength = 35;
        hexGrid = new HexGrid(gridDimension);
        add(hexGrid, BorderLayout.CENTER);

        gc = new GameController();
        gc.setSize(gridDimension);
        gc.setDelegate(this);
    }

    private void resetGridDimension(int dim) {
        resetLabel();
        if(hexGrid != null) {
            remove(hexGrid);
        }
        gridDimension = dim;
        initGame();
    }

    public void paintComponent(Graphics g) {
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        g.setColor(new Color(0x33cccc));
        g.fillRect(0,0, BOARD_WIDTH, BOARD_HEIGHT);
    }

    @Override
    public void displayTurn(String clr) {
        turnLabel.setText("Turn:" + clr);
    }

    @Override
    public void setColor(int row, int col, int clr) {
        hexGrid.setColor(row, col, clr);
    }

    @Override
    public void listen(MouseListener ml) {
        hexGrid.addMouseListener(ml);
    }

    @Override
    public Optional<Point> getHexDim(int y, int x) {
        return hexGrid.getHexDim(y, x);
    }

    @Override
    public void setTime(double ms) {
        String s = String.format("time: %.3f millisecs", ms);
        timer.setText(s);
    }

    @Override
    public void setWinner(String name) {
        turnLabel.setText("Game Over");
        winner.setText(name + " wins");
        repaint();
    }
}
