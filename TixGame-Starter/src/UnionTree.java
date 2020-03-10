/**
 * An altered version of the WeightedQuickUnionUF shaed by Dr. Godbout
 */
public class UnionTree {
    private int[] spot;
    int count, parent,size, row, column, player;
    String replacement;
    boolean replaced, full;


    public UnionTree(int s, int r, int c, int p, int pl){
        player = pl;
        parent = p;
        count = 1;
        size = s;
        spot = new int[size];
        replaced = false;
        replacement = "";
        row = r;
        column = c;
        spot[p-1] = p;
        full = false;
    }

    public boolean addArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int j = arr[i];
            addToArray(j);
            if(full){return full;}
        }
        return full;
    }

    public boolean checkIfReplaces(){return replaced;}
    public String getReplacement(){return replacement;}
    public void replace(String name){
        replacement = name;
        replaced = true;
    }
    public int getParent(){return parent;}
    public int getPlayer(){return  parent;}

    public int[] getArray(){return spot;}

    public boolean add(int n){
        if(full){return full;}
        if(n == 0){return false;}
        /*if(parent == 0){
            parent = n;
            addToArray(parent);
        }
        /*else if(parent > n){
            int temp = parent;
            parent = n;
            addToArray(parent);
            add(temp);
        }*/
        else{//}{
            return addToArray(n);
        }
    }

    public int getR(){return row;}
    public int getC(){return column;}
    public void replaceRC(int r, int c){
        row = r;
        column = c;
        replaced = true;
    }

    public boolean addToArray(int n){
        if(full){return full;}
        if(spot[n-1] == 0){
            spot [n-1] = n;
            count++;
            System.out.println(count);

            if(count == size){
                full = true;
                System.out.println("win");
                return full;
            }
            //System.out.println("count: " + count);
        }
        //if(count == size){full = true;}
        return full;
    }

    public boolean haveAWinner(){
        //System.out.println("count: " + count);printArray();
        /*if(count == size){return true;}
        return false;*/
        return full;
    }

    private void printArray(){
        for(int i = 0; i < size; i++){
            int n = spot[i];
            System.out.print(n + " ");
        }
        System.out.println();
    }

}
