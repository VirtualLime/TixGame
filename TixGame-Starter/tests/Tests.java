import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//Requires JUnit 4
//a couple of tests for common issues

//the timeouts are a bit system dependent and can be adjusted slightly
public class Tests {

    @Test (timeout=3)
    public void cycleTest() {

        GameModel gm = new GameModel(11);

        gm.makePlay(1,1, GameController.BLACK);
        gm.makePlay(1,2, GameController.BLACK);
        gm.makePlay(1,3, GameController.BLACK);
        gm.makePlay(2,3, GameController.BLACK);
        gm.makePlay(3,3, GameController.BLACK);
        gm.makePlay(3,2, GameController.BLACK);
        gm.makePlay(3,1, GameController.BLACK);
        boolean b = gm.makePlay(2,1, GameController.BLACK);

        assertFalse(b);
    }

    @Test (timeout=3)
    public void cycleTestWithWinner() {

        GameModel gm = new GameModel(5);

        gm.makePlay(1,1, GameController.BLACK);
        gm.makePlay(1,2, GameController.BLACK);
        gm.makePlay(1,3, GameController.BLACK);
        gm.makePlay(2,3, GameController.BLACK);
        gm.makePlay(3,3, GameController.BLACK);
        gm.makePlay(3,2, GameController.BLACK);
        gm.makePlay(3,1, GameController.BLACK);
        boolean b = gm.makePlay(2,1, GameController.BLACK);
        assertFalse(b);
        gm.makePlay(4,0, GameController.BLACK);
        b = gm.makePlay(0,4, GameController.BLACK);
        assertTrue(b);

    }


    @Test (timeout=3)
    public void adjacentButNotConnectedTest() {

        GameModel gm = new GameModel(11);

        gm.makePlay(0,0, GameController.BLACK);
        gm.makePlay(1,1, GameController.BLACK);
        gm.makePlay(2,2, GameController.BLACK);
        gm.makePlay(3,3, GameController.BLACK);
        gm.makePlay(10,10, GameController.BLACK);
        gm.makePlay(9,9, GameController.BLACK);
        gm.makePlay(8,8, GameController.BLACK);
        gm.makePlay(7,7, GameController.BLACK);
        gm.makePlay(5,5, GameController.BLACK);
        gm.makePlay(4,4, GameController.BLACK);
        boolean b = gm.makePlay(6,6, GameController.BLACK);

        assertFalse(b);
    }

    @Test (timeout=3)
    public void convergingGroupsTest() {

        GameModel gm = new GameModel(11);

        //group 1
        gm.makePlay(10,0, GameController.BLACK);
        gm.makePlay(9,1, GameController.BLACK);
        gm.makePlay(8,2, GameController.BLACK);
        gm.makePlay(7,3, GameController.BLACK);
        //group 2
        gm.makePlay(0,10, GameController.BLACK);
        gm.makePlay(1,9, GameController.BLACK);
        gm.makePlay(2,8, GameController.BLACK);
        //group 3
        gm.makePlay(5,5, GameController.BLACK);
        gm.makePlay(4,6, GameController.BLACK);

        //join group 1 and group 3
        gm.makePlay(6,4, GameController.BLACK);

        //join group 1,3 and 2
        boolean b = gm.makePlay(3,7, GameController.BLACK);
        assertTrue(b);
    }

    @Test (timeout=5)
    public void twoPlayerTestBlackWins() {

        GameModel gm = new GameModel(11);

        //group 1
        gm.makePlay(10,0, GameController.WHITE);
        gm.makePlay(5,0, GameController.BLACK);

        gm.makePlay(9,1, GameController.WHITE);
        gm.makePlay(5,1, GameController.BLACK);


        gm.makePlay(8,2, GameController.WHITE);
        gm.makePlay(5,2, GameController.BLACK);

        gm.makePlay(7,3, GameController.WHITE);
        gm.makePlay(5,3, GameController.BLACK);

        gm.makePlay(6,4, GameController.WHITE);
        gm.makePlay(5,4, GameController.BLACK);



        //group 2
        gm.makePlay(0,10, GameController.WHITE);
        gm.makePlay(5,10, GameController.BLACK);

        gm.makePlay(1,9, GameController.WHITE);
        gm.makePlay(5,9, GameController.BLACK);

        gm.makePlay(2,8, GameController.WHITE);
        gm.makePlay(5,8, GameController.BLACK);

        gm.makePlay(3,7, GameController.WHITE);
        gm.makePlay(5,7, GameController.BLACK);

        boolean notWin = gm.makePlay(4,6, GameController.WHITE);
        assertFalse(notWin);
        notWin = gm.makePlay(5,6, GameController.BLACK);
        assertFalse(notWin);


        //join group 1 and 2
        boolean winnerWinnerChickedDinner = gm.makePlay(5,5, GameController.BLACK);

        assertTrue(winnerWinnerChickedDinner);

    }

    @Test (timeout=5)
    public void twoPlayerTestWhiteWins() {

        GameModel gm = new GameModel(11);

        //group 1
        gm.makePlay(10,0, GameController.WHITE);
        gm.makePlay(5,0, GameController.BLACK);

        gm.makePlay(9,1, GameController.WHITE);
        gm.makePlay(5,1, GameController.BLACK);


        gm.makePlay(8,2, GameController.WHITE);
        gm.makePlay(5,2, GameController.BLACK);

        gm.makePlay(7,3, GameController.WHITE);
        gm.makePlay(5,3, GameController.BLACK);

        gm.makePlay(6,4, GameController.WHITE);
        gm.makePlay(5,4, GameController.BLACK);



        //group 2
        gm.makePlay(0,10, GameController.WHITE);
        gm.makePlay(5,10, GameController.BLACK);

        gm.makePlay(1,9, GameController.WHITE);
        gm.makePlay(5,9, GameController.BLACK);

        gm.makePlay(2,8, GameController.WHITE);
        gm.makePlay(5,8, GameController.BLACK);

        gm.makePlay(3,7, GameController.WHITE);
        gm.makePlay(5,7, GameController.BLACK);

        boolean notWin = gm.makePlay(4,6, GameController.WHITE);
        assertFalse(notWin);
        notWin = gm.makePlay(5,6, GameController.BLACK);
        assertFalse(notWin);


        //join group 1 and 2
        boolean winnerWinnerChickedDinner = gm.makePlay(5,5, GameController.WHITE);

        assertTrue(winnerWinnerChickedDinner);
    }

    @Test
    public void invalidMove() {
        GameModel gm = new GameModel(11);
        boolean playOnEmpty = gm.canPlay(0, 0);
        assertTrue(playOnEmpty);

        gm.makePlay(0,0, GameController.BLACK);
        boolean playOnOccupiedSpot = gm.canPlay(0,0);
        assertFalse(playOnOccupiedSpot);
    }


}
