public class Pair {private int player;
    private UnionTree tree;
    boolean present;

    public Pair(int p, UnionTree t){
        player = p;
        tree = t;
        present = false;
    }


    public int getPlayer(){return player;}
    public void setPlayer(int p){player = p;}
    public UnionTree getTree(){return tree;}
    public void setTree(UnionTree t){
        present = true;
        tree = t;
    }

    public String toString(){
        return player + " " + "tree: " + tree;
    }

}
