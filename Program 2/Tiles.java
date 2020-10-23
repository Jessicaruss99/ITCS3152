//Program 2 board

//import arraylist
import java.util.ArrayList;

//class for tiles for a star search
public class Tiles {
    // create a node array list called path
    private ArrayList<Node> finalPath;

    // set unpathable to #
    public static String UNPATHABLE = "#";

    // set pathable to ~
    public static String PATHABLE = "~";

    // create a tile string array
    private String[][] tileBoard;

    // create a tile string array for the path
    private String[][] tilePath;

    // create a node array
    private Node[][] nodeArray;

    public Tiles() {
        // initialize the tile board to 15 by15
        tileBoard = new String[15][15];

        // generate the tile board
        createTileBoard();

        // set hte node array to 15by15
        nodeArray = new Node[15][15];

        // generate the nodes
        createNodes();

        // set hte map path to 15 by 15
        tilePath = new String[15][15];

    }// end of tiles constructor

    public void createTileBoard() {
        // loop through all 15 rows and columns
        for (int i = 0; i < 15; i++) {
            // second loop
            for (int j = 0; j < 15; j++) {
                // random variable
                double random = Math.random();
                // probabilit less than 10% make unpathable
                if (random < 0.10) {
                    tileBoard[i][j] = UNPATHABLE;
                } // end of if statement
                  // otherwise set to pathable
                else {
                    tileBoard[i][j] = PATHABLE;
                } // end of if statement
            } // end inner for
        } // end outer for

    }// end of create tiles method

    public void createNodes() {
        // loop through 15 rows and columns
        for (int i = 0; i < 15; i++) {
            // second loop
            for (int j = 0; j < 15; j++) {
                // if pathable set to pathable value
                int type;
                if (tileBoard[i][j].equals(PATHABLE)) {
                    type = Node.PATHABLE;

                } // end if statement
                  // otherwise set to unpathable
                else {
                    type = Node.UNPATHBALE;
                } // end of else

                // set the node type
                nodeArray[i][j] = new Node(i, j, type);
            } // end inner for
        } // end outter for
    }// end of create nodes method

    public void createPath(int startRow, int startColumn, int goalRow, int goalColumn) {
        // create a new astartsearch
        AStarSearch search = new AStarSearch(nodeArray, startRow, startColumn, goalRow, goalColumn, 15);

        // if the path is found for the search return the path
        if (search.pathFound()) {
            finalPath = search.getNodePath();
        } // end if statement

        // otherwise set path to null
        else {
            finalPath = null;
        } // end of else
    }// end of create path method

    public String showPath() {
        // if the path is null
        if (finalPath == null) {
            // say no path could be found
            return "Path cannot be found";
        } // end of if

        // otherwise loop through path size
        else {
            // set the result
            String result = " ";
            for (int i = finalPath.size() - 1; i >= 0; i--) {
                // return the result string
                result = result + finalPath.get(i).toString() + " ";
            } // end of for loop
            return result;
        } // end else
    }// end of show path method

    public void updateTileBoard() {
        // reset the tile path
        resetTilePath();

        // if the path isnt null
        if (finalPath != null) {
            // create a counter for path
            int counter = 1;

            // loop through the path size
            for (int i = finalPath.size() - 1; i >= 0; i--) {

                // get the path
                Node nextNode = finalPath.get(i);

                // get the row
                int row = nextNode.getRow();

                // get the column
                int column = nextNode.getColumn();

                // set the path
                tilePath[row][column] = " " + counter;

                // increase the counter
                counter++;
            } // end for loop
        } // end if statement
    }// end of update tiles

    public void resetTilePath() {
        // loop through rows
        for (int i = 0; i < 15; i++) {
            // loop through columns
            for (int j = 0; j < 15; j++) {
                // set the tile path to the original tile board
                tilePath[i][j] = tileBoard[i][j];
            } // end inner for
        } // end outer for
    }// end of reset tiles method

    public void resetNodes() {
        // create new node
        nodeArray = new Node[15][15];
        // create nodes method
        createNodes();
    }// end of reset nodes method

    public void resetPath() {
        // clear path
        finalPath.clear();
    }// end of reset path

    public String[][] getTileBoard() {
        // return tile board
        return tileBoard;
    }// end of get tiles method

    public String[][] getTilePath() {
        // return tilepath
        return tilePath;
    }// end of get tile path method

    public String getType(int row, int column) {
        // return tile row and column
        return tileBoard[row][column];
    }// end of get type method

    public void setElement(int row, int column, String symbol) {
        // set tile board to the symbol
        tileBoard[row][column] = symbol;
    }// end of set element method

    public String toString() {
        // create empty result string
        String result = "";
        // new line
        result = result + "\t";
        // loop through map
        for (int i = 0; i < 15; i++) {
            // add to result
            result = result + i + "\t";
        } // end for loop

        // new line in result
        result = result + "\n";

        // loop through rows
        for (int i = 0; i < 15; i++) {
            // add to result
            result = result + i + "\t";
            // loop through columns
            for (int j = 0; j < 15; j++) {
                // add tile board nodes to result
                result = result + tileBoard[i][j] + "\t";
            } // end inner for loop

            // add new line in result
            result = result + "\n";
        } // end outer for loop

        // return result
        return result;
    }// end of to string

    public String tilePathToString() {
        // create empty result string
        String result = "";
        // new line
        result = result + "\t";
        // loop through map
        for (int i = 0; i < 15; i++) {
            // add to result
            result = result + i + "\t";
        } // end for loop

        // new line in result
        result = result + "\n";

        // loop through rows
        for (int i = 0; i < 15; i++) {
            // add to result
            result = result + i + "\t";
            // loop through columns
            for (int j = 0; j < 15; j++) {
                // add tile path nodes to result
                result = result + tilePath[i][j] + "\t";
            } // end inner for loop

            // add new line in result
            result = result + "\n";
        } // end outer for loop

        // return result
        return result;
    }// end of tile path to string

} // end of class