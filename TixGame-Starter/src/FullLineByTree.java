import java.util.ArrayList;

public class FullLineByTree {
    private Triple[][] board;
    private int size,trees,currentTree;
    ArrayList<String> treeNames;

    public FullLineByTree(int s){
        size = s;
        currentTree = 0;
        board = new Triple[size][size];
        emptyBoard();
        treeNames = new ArrayList<>();
        trees = size * size;
        fillnames();
    }


    public Triple[][] getArray(){return board;}

    private void fillnames(){
        for(int i = 0; i < trees; i++){
            String name = "a" + i;
            treeNames.add(name);
        }
    }

    private void emptyBoard(){
        for(int i = 0; i < size; i++){
            for(int m = 0; m < size; m++){
                Triple t = new Triple(0,"", null);
                board[i][m] = t;
            }
        }
    }

    public boolean makeMove(int p, int column, int row){
        Triple triple = board[row][column];
        triple.setTreeName(treeNames.get(currentTree));
        triple.setPlayer(p);
        currentTree++;
        boolean foundTree = false;
        if(p == -1) {
            if (row - 1 >= 0) {
                //System.out.println("p: " + p + " compared p: " + board[row-1][column].getPlayer());
                if(board[row-1][column].getPlayer()==p){
                    //System.out.println(board[row-1][column].getTree().checkIfReplaces());
                    if(!foundTree && !board[row-1][column].getTree().checkIfReplaces()){//System.out.println("1a");
                        board[row][column].setTreeName(board[row-1][column].getTreeName());
                        board[row][column].setTree(board[row-1][column].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row-1][column].getTree().checkIfReplaces()){//System.out.println("1b");
                        int r = row-1;
                        int c = column;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row-1][column].getTree().row
                            && board[row][column].getTree().column == (board[row-1][column].getTree().column)){}
                        else{//System.out.println("1c");
                            int r = row-1;
                            int c = column;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = tree.checkIfReplaces();
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = tree.checkIfReplaces();
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
                //else if(){}
            }
            if (column - 1 >= 0) {
                if(board[row][column-1].getPlayer()==p){
                    if(!foundTree && !board[row][column-1].getTree().replaced){//System.out.println("2a");
                        board[row][column].setTreeName(board[row][column-1].getTreeName());
                        board[row][column].setTree(board[row][column-1].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row][column-1].getTree().replaced){//System.out.println("2b");
                        int r = row;
                        int c = column-1;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row][column-1].getTree().row
                                && board[row][column].getTree().column == (board[row][column-1].getTree().column)){}
                        else{//System.out.println("2c");
                            int r = row;
                            int c = column-1;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row - 1 >= 0 && column + 1 < size) {
                if(board[row-1][column+1].getPlayer()==p){
                    if(!foundTree && !board[row-1][column+1].getTree().replaced){//System.out.println("3a");
                        board[row][column].setTreeName(board[row-1][column+1].getTreeName());
                        board[row][column].setTree(board[row-1][column+1].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row-1][column+1].getTree().replaced){//System.out.println("3b");
                        int r = row-1;
                        int c = column+1;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row-1][column+1].getTree().row
                                && board[row][column].getTree().column == (board[row-1][column+1].getTree().column)){}
                        else{//System.out.println("3c");
                            int r = row-1;
                            int c = column+1;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row + 1 < size) {
                if(board[row+1][column].getPlayer()==p){
                    if(!foundTree && !board[row+1][column].getTree().replaced){
                        board[row][column].setTreeName(board[row+1][column].getTreeName());
                        board[row][column].setTree(board[row+1][column].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row+1][column].getTree().replaced){
                        int r = row+1;
                        int c = column;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row+1][column].getTree().row
                                && board[row][column].getTree().column == (board[row+1][column].getTree().column)){}
                        else{
                            int r = row+1;
                            int c = column;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row + 1 < size && column - 1 >= 0) {
                if(board[row+1][column-1].getPlayer()==p){
                    if(!foundTree && !board[row+1][column-1].getTree().replaced){
                        board[row][column].setTreeName(board[row+1][column-1].getTreeName());
                        board[row][column].setTree(board[row+1][column-1].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row+1][column-1].getTree().replaced){
                        int r = row+1;
                        int c = column-1;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row+1][column-1].getTree().row
                                && board[row][column].getTree().column == (board[row+1][column-1].getTree().column)){}
                        else{
                            int r = row+1;
                            int c = column-1;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (column + 1 < size) {
                if(board[row][column+1].getPlayer()==p){
                    if(!foundTree && !board[row][column+1].getTree().replaced){
                        board[row][column].setTreeName(board[row][column+1].getTreeName());
                        board[row][column].setTree(board[row][column+1].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row][column+1].getTree().replaced){
                        int r = row;
                        int c = column+1;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row][column+1].getTree().row
                                && board[row][column].getTree().column == (board[row][column+1].getTree().column)){}
                        else{
                            int r = row;
                            int c = column+1;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
        }
        if(p == 1)
        {if (row - 1 >= 0) {
            if(board[row-1][column].getPlayer()==p){
                if(!foundTree && !board[row-1][column].getTree().replaced){
                    board[row][column].setTreeName(board[row-1][column].getTreeName());
                    board[row][column].setTree(board[row-1][column].getTree());
                    board[row][column].setPlayer(p);
                    UnionTree tree = board[row][column].getTree();
                    tree.add(column+1);
                    foundTree = true;
                    if(tree.haveAWinner()){return true;}
                }
                else if(!foundTree && board[row-1][column].getTree().replaced){
                    int r = row-1;
                    int c = column;
                    UnionTree tree = board[r][c].getTree();
                    boolean found = false;
                    while(!found){
                        r = tree.row;
                        c = tree.column;
                        tree = board[r][c].getTree();
                        found = !tree.replaced;
                    }
                    board[row][column].setTreeName(board[r][c].getTreeName());
                    board[row][column].setTree(board[r][c].getTree());
                    board[row][column].setPlayer(p);
                    tree = board[row][column].getTree();
                    tree.add(column+1);
                    foundTree = true;
                    if(tree.haveAWinner()){return true;}
                }
                else{
                    if(board[row][column].getTree().row == board[row-1][column].getTree().row
                            && board[row][column].getTree().column == (board[row-1][column].getTree().column)){}
                    else{
                        int r = row-1;
                        int c = column;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = !tree.replaced;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        UnionTree thisTree = board[row][column].getTree();
                        thisTree.replaceRC(r,c);
                        tree.addArray(thisTree.getArray());
                        //board[row][column].setTreeName();
                        if(tree.haveAWinner()){return true;}
                    }
                }
            }
        }
            if (column - 1 >= 0) {
                if(board[row][column-1].getPlayer()==p){
                    if(!foundTree && !board[row][column-1].getTree().replaced){
                        board[row][column].setTreeName(board[row][column-1].getTreeName());
                        board[row][column].setTree(board[row][column-1].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row][column-1].getTree().replaced){
                        int r = row;
                        int c = column-1;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row][column-1].getTree().row
                                && board[row][column].getTree().column == (board[row][column-1].getTree().column)){}
                        else{
                            int r = row;
                            int c = column-1;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row - 1 >= 0 && column + 1 < size) {
                if(board[row-1][column+1].getPlayer()==p){
                    if(!foundTree && !board[row-1][column+1].getTree().replaced){
                        board[row][column].setTreeName(board[row-1][column+1].getTreeName());
                        board[row][column].setTree(board[row-1][column+1].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row-1][column+1].getTree().replaced){
                        int r = row-1;
                        int c = column+1;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row-1][column+1].getTree().row
                                && board[row][column].getTree().column == (board[row-1][column+1].getTree().column)){}
                        else{
                            int r = row-1;
                            int c = column+1;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row + 1 < size) {
                if(board[row+1][column].getPlayer()==p){
                    if(!foundTree && !board[row+1][column].getTree().replaced){
                        board[row][column].setTreeName(board[row+1][column].getTreeName());
                        board[row][column].setTree(board[row+1][column].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row+1][column].getTree().replaced){
                        int r = row+1;
                        int c = column;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row+1][column].getTree().row
                                && board[row][column].getTree().column == (board[row+1][column].getTree().column)){}
                        else{
                            int r = row+1;
                            int c = column;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row + 1 < size && column - 1 >= 0) {
                if(board[row+1][column-1].getPlayer()==p){
                    if(!foundTree && !board[row+1][column-1].getTree().replaced){
                        board[row][column].setTreeName(board[row+1][column-1].getTreeName());
                        board[row][column].setTree(board[row+1][column-1].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row+1][column-1].getTree().replaced){
                        int r = row+1;
                        int c = column-1;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row+1][column-1].getTree().row
                                && board[row][column].getTree().column == (board[row+1][column-1].getTree().column)){}
                        else{
                            int r = row+1;
                            int c = column-1;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (column + 1 < size) {
                if(board[row][column+1].getPlayer()==p){
                    if(!foundTree && !board[row][column+1].getTree().replaced){
                        board[row][column].setTreeName(board[row][column+1].getTreeName());
                        board[row][column].setTree(board[row][column+1].getTree());
                        board[row][column].setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && board[row][column+1].getTree().replaced){
                        int r = row;
                        int c = column+1;
                        UnionTree tree = board[r][c].getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        board[row][column].setTreeName(board[r][c].getTreeName());
                        board[row][column].setTree(board[r][c].getTree());
                        board[row][column].setPlayer(p);
                        tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(board[row][column].getTree().row == board[row][column+1].getTree().row
                                && board[row][column].getTree().column == (board[row][column+1].getTree().column)){}
                        else{
                            int r = row;
                            int c = column+1;
                            UnionTree tree = board[r][c].getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = board[row][column].getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            /*
            if (row - 1 >= 0) {
            }
            if (column - 1 >= 0) {
            }
            if (row - 1 >= 0 && column + 1 < size) {
            }
            if (row + 1 < size) {
            }
            if (row + 1 < size && column + 1 < size) {
            }
            if (column + 1 < size) {
            }*/
        }
        if(!foundTree){
            if(p == -1){
                UnionTree tree = new UnionTree(size,row,column,row+1, -1);
                board[row][column].setTree(tree);
                System.out.println("tree parent: "+ board[row][column].getTree().getParent());
            }
            if(p == 1){
                UnionTree tree = new UnionTree(size,row,column,column+1,1);
                board[row][column].setTree(tree);
                System.out.println("tree parent: "+ board[row][column].getTree().getParent());
            }
        }
        //StringIntPair pair = board[row][column];
        /*if(p == -1) {
            pair.setSpot(row + 1);
            pair.setPlayer(-1);
        }
        else{
            pair.setSpot(column + 1);
            pair.setPlayer(1);
        }
        int place = 0;
        if(p == 1){place = column + 1;}
        else{place = row + 1;}
        return checkNeighbours(place, row, column, p);*/

        return false;
    }

    private boolean checkNeighbours(int place, int r, int c, int p){
        boolean neighbours = false;

        return neighbours;

    }
}
