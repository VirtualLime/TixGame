public class Triple {
    private int player;
    private String treeName;
    private UnionTree tree;
    boolean present;

    public Triple(int p, String s, UnionTree t){
        player = p;
        treeName = s;
        tree = t;
        present = false;
    }


    public int getPlayer(){return player;}
    public String getTreeName(){return treeName;}
    public void setPlayer(int p){player = p;}
    public void setTreeName(String s){treeName = s;}
    public UnionTree getTree(){return tree;}
    public void setTree(UnionTree t){
        present = true;
        tree = t;
    }

    public String toString(){
        return player + " " + treeName + " tree present: " + present;
    }
}
