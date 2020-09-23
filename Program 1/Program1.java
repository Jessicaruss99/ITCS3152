//Project 1 ITIS 3153
//Jessica Russ 801019588

//assignment description: place 8 queens on a board with 8 rows
// and 8 columns so that no queens occupy the same row, column, or diagonal
// use hill climbing with random restarts
// first generate a random starting state to place a queen in a random row of each column
// then check to see if the state is a goal state aka no conflict
// if not evaluate all possible neighbor states by moving 
// each columns queen through the rows of its columns and 
// generating a heuristic value 
// ocne all neighbor states have been generated, check to see
// if any generated have lower heuristic than the current
// if better state is found, then that becomes the current state and repeats
// if not, we reached local minima and start over
// use 0s to represent empty and 1s to represent queens
// each state should generate an output along with current heuristic,
// number of neighboring with lower heuristic, and action taken
// when solution is reached, display number restarts, and total number 
// of state changes



//import random to get starting rows for queens
import java.util.Random;

//class for Program 1 8 queens
public class Program1 {
    //count for number of state changes
    private static int stateChanges = 0;
    //value for heuristic
    private static int heuristic = 0;
    //value for number of better heuristic
    private static int betterHeuristics = 0;
    //number of random restarts
    private static int randomRestart = 0;
    
    //main method
    public static void main(String[] args) {
         //variable for value of current heuristic
        int currentHeuristic;
        
        //welcome to 8 queens messgae
        System.out.println("Welcome! I will now begin solving the 8 Queens problem with hill climbing and random restarts. By Jessica Russ");
        
        //Creating the initial board
        Queens[] currentBoard = newBoard();
        currentHeuristic = findHeuristic(currentBoard);
        
        // test if the present board is the solution board
        //while the current heuristic isnt zero aka isnt the solution, loop through until it is 
        while (currentHeuristic != 0) {
           
            currentBoard = nextBoard(currentBoard);
            currentHeuristic  = heuristic;
       
        //messages and board to print out while working for solution
        System.out.println("Current h: " + currentHeuristic);
        System.out.println("Current State");
        printState(currentBoard);
        System.out.println("Neighbors found with lower h: " + betterHeuristics);
        

        
        }
       //message and board to print out when we found a solution
        System.out.println("Current State");
        printState(currentBoard);
        System.out.println("Solution Found!");
        System.out.println("State Changes: " + stateChanges);
        System.out.println("Restarts: " + randomRestart);
        
    }
     // method for finding heuristic value
    public static int findHeuristic (Queens[] currrentstate) {
        //set initial heuristic value 
        int heuristic = 0;
        
        //loop through to find conflict
        for (int i = 0; i< 8; i++) {
            for (int j=i+1; j<8; j++ ) {
                if (currrentstate[i].ifConflict(currrentstate[j])) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }//end of method to find heuristic

    
    //method to make a new board 8x8
    public static Queens[] newBoard() {
         //create a new queens board that is 8 spaces big
        Queens[] startingBoard = new Queens[8];
        
        //new random variable
        Random random = new Random();
        
        //loop through the board columns placing each queen in a random row
        for(int i=0; i<8; i++){
            startingBoard[i] = new Queens(random.nextInt(8), i);
        }
        
        //display the random starting board
        return startingBoard;
    }//end of generate board method
    
    
    //method to print out the current state of the 8x8 board
    private static void printState (Queens[] currrentstate) {
    
        //create a temporary board
        int[][] temporaryBoard = new int[8][8];
        
        //loop through columns
        for (int i=0; i<8; i++) {
            //find positions of queens in starting board and put them in temporary board
            temporaryBoard[currrentstate[i].getRow()][currrentstate[i].getColumn()]=1;
        }
        
        System.out.println();
        for (int i=0; i<8; i++) {
            for (int j= 0; j < 8; j++) {
                System.out.print(temporaryBoard[i][j] + " ");
            }
            System.out.println();
        }
    }//end of print state method
    
        
    // method to search through board to find lowest heuristic
    public static Queens[] nextBoard (Queens[] currentBoard) {
        //make a queens board for the next board
        Queens[] nextBoard = new Queens[8];
        //make a temporary baord
        Queens[] temporaryBoard = new Queens[8];
        
        
        int currentHeuristic = findHeuristic(currentBoard);
        int lowerHeuristic = currentHeuristic;
        int temporaryHeuristic;

        //put values of current board in next board and temporar board
        for (int i=0; i<8; i++) {
            nextBoard[i] = new Queens(currentBoard[i].getRow(), currentBoard[i].getColumn());
            temporaryBoard[i] = nextBoard[i];
        }
        // go through each column
        for (int i=0; i<8; i++) {
            if (i>0)
                temporaryBoard[i-1] = new Queens (currentBoard[i-1].getRow(), currentBoard[i-1].getColumn());
                temporaryBoard[i] = new Queens (0, temporaryBoard[i].getColumn());
                
            //  go through each row
            for (int j=0; j<8; j++) {
            
                //find the heuristic
                temporaryHeuristic = findHeuristic(temporaryBoard);
                
                //find out if the temporary board is better 
                if (temporaryHeuristic < lowerHeuristic) {
                    lowerHeuristic = temporaryHeuristic;
                    betterHeuristics++;
                    
                    //maek the temporary board the next board
                    for (int k=0; k<8; k++) {
                        nextBoard[k] = new Queens(temporaryBoard[k].getRow(), temporaryBoard[k].getColumn());
                    }
                }
                //move the queens
                if (temporaryBoard[i].getRow()!=8-1)
                    temporaryBoard[i].move();
                    
            }
        }
        
        //find out fi we reached local minima and the current and lower heuristic are hte same
        //randomly make a new board to start over
        if (lowerHeuristic == currentHeuristic) {
            randomRestart++;
           
            nextBoard = newBoard();
            heuristic = findHeuristic(nextBoard);
            System.out.println("" );
            System.out.println("RESTART");
            System.out.println("" );

        }
        else
            heuristic = lowerHeuristic;
            stateChanges++;
            System.out.println("" );
            System.out.println("Setting up board" );
            System.out.println("" );
            return nextBoard;
        

    }//end of next board method

}//end of program 1
