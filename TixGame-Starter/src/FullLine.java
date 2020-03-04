public class FullLine {
    private Pair[][] board;
    private int size;

    public FullLine(int s){
        size = s;
        board = new Pair[size][size];
        emptyBoard();
    }

    private void emptyBoard(){
        for(int i = 0; i < size; i++){
            for(int m = 0; m < size; m++){
                Pair p = new Pair(0,0);
                board[i][m] = p;
            }
        }
    }

    public boolean makeMove(int p, int column, int row){
        Pair pair = board[row][column];
        if(p == -1) {
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
        return checkNeighbours(place, row, column, p);

        //return false;
    }

    private boolean checkNeighbours(int place, int r, int c, int p){
        //if p == -1 && any r == size - 1 and has a spot of 1, return true
        //if p == 1 && any c == size-1 and has a pot of 1, return true
        boolean finished = false;
        if(r - 1 >= 0){
            if(board[r-1][c].getPlayer() == p){
                if(board[r-1][c].getSpot() < place){
                    checkNeighbours(board[r-1][c].getSpot(), r-1, c, p);
                }
                if(board[r-1][c].getSpot() > place){
                    board[r-1][c].setSpot(place);
                    checkNeighbours(place, r-1, c, p);
                }
            }
        }
        if(c - 1 >= 0){
            if(board[r][c-1].getPlayer() == p){
                if(board[r][c-1].getSpot() < place){
                    checkNeighbours(board[r][c-1].getSpot(), r, c-1, p);
                }
                if(board[r][c-1].getSpot() > place){
                    board[r][c-1].setSpot(place);
                    checkNeighbours(place, r, c-1, p);
                }
            }
        }
        if(r - 1 >= 0 && c+1 < size){
            if(board[r-1][c+1].getPlayer() == p){
                if(board[r-1][c+1].getSpot() < place){
                    checkNeighbours(board[r-1][c].getSpot(), r-1, c+1, p);
                }
                if(board[r-1][c+1].getSpot() > place){
                    board[r-1][c+1].setSpot(place);
                    checkNeighbours(place, r-1, c+1, p);
                }
            }
            if(board[r-1][c+1].getPlayer() ==p && p == 1 && c+1 == size -1 &&
                    board[r-1][c+1].getSpot() == 1){printBoard(); System.out.println("True");return true;}
        }
        if(c+1 < size){
            if(board[r][c+1].getPlayer() == p){
                if(board[r][c+1].getSpot() < place){
                    checkNeighbours(board[r][c+1].getSpot(), r, c+1, p);
                }
                if(board[r][c+1].getSpot() > place){
                    board[r][c+1].setSpot(place);
                    checkNeighbours(place, r, c+1, p);
                }
            }
            if(board[r][c+1].getPlayer() ==p && p == 1 && c+1 == size -1 &&
                    board[r][c+1].getSpot() == 1){printBoard(); System.out.println("True");return true;}
        }
        if(r+1 < size){
            if(board[r+1][c].getPlayer() == p){
                if(board[r+1][c].getSpot() < place){
                    checkNeighbours(board[r+1][c].getSpot(), r+1, c, p);
                }
                if(board[r+1][c].getSpot() > place){
                    board[r+1][c].setSpot(place);
                    checkNeighbours(place, r+1, c, p);
                }
            }
            if(board[r+1][c].getPlayer() ==p && p == -1 && r+1 == size -1 &&
                    board[r+1][c].getSpot() == 1){printBoard(); System.out.println("True");return true;}
        }
        if(r+1 < size && c-1 >= 0){
            if(board[r+1][c-1].getPlayer() == p){
                if(board[r+1][c-1].getSpot() < place){
                    checkNeighbours(board[r+1][c-1].getSpot(), r+1, c-1, p);
                }
                if(board[r+1][c-1].getSpot() > place){
                    board[r+1][c-1].setSpot(place);
                    checkNeighbours(place, r+1, c-1, p);
                }
            }
            if(board[r+1][c-1].getPlayer() ==p && p == -1 && r+1 == size -1 &&
                    board[r+1][c-1].getSpot() == 1){printBoard();System.out.println("True");return true;}
        }
        if(r == size - 1 && p == -1 && board[r][c].getSpot() == 1){return true;}
        if(c == size - 1 && p == 1 && board[r][c].getSpot() == 1){return true;}
        printBoard();

        return finished;
    }

    private void printBoard(){
        for(int i = 0; i < size; i++){
            for(int m = 0; m < size; m++){
                Pair spot = board[i][m];
                System.out.print(spot + " ");
            }
            System.out.println();
        }


    }
}
