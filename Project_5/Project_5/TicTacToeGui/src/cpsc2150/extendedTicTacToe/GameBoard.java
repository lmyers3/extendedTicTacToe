package cpsc2150.extendedTicTacToe;


public class GameBoard extends AbsGameBoard implements IGameBoard{

    /**
     * @Invariant 3 <= numRows <= 100 and
     *              3 <= numColumns <= 100 and
     *              3 <= numToWin <= ( numRows iff numRows >= numColumns else numColumns) and
     *              0 <= numPlays <= spaces
     *              spaces = numRows * numColumns
     *
     * @Correspondence Grid = grid
     *                  NumOfRows = numRows
     *                  NumOfColumns = numColumns
     *                  NumToWin = numToWin
     *                  NumOfPlays = numPlays
     *                  NumOfSpaces = spaces
     *
     */

    private char [][] grid;

    private int numRows;
    private int numColumns;
    private int numToWin;
    private int spaces;

    private int numPlays = 0;


    /**
     *
     * @param rows is the NumOfRows we wish to hold in Grid
     * @param columns is the NumOfColumns we wish to hold in Grid
     * @param winNum is NumToWin the number of aligned pieces in Grid that must all belong to Player in order to win
     * @pre
     * @post grid is a 2D array bound by numRows by numColumns will be initialized with all ' ' empty spaces EMPTY
     *          and numRows = rows and numColumns = columns numToWin = winNum and spaces = numRows * numColumns
     */
    GameBoard(int rows, int columns, int winNum) {

        numRows = rows;
        numColumns = columns;
        numToWin = winNum;

        spaces = numRows * numColumns;

        grid = new char[numRows][numColumns];

        for ( int i = 0; i < getNumRows(); i++ ) {
            for ( int j = 0; j < getNumColumns(); j++) {
                grid[i][j] = EMPTY;
            }
        }
    }


    public void placeMarker(BoardPosition marker, char player) {
        grid[marker.getRow()][marker.getColumn()] = player;
        addNumPlays();
    }

    public char whatsAtPos(BoardPosition pos) { return grid[pos.getRow()][pos.getColumn()]; }

    public int getNumRows() { return numRows; }

    public int getNumColumns() { return numColumns; }

    public int getNumToWin() { return numToWin; }

    public int getNumPlays() { return numPlays; }

    public int getNumSpaces() { return spaces; }

    public void addNumPlays() { numPlays++; }

}
