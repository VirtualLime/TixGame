public class Pair {
    private int player;
    private int spot;

    public Pair(int p, int s){
        player = p;
        spot = s;
    }

    public int getPlayer(){return player;}
    public int getSpot(){return spot;}
    public void setPlayer(int p){player = p;}
    public void setSpot(int s){spot = s;}

}
