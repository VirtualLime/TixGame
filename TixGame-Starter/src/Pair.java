/**
 * This is a Pair class designed to keep important information together rather than
 * spread through multiple arrays. The instance variables are an int for the player
 * identifier and UnionTree for the group found on that board space.
 */

public class Pair {
    private int player;
    private UnionTree tree;

    /**
     * The constructor for Pair accepts an int p (for the player) and a
     * UnionTree t (for tree).
     * @param p
     * @param t
     */
    public Pair(int p, UnionTree t){
        player = p;
        tree = t;
    }

    /**
     * These are a series of getters and setters to adjust the information in the
     * Pair, or to send it elsewhere.
     */
    public int getPlayer(){return player;}
    public void setPlayer(int p){player = p;}
    public UnionTree getTree(){return tree;}
    public void setTree(UnionTree t){tree = t;}

    /**
     * A toString method to return information about the pair.
     */
    public String toString(){return "player: " + player + " " + "tree: " + tree;}
}
