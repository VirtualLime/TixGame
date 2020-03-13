/**
 * This Class was inspired by the weighted version posted by Dr. Godbout.
 * Unlike that version, this class does not form an actual tree. Instead,
 * the values are placed into an array (if their position hasn't already
 * been filled), and the count increases. When the cound reaches "size",
 * it is able to return a boolean indicating the "tree" now represents
 * a winning solution.
 */
public class UnionTree {
    private int[] spot;
    int count, parent,size, row, column, player;
    private String replacement;
    boolean replaced, full;

    /**
     * This is the Constructor for UnionTree.
     * @param sz size of the array
     * @param rw row selected
     * @param cl column selected
     * @param par parent (row or column number)
     * @param pl player number (-1 for white, 1 for black)
     */
    public UnionTree(int sz, int rw, int cl, int par, int pl){
        player = pl;
        parent = par;
        count = 1;
        size = sz;
        spot = new int[size];
        replaced = false;
        replacement = "";
        row = rw;
        column = cl;
        spot[par-1] = par;
        full = false;
    }

    /**
     * This is a method used for combining groups. It takes the array
     * to be added, and sends the non-zero values to addToArray to be tested
     * and added. It doesn't bother adding all of them if the count ever reaches
     * the winning level. At that point, it returns true. If, after all elements
     * have been added, the array still has zeroes, it returns false.
     * @param arr the array to be added to the local array
     * @return an indication on if a winning solution was found.
     */
    public boolean addArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int j = arr[i];
            if(j>0){addToArray(j );}
            if(full){return full;}
        }
        return full;
    }

    /**
     * A series of getters and setters. Replace is not currently
     * in use. The Class used to also return a name for the group,
     * but this was seens as unnecessary and now the replacing
     * group's coordinates were used instead.
     * @return
     */
    public boolean checkIfReplaces(){return replaced;}
    public String getReplacement(){return replacement;}
    public void replace(String name){
        replacement = name;
        replaced = true;
    }
    public int getParent(){return parent;}
    public int getPlayer(){return  parent;}
    public int[] getArray(){return spot;}
    public int getR(){return row;}
    public int getC(){return column;}
    public boolean haveAWinner(){return full;}

    /**
     * A method not currently in use. I can be used to add a single value
     * to the array. Given the function of addToArray, it was seen as
     * unncessary, but it could still be used to import a single value.
     * @param n the value to be added
     * @return boolean for a winning solution.
     */
    public boolean add(int n){
        if(full){return full;}
        if(n == 0){return false;}
        else{
            return addToArray(n);
        }
    }

    /**
     * This is the method used to indicate that this group is
     * incomplete, a complete version (or partial directions to
     * one) can be found at the new RC.
     * @param r the row of the replacement
     * @param c the column of the replacement.
     */
    public void replaceRC(int r, int c){
        row = r;
        column = c;
        replaced = true;
    }

    /**
     * This method adds values that have a zero in their adjusted
     * index position, and adjust the count. It returns a boolean
     * based on achieving (or failing to achieve) a winning solution.
     * @param n
     * @return
     */
    public boolean addToArray(int n){
        if(full){return full;}
        if(spot[n-1] == 0){
            spot [n-1] = n;
            count++;
            if(count == size){
                full = true;
                return full;
            }
        }
        return full;
    }

    /**
     * This method was generally used for debugging purposes. IT
     * could also be used as an alternative to the current toString.
     * It prints the array for this group.
     */
    private void printArray(){
        for(int i = 0; i < size; i++){
            int n = spot[i];
            System.out.print(n + " ");
        }
        System.out.println();
    }

    /**
     * A toString method that indicates how many rows/columns are represented by
     * the current group, and if the group is only a partial group (due to replacement).
     */
    public String toString(){
        return "Group count: " + count + " replaced: " + replaced;
    }
}
