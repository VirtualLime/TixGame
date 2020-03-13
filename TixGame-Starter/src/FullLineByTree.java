/**
 * The FullLineByTree Class Builds, and makes adjustments to the 2-D array
 * that houses which player controls a spot, and the group that includes that
 * spot.
 */

public class FullLineByTree {
    private Pair[][] board;
    private int size;
    private boolean done;

    /**
     * The constructor for FullLineByTree accepts sz (size) creates
     * an appropriately-size 2-D array, fills it as zero/null, and sets done to false.
     * @param sz size
     */
    public FullLineByTree(int sz){
        size = sz;
        board = new Pair[size][size];
        emptyBoard();
        done = false;
    }


    public Pair[][] getArray(){return board;}

    /**
     * This method creates an empty board. All of the spaces have a Pair. The
     * Pair initially receives a 0 and a null (for player and UnionTree).
     */
    private void emptyBoard(){
        for(int i = 0; i < size; i++){
            for(int m = 0; m < size; m++){
                Pair pair = new Pair(0,null);
                board[i][m] = pair;
            }
        }
    }

    /**
     * This is the method dealing with all of the checking and rearranging
     * for the entire program. It checks the surrounding spots of the selected
     * spot, sees if they are occupied by the same player. If they are, it sends
     * out for combining the arrays. If no surround spots (owned by the same player)
     * are found, it creates a new tree with that spots player-specific value.
     * @param row the row of the spot
     * @param column the column of the spot
     * @param playerIn the player taking that spot
     * @return a boolean regarding win condition, any time done is true, it stops and returns it
     */
    public boolean makeMove(int row, int column, int playerIn){
        boolean foundTree = false; //This boolean, if true, prevents creating a new tree
        int count = 0;
        int alter;// the value sent to the (tree) array to represent this spot
        int infoRow = row - 1;
        int infoColumn = column;
        if (playerIn == -1){alter = row + 1;}
        else{alter = column + 1;}
        Pair pair = board[row][column];
        UnionTree tree = pair.getTree();
        Pair infoPair;
        UnionTree infoTree;

        while(!done && count < 6){//This process will go through the six adjacent spots
            if(infoRow >= 0 && infoRow < size && infoColumn >= 0 && infoColumn < size){
                infoPair = board[infoRow][infoColumn];//the compared Pair
                int comparedPlayer = infoPair.getPlayer();
                infoTree = infoPair.getTree();
                if(comparedPlayer == playerIn){
                    if(!foundTree && !infoTree.checkIfReplaces()){//compared tree is its current array
                        pair.setTree(infoTree);
                        pair.setPlayer(playerIn);
                        tree = infoTree;
                        done = tree.addToArray(alter);
                        if(done){return true;}
                        foundTree = true;
                    }
                    else if(!foundTree && infoTree.checkIfReplaces()){//compared tree doesn't have the latest array, search for it
                        int r1 = infoRow;
                        int c1 = infoColumn;
                        UnionTree tempTree = infoTree;
                        boolean found = false;
                        while(!found){
                            r1 = tempTree.row;
                            c1 = tempTree.column;
                            tempTree = board[r1][c1].getTree();
                            found = !tempTree.replaced;
                        }
                        pair.setTree(board[r1][c1].getTree());
                        pair.setPlayer(playerIn);
                        tree = pair.getTree();
                        done = tree.addToArray(alter);
                        if(done){return true;}
                        foundTree = true;
                    }
                    else{//the spot currently has a tree, further updates possible
                        if(tree.row == infoTree.row
                                && tree.column == infoTree.column){}//indicates both trees are in the same group
                        else{
                            int r1 = row-1;
                            int c1 = column;
                            UnionTree tempTree = infoTree;
                            boolean found = tree.checkIfReplaces();
                            while(!found){//searches for the current group location
                                r1 = tempTree.row;
                                c1 = tempTree.column;
                                tempTree = board[r1][c1].getTree();
                                found = !tempTree.checkIfReplaces();
                            }
                            tempTree.replaceRC(row,column);//the active tree is now referenced as most up-to-date
                            done = tree.addArray(tempTree.getArray());
                            if(done){return done;}
                            foundTree = true;
                        }
                    }
                }
            }
            switch(count){//A switch statement going through the adjacent spots
                case 5:
                    count = 6;
                    infoRow = row - 1;
                    infoColumn = column;
                    break;
                case 4:
                    count = 5;
                    infoRow = row;
                    infoColumn = column + 1;
                    break;
                case 3:
                    count = 4;
                    infoRow = row + 1;
                    infoColumn = column - 1;
                    break;
                case 2:
                    count = 3;
                    infoRow = row + 1;
                    infoColumn = column;
                    break;
                case 1:
                    count = 2;
                    infoRow = row - 1;
                    infoColumn = column + 1;
                    break;
                case 0:
                    count = 1;
                    infoRow = row;
                    infoColumn = column - 1;
                    break;
            }
        }
        if(!foundTree && !done){//No tree has been added to this spot (after all 6 adjacent spots), so a new one is created
            if(playerIn == -1){
                tree = new UnionTree(size,row,column,row+1, -1);
                pair.setPlayer(playerIn);
                pair.setTree(tree);
            }
            if(playerIn == 1){
                tree = new UnionTree(size,row,column,column+1,1);
                pair.setPlayer(playerIn);
                pair.setTree(tree);
            }
        }
        return done;
    }
}
