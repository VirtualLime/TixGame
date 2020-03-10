import java.util.ArrayList;

public class FullLineByTree {
    private Triple[][] board;
    private int size,trees,currentTree;
    //ArrayList<String> treeNames;
    boolean done;

    public FullLineByTree(int s){
        size = s;
        currentTree = 0;
        board = new Triple[size][size];
        emptyBoard();
        //treeNames = new ArrayList<>();
        trees = size * size;
        done = false;
        //fillnames();
    }


    public Triple[][] getArray(){return board;}

    private void fillnames(){
        for(int i = 0; i < trees; i++){
            String name = "a" + i;
            //treeNames.add(name);
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

    //private boolean makeMove(int row, int column, Triple triple,
                             //int newColumn, int newRow, boolean prevFound, int p){
    public boolean makeMove(int row, int column, int p){
        boolean foundTree = false;
        int count = 0;
        int stageCount = 0;
        int alter;
        int r = row - 1;
        int c = column;
        if (p == -1){alter = row + 1;}
        else{alter = column + 1;}
        Triple triple = board[row][column];
        UnionTree tree = triple.getTree();
        Triple infoTriple;
        UnionTree infoTree;

        while(!done && count < 6){
            if(r >= 0 && r < size && c >= 0 && c < size){
                infoTriple = board[r][c];
                int comparedPlayer = infoTriple.getPlayer();
                infoTree = infoTriple.getTree();
                if(comparedPlayer == 0 || comparedPlayer == -p){}
                else{
                    if(!foundTree && !infoTree.checkIfReplaces()){
                        //infoTriple.setTreeName(board[row-1][column].getTreeName());
                        triple.setTree(infoTree);
                        triple.setPlayer(p);
                        tree = infoTree;
                        done = tree.addToArray(alter);
                        if(done){return true;}
                        foundTree = true;
                    }
                    else if(!foundTree && infoTree.checkIfReplaces()){//System.out.println("1b");
                        int r1 = r;
                        int c1 = c;
                        UnionTree tempTree = infoTree;
                        //UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r1 = tree.row;
                            c1 = tree.column;
                            tempTree = board[r1][c1].getTree();
                            found = !tree.replaced;
                        }
                        //triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r1][c1].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        done = tree.addToArray(alter);
                        if(done){return true;}
                        foundTree = true;
                        //if(tree.haveAWinner()){return true;}
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
                                r1 = tree.row;
                                c1 = tree.column;
                                tempTree = board[r1][c1].getTree();
                                found = tree.checkIfReplaces();
                            }
                            //UnionTree thisTree = triple.getTree();
                            tree.replaceRC(r1,c1);
                            done = tree.addArray(tempTree.getArray());
                            if(done){return done;}
                            foundTree = true;
                            //board[row][column].setTreeName();
                            //if(tree.haveAWinner()){return true;}
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
                    c = column + 1;
                    break;
            }
        }
        if(!foundTree && !done){
            if(p == -1){
                tree = new UnionTree(size,row,column,row+1, -1);
                triple.setPlayer(p);
                triple.setTree(tree);
                //triple.setTree(tree);
                //System.out.println("tree parent: "+ board[row][column].getTree().getParent());
            }
            if(p == 1){
                tree = new UnionTree(size,row,column,column+1,1);
                triple.setPlayer(p);
                triple.setTree(tree);
                //triple.setTree(tree);
                //System.out.println("tree parent: "+ board[row][column].getTree().getParent());
            }
        }
        //return  foundTree;
        return done;
        /*if(!foundTree && !infoTriple.getTree().checkIfReplaces()){//System.out.println("1a");
            infoTriple.setTreeName(board[row-1][column].getTreeName());
            triple.setTree(infoTriple.getTree());
            triple.setPlayer(p);
            UnionTree tree = triple.getTree();
            done = tree.addToArray(row+1);
            if(done){return true;}
            foundTree = true;
            //if(tree.haveAWinner()){return true;}
        }
        else if(!foundTree && infoTriple.getTree().checkIfReplaces()){//System.out.println("1b");
            int r = row-1;
            int c = column;
            UnionTree tree = infoTriple.getTree();
            boolean found = false;
            while(!found){
                r = tree.row;
                c = tree.column;
                tree = board[r][c].getTree();
                found = !tree.replaced;
            }
            triple.setTreeName(board[r][c].getTreeName());
            triple.setTree(board[r][c].getTree());
            triple.setPlayer(p);
            tree = triple.getTree();
            done = tree.addToArray(row+1);
            if(done){return true;}
            foundTree = true;
            //if(tree.haveAWinner()){return true;}
        }
        else{
            if(triple.getTree().row == infoTriple.getTree().row
                    && triple.getTree().column == (infoTriple.getTree().column)){}
            else{//System.out.println("1c");
                int r = row-1;
                int c = column;
                UnionTree tree = triple.getTree();
                boolean found = tree.checkIfReplaces();
                while(!found){
                    r = tree.row;
                    c = tree.column;
                    tree = board[r][c].getTree();
                    found = tree.checkIfReplaces();
                }
                UnionTree thisTree = triple.getTree();
                thisTree.replaceRC(r,c);
                done = tree.addArray(thisTree.getArray());
                //board[row][column].setTreeName();
                if(tree.haveAWinner()){return true;}
            }
        }
        return foundTree;*/
    }

    /*public boolean makeMove(int p, int column, int row){
        Triple triple = board[row][column];
        //triple.setTreeName(treeNames.get(currentTree));
        triple.setPlayer(p);
        currentTree++;
        boolean foundTree = false;
        Triple infoTriple;
        if(p == -1) {
            if (row - 1 >= 0) {
                //System.out.println("p: " + p + " compared p: " + board[row-1][column].getPlayer());
                infoTriple = board[row-1][column];
                if(infoTriple.getPlayer()==p){
                    //System.out.println(board[row-1][column].getTree().checkIfReplaces());
                    if(!foundTree && !infoTriple.getTree().checkIfReplaces()){//System.out.println("1a");
                        infoTriple.setTreeName(board[row-1][column].getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().checkIfReplaces()){//System.out.println("1b");
                        int r = row-1;
                        int c = column;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                            && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{//System.out.println("1c");
                            int r = row-1;
                            int c = column;
                            UnionTree tree = triple.getTree();
                            boolean found = tree.checkIfReplaces();
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = tree.checkIfReplaces();
                            }
                            UnionTree thisTree = triple.getTree();
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
                infoTriple = board[row][column-1];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){//System.out.println("2a");
                        triple.setTreeName(infoTriple.getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){//System.out.println("2b");
                        int r = row;
                        int c = column-1;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{//System.out.println("2c");
                            int r = row;
                            int c = column-1;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row - 1 >= 0 && column + 1 < size) {
                infoTriple = board[row-1][column+1];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){//System.out.println("3a");
                        triple.setTreeName(infoTriple.getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){//System.out.println("3b");
                        int r = row-1;
                        int c = column+1;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{//System.out.println("3c");
                            int r = row-1;
                            int c = column+1;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row + 1 < size) {
                infoTriple = board[row+1][column];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){
                        triple.setTreeName(infoTriple.getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){
                        int r = row+1;
                        int c = column;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{
                            int r = row+1;
                            int c = column;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row + 1 < size && column - 1 >= 0) {
                infoTriple = board[row+1][column-1];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){
                        triple.setTreeName(board[row+1][column-1].getTreeName());
                        triple.setTree(board[row+1][column-1].getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){
                        int r = row+1;
                        int c = column-1;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{
                            int r = row+1;
                            int c = column-1;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (column + 1 < size) {
                infoTriple = board[row][column+1];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){
                        triple.setTreeName(infoTriple.getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){
                        int r = row;
                        int c = column+1;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(row+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{
                            int r = row;
                            int c = column+1;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
        }
        if(p == 1){
            if (row - 1 >= 0) {
                //System.out.println("p: " + p + " compared p: " + board[row-1][column].getPlayer());
                infoTriple = board[row-1][column];
                if(infoTriple.getPlayer()==p){
                    //System.out.println(board[row-1][column].getTree().checkIfReplaces());
                    if(!foundTree && !infoTriple.getTree().checkIfReplaces()){//System.out.println("1a");
                        infoTriple.setTreeName(board[row-1][column].getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = board[row][column].getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().checkIfReplaces()){//System.out.println("1b");
                        int r = row-1;
                        int c = column;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{//System.out.println("1c");
                            int r = row-1;
                            int c = column;
                            UnionTree tree = triple.getTree();
                            boolean found = tree.checkIfReplaces();
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = tree.checkIfReplaces();
                            }
                            UnionTree thisTree = triple.getTree();
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
                infoTriple = board[row][column-1];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){//System.out.println("2a");
                        triple.setTreeName(infoTriple.getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){//System.out.println("2b");
                        int r = row;
                        int c = column-1;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{//System.out.println("2c");
                            int r = row;
                            int c = column-1;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row - 1 >= 0 && column + 1 < size) {
                infoTriple = board[row-1][column+1];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){//System.out.println("3a");
                        triple.setTreeName(infoTriple.getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){//System.out.println("3b");
                        int r = row-1;
                        int c = column+1;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{//System.out.println("3c");
                            int r = row-1;
                            int c = column+1;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row + 1 < size) {
                infoTriple = board[row+1][column];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){
                        triple.setTreeName(infoTriple.getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){
                        int r = row+1;
                        int c = column;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{
                            int r = row+1;
                            int c = column;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (row + 1 < size && column - 1 >= 0) {
                infoTriple = board[row+1][column-1];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){
                        triple.setTreeName(board[row+1][column-1].getTreeName());
                        triple.setTree(board[row+1][column-1].getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){
                        int r = row+1;
                        int c = column-1;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{
                            int r = row+1;
                            int c = column-1;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
            if (column + 1 < size) {
                infoTriple = board[row][column+1];
                if(infoTriple.getPlayer()==p){
                    if(!foundTree && !infoTriple.getTree().replaced){
                        triple.setTreeName(infoTriple.getTreeName());
                        triple.setTree(infoTriple.getTree());
                        triple.setPlayer(p);
                        UnionTree tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else if(!foundTree && infoTriple.getTree().replaced){
                        int r = row;
                        int c = column+1;
                        UnionTree tree = infoTriple.getTree();
                        boolean found = false;
                        while(!found){
                            r = tree.row;
                            c = tree.column;
                            tree = board[r][c].getTree();
                            found = !tree.replaced;
                        }
                        triple.setTreeName(board[r][c].getTreeName());
                        triple.setTree(board[r][c].getTree());
                        triple.setPlayer(p);
                        tree = triple.getTree();
                        tree.add(column+1);
                        foundTree = true;
                        if(tree.haveAWinner()){return true;}
                    }
                    else{
                        if(triple.getTree().row == infoTriple.getTree().row
                                && triple.getTree().column == (infoTriple.getTree().column)){}
                        else{
                            int r = row;
                            int c = column+1;
                            UnionTree tree = infoTriple.getTree();
                            boolean found = !tree.replaced;
                            while(!found){
                                r = tree.row;
                                c = tree.column;
                                tree = board[r][c].getTree();
                                found = !tree.replaced;
                            }
                            UnionTree thisTree = triple.getTree();
                            thisTree.replaceRC(r,c);
                            tree.addArray(thisTree.getArray());
                            //board[row][column].setTreeName();
                            if(tree.haveAWinner()){return true;}
                        }
                    }
                }
            }
        }
            /*if (row - 1 >= 0) {
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
            }
        }*/
        /*if(!foundTree){
            if(p == -1){
                UnionTree tree = new UnionTree(size,row,column,row+1, -1);
                triple.setTree(tree);
                //System.out.println("tree parent: "+ board[row][column].getTree().getParent());
            }
            if(p == 1){
                UnionTree tree = new UnionTree(size,row,column,column+1,1);
                triple.setTree(tree);
                //System.out.println("tree parent: "+ board[row][column].getTree().getParent());
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

      //  return false;
    //}

    private boolean checkNeighbours(int place, int r, int c, int p){
        boolean neighbours = false;

        return neighbours;

    }
}
