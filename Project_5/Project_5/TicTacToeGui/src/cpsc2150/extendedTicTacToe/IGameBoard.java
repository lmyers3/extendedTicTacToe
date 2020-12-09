package cpsc2150.extendedTicTacToe;

public interface IGameBoard {

    /**
     * A grid representing the game board will hold player markers in appropriate location.
     * Grid can be implemented by a Map (memory efficient) or a 2D array(faster)
     * It is bound (NumOfRows) by (NumOfColumns),the dimensions are variable.
     * Defines: Grid: a grid representation of game board that will hold game tokens
     *          Row: Row coordinate of a player's piece
     *          Column: Column coordinate of a player's piece
     *          NumOfRows: number of rows in GameBoard
     *          NumOfColumns: number of columns in GameBoard
     *          NumToWin: number of player placements in a row to win
     *          NumOfSpaces: total number of spaces on game board
     *          NumOfPlays: total number of occupied spaces on game board
     *          Player: Player's game token
     *
     * Initialization ensures: GameBoard will be filled with empty char ' ' and
     *                          have a Grid, NumOfRows, NumOfColumns, NumToWin, NumOfMarkers and NumOfSpaces
     * Constraints: 3 <= NumOfRows <= 100 and
     *              3 <= NumOfColumns <= 100 and
     *              0 <= Row < NumOfRows and
     *              0 <= Column < NumOfColumns and
     *              3 <= NumToWin <= ( NumOfRows iff NumOfRows >= NumOfColumns else NumOfColumns) and
     *              NumOfSpaces = NumOfRows * NumOfColumns and
     *              0 <= NumOfPlays <= NumOfSpaces
     *
     */

    public final static int MIN_INDEX = 0;
    public final static char EMPTY = ' ';

    public final static int MIN_NUM_PLAYERS = 2;
    public final static int MAX_NUM_PLAYERS = 10;

    public final static int MAX_NUM_ROWS = 100;
    public final static int MAX_NUM_COLUMNS = 100;
    public final static int MAX_NUM_TO_WIN = 25;

    public final static int MIN_NUM_ROWS = 3;
    public final static int MIN_NUM_COLUMNS = 3;
    public final static int MIN_NUM_TO_WIN = 3;


    /**
     * @return the number of rows in the Grid
     * @pre
     * @post getNumRows = NumOfRows
     *
     */
    public int getNumRows();

    /**
     * @return the number of Columns in Grid
     * @pre
     * @post getNumColumns = NumOfColumns
     *
     */
    public int getNumColumns();

    /**
     * @return the number of a single Player that must be aligned in Grid to win
     * @pre
     * @post getNumToWin = NumToWin
     *
     */
    public int getNumToWin();

    /**
     * @return the number of player placements on Grid
     * @pre
     * @post getNumPlays = NumOfPlays
     *
     */
    public int getNumPlays();

    /**
     *
     * @return number of total spaces on grid
     * @pre
     * @post getNumSpaces = NumOfSpaces
     */
    public int getNumSpaces();

    /**
     * @params marker is a board position holding a Row and Column
     * @pre checkSpace(marker) == true
     * @post Player is placed at marker's board position on Grid
     *
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * @param pos is a board position with Row and Column
     * @return Player or empty space that may be found at pos in Grid
     * @pre isInBounds == true
     * @post whatsAtPos(pos) = what is found at pos board position in Grid
     *
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * @param lastPos is the most recent board position placed in Grid
     * @param player is Player that was most recently placed in Grid
     * @return true if NumToWin adjacent horizontal grid cells belong to player, otherwise false
     * @pre checkForDraw() = false
     * @post checkHorizontalWin() iff (NumToWin horizontally adjacent Grid cells hold player)
     *
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int count = 1;

        BoardPosition leftPos = new BoardPosition( lastPos.getRow(), lastPos.getColumn()-1 );
        BoardPosition rightPos = new BoardPosition( lastPos.getRow(), lastPos.getColumn()+1 );

        //check left
        while ( isInBounds( leftPos ) && isPlayerAtPos(leftPos, player)) {
            count++;
            leftPos = new BoardPosition( leftPos.getRow(), leftPos.getColumn()-1 );
        }
        //check right
        while ( isInBounds( rightPos ) && isPlayerAtPos(rightPos, player) ) {
            count++;
            rightPos = new BoardPosition(rightPos.getRow(), rightPos.getColumn()+1 );
        }

        return ( count >= getNumToWin() );
    }

    /**
     * @param lastPos is the most recent board position placed
     * @param player is Player that was most recently placed
     * @return true if NumToWin vertically adjacent grid cells belong to player, otherwise false
     * @pre checkForDraw() = false
     * @post checkVerticalWin iff ( NumToWin of player tokens are vertically adjacent  )
     *
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int count = 1;

        BoardPosition upPos = new BoardPosition( lastPos.getRow()+1, lastPos.getColumn() );
        BoardPosition downPos = new BoardPosition( lastPos.getRow()-1, lastPos.getColumn() );

        //check upwards
        while ( isInBounds( upPos ) && isPlayerAtPos(upPos, player) ) {
            count++;
            upPos = new BoardPosition( upPos.getRow()+1, upPos.getColumn() );
        }
        //check downwards
        while ( isInBounds( downPos ) && isPlayerAtPos(downPos, player) ) {
            count++;
            downPos = new BoardPosition( downPos.getRow()-1, downPos.getColumn() );
        }

        return ( count >= getNumToWin() );
    }

    /**
     * @param lastPos is the most recent board position placed
     * @param player is Player that was most recently placed
     * @return true if NumToWin diagonally lined Grid cells belong to player, otherwise false
     * @pre checkForDraw() = false
     * @post checkVerticalWin iff ( NumToWin of player tokens belonging to player are aligned diagonally )
     *
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int countRightDiagonal = 1;
        int countLeftDiagonal = 1;

        //Count Right Diagonal spaces
        BoardPosition upRightDiagonalPos = new BoardPosition( lastPos.getRow()-1, lastPos.getColumn()+1 );
        BoardPosition downRightDiagonalPos = new BoardPosition( lastPos.getRow()+1, lastPos.getColumn()-1 );

        //check upwards on Right Diagonal
        while ( isInBounds( upRightDiagonalPos ) && isPlayerAtPos(upRightDiagonalPos, player) ) {
            countRightDiagonal++;
            upRightDiagonalPos = new BoardPosition( upRightDiagonalPos.getRow()-1, upRightDiagonalPos.getColumn()+1 );
        }
        //check downwards on Right diagonal
        while ( isInBounds( downRightDiagonalPos ) && isPlayerAtPos(downRightDiagonalPos, player) ) {
            countRightDiagonal++;
            downRightDiagonalPos = new BoardPosition( downRightDiagonalPos.getRow()+1, downRightDiagonalPos.getColumn()-1 );
        }

        //Count Left Diagonal spaces
        BoardPosition upLeftDiagonalPos = new BoardPosition( lastPos.getRow()-1, lastPos.getColumn()-1 );
        BoardPosition downLeftDiagonalPos = new BoardPosition( lastPos.getRow()+1, lastPos.getColumn()+1 );

        //check upwards on LEFT Diagonal
        while ( isInBounds( upLeftDiagonalPos ) && isPlayerAtPos(upLeftDiagonalPos, player) ) {
            countLeftDiagonal++;
            upLeftDiagonalPos = new BoardPosition( upLeftDiagonalPos.getRow()-1, upLeftDiagonalPos.getColumn()-1 );
        }
        //check downwards on Right diagonal
        while ( isInBounds( downLeftDiagonalPos ) && isPlayerAtPos(downLeftDiagonalPos, player) ) {
            countLeftDiagonal++;
            downLeftDiagonalPos = new BoardPosition( downLeftDiagonalPos.getRow()+1, downLeftDiagonalPos.getColumn()+1 );
        }

        return ( countRightDiagonal >= getNumToWin() || countLeftDiagonal >= getNumToWin());
    }

    /**
     * @params pos, the board position with a Row and Column
     * @return true if the position is in bounds of Grid and empty, otherwise false
     * @pre
     * @post checkSpace(pos) iff ( isInBounds(pos) and whatsAtPos(pos) = ' ' )
     *
     */
    default boolean checkSpace(BoardPosition pos) {
        if ( isInBounds(pos) ) { return whatsAtPos(pos) == EMPTY; }
        else { return false; }
    }

    /**
     * @param lastPos is board position most recently placed in Grid
     * @return true if last position placed results in a win, otherwise false
     * @pre
     * @post checkForWinner(lastPos) iff ( checkHorizontalWin(lastPos) or checkVerticalWin(lastPos) or checkDiagonalWin(lastPos)
     *
     */
    default boolean checkForWinner(BoardPosition lastPos) {
        char lastPlayer = whatsAtPos(lastPos);
        return ( checkHorizontalWin(lastPos, lastPlayer) || checkVerticalWin(lastPos,lastPlayer )
                || checkDiagonalWin(lastPos, lastPlayer));
    }

    /**
     * @return true if no spaces in Grid are available, otherwise false
     * @pre
     * @post checkForDraw iff NumOfPlays = NumOfSpaces
     *
     */
    default boolean checkForDraw() { return getNumPlays() == getNumSpaces(); }


    /**
     * @param pos is a board position
     * @param player is the Player we want to check is at board position pos in Grid
     * @return true if Player is at specified position, otherwise false
     * @pre isInBound(pos) = true
     * @post isPlayerAtPos(pos, player) iff ( Player is found in Grid at board position pos )
     *
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) { return whatsAtPos(pos) == player; }

    /**
     *
     * @param pos is a board position holding a Row and Column
     * @return if pos in bounds of the Grid, otherwise false
     * @pre
     * @post isInBounds iff ( 0 <= Row < NumOfRows and 0 <= Column < NumOfColumns )
     */
    default boolean isInBounds( BoardPosition pos ) {
        if ( pos.getRow() >= MIN_INDEX && pos.getRow() < getNumRows() &&
                pos.getColumn() >= MIN_INDEX && pos.getColumn() < getNumColumns() ) {
            return true;
        }
        else { return false; }
    }


}
