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


//class for queens
public class Queens {
//create private int variables for row and column
    private int row;
    private int column;

//constructor for row and column
    public Queens(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
//method to move rows
    public void move () {
        row++;
    }
    
//method to find conflicts 
    public boolean ifConflict(Queens queen){
        //  Check rows and columns for potential conflict
        if(row == queen.getRow() || column == queen.getColumn()){
            return true;
            }
            //  Check diagonals for potential conflict
        else if(Math.abs(column-queen.getColumn()) == Math.abs(row-queen.getRow())){
            return true;
            }
        else{
            return false;
        }
    }//end of if conflict
    
    //method to get the row
    public int getRow() {
        return row;
    }//end of get row

   //method to get the column
    public int getColumn() {
        return column;
    }//end of get column
    
    
    
} //end of class