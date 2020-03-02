# CS2920-A3-GridAgain

In this assignment you'll construct the backend for another grid style game - although the grid is composed of hexagon shaped tiles. The game is simple it's a two player game with one player playing white game pieces and the other playing black game pieces. The goal for the player playing white pieces is to create a link of white pieces from the bottom edge of the board to the top edge. The goal of the player playing black pieces is to create a link of black pieces from the left edge of the board to the right edge. 

You will complete the GameModel class and specifically two methods:

```java
public boolean canPlay(int row, int col)

public boolean makePlay(int row, int col, int player)
```

The canPlay method should return true if the gameboard at position row, col is empty and false otherwise.

The makePlay method should play a piece at the position row, col by the player given. makePlay returns true if the game has been won and false otherwise. The game can never end in a draw. Thus eventually makePlay should be true.

Your goal is to implement the makePlay method as efficiently as possible.

JUnit4 unittests are provided which specify some time restrictions on your code - if the tests fail on your computer you can adjust the time restrictions slightly - but be aware the grader will set these as appropriate for the grader's computer. Thus you need to ensure your program runs as efficiently as possible.

For additional information see the youtube video at the following link:

https://www.youtube.com/watch?v=efOl0PBEX9A

Deliverables:

Monday March 9, 2020 (11:59pm):

1. A single pdf called Plan.pdf checked into your repository indicating two things - 1. who is working on the assignment and 2. Which algorithm from class you have chosen to implement as part of your solution

Friday March 13, 2020 (11:59pm): 

1. Your completed source code, that implements the algorithm you specified in the first deliverable.


Grading: 

1. Deliverable 1 checked in by its due date into a file called Plan.pdf 1pt
2. All unit tests pass and game plays appropriately 4pt 
3. Code Readability - appropriate method and variable names, spacing and indentation, comments above all methods, etc 1pt
4. Code design - no inappropriate class instance variables, the algorithm you specified in part 1 is clearly evident in your code 3pts
5. Your code runs as fast as the other student submissions 1pt

Note of course that we don't know how fast the other student submissions run yet, but if your code is the top half of students you'll get this point. If your code is noticeably slower you will not get the single point.



