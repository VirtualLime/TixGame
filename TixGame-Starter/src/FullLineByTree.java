import java.util.ArrayList;

public class FullLineByTree {
    private Pair[][] board;
    private int size,trees;
    boolean done;

    public FullLineByTree(int s){
        size = s;
        board = new Pair[size][size];
        emptyBoard();
        trees = size * size;
        done = false;
    }


    public Pair[][] getArray(){return board;}

    private void emptyBoard(){
        for(int i = 0; i < size; i++){
            for(int m = 0; m < size; m++){
                Pair pair = new Pair(0,null);
                board[i][m] = pair;
            }
        }
    }

    public boolean makeMove(int row, int column, int p){
        boolean foundTree = false;
        int count = 0;
        int alter;
        int r = row - 1;
        int c = column;
        if (p == -1){alter = row + 1;}
        else{alter = column + 1;}
        Pair pair = board[row][column];
        UnionTree tree = pair.getTree();
        Pair infoPair;
        UnionTree infoTree;

        while(!done && count < 6){
            if(r >= 0 && r < size && c >= 0 && c < size){
                infoPair = board[r][c];
                int comparedPlayer = infoPair.getPlayer();
                infoTree = infoPair.getTree();
                if(comparedPlayer == p){
                    if(!foundTree && !infoTree.checkIfReplaces()){
                        pair.setTree(infoTree);
                        pair.setPlayer(p);
                        tree = infoTree;
                        done = tree.addToArray(alter);
                        if(done){return true;}
                        foundTree = true;
                    }
                    else if(!foundTree && infoTree.checkIfReplaces()){
                        int r1 = r;
                        int c1 = c;
                        UnionTree tempTree = infoTree;
                        boolean found = false;
                        while(!found){
                            r1 = tempTree.row;
                            c1 = tempTree.column;
                            tempTree = board[r1][c1].getTree();
                            found = !tempTree.replaced;
                        }
                        pair.setTree(board[r1][c1].getTree());
                        pair.setPlayer(p);
                        tree = pair.getTree();
                        done = tree.addToArray(alter);
                        if(done){return true;}
                        foundTree = true;
                    }
                    else{
                        if(tree.row == infoTree.row
                                && tree.column == infoTree.column){}
                        else{
                            int r1 = row-1;
                            int c1 = column;
                            UnionTree tempTree = infoTree;
                            boolean found = tree.checkIfReplaces();
                            while(!found){
                                r1 = tempTree.row;
                                c1 = tempTree.column;
                                tempTree = board[r1][c1].getTree();
                                found = !tempTree.checkIfReplaces();
                            }
                            tempTree.replaceRC(row,column);
                            done = tree.addArray(tempTree.getArray());
                            if(done){return done;}
                            foundTree = true;
                        }
                    }
                }
            }
            switch(count){
                case 5:
                    count = 6;
                    r = row - 1;
                    c = column;
                    break;
                case 4:
                    count = 5;
                    r = row;
                    c = column + 1;
                    break;
                case 3:
                    count = 4;
                    r = row + 1;
                    c = column - 1;
                    break;
                case 2:
                    count = 3;
                    r = row + 1;
                    c = column;
                    break;
                case 1:
                    count = 2;
                    r = row - 1;
                    c = column + 1;
                    break;
                case 0:
                    count = 1;
                    r = row;
                    c = column - 1;
                    break;
            }
        }
        if(!foundTree && !done){
            if(p == -1){
                tree = new UnionTree(size,row,column,row+1, -1);
                pair.setPlayer(p);
                pair.setTree(tree);
            }
            if(p == 1){
                tree = new UnionTree(size,row,column,column+1,1);
                pair.setPlayer(p);
                pair.setTree(tree);
            }
        }
        return done;
    }
}
