package cpsc2150.extendedTicTacToe;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardMem extends AbsGameBoard {

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

    private Map<Character, List<BoardPosition>> grid;

    private int numRows;
    private int numColumns;
    private int numToWin;

    private int numPlays = 0;
    private int spaces;

    /**
     *
     * @param rows is the NumOfRows we wish to hold in Grid
     * @param columns is the NumOfColumns we wish to hold in Grid
     * @param winNum is NumToWin the number of aligned pieces in Grid that must all belong to Player in order to win
     * @pre
     * @post grid is a Map where player is the key and a list of board positions is the value and numRows = rows and
     *          numColumns = columns numToWin = winNum and spaces = numRows * numColumns
     */
    GameBoardMem(int rows, int columns, int winNum) {
        numRows = rows;
        numColumns = columns;
        numToWin = winNum;

        spaces = numRows * numColumns;

        grid = new HashMap<>();
    }

    public void placeMarker(BoardPosition marker, char player) {
        if ( grid.containsKey(player) ) {
            grid.get(player).add(marker);
        }
        else {
            List<BoardPosition> list;
            list = new ArrayList<>();
            list.add(marker);
            grid.put(player, list);
        }

        addNumPlays();
    }

    public char whatsAtPos(BoardPosition pos) {
        for ( Map.Entry<Character, List<BoardPosition>> m : grid.entrySet() ) {
            //checks if pos is in player's list of board placements
            for (BoardPosition k : m.getValue() ) {
                if ( k.equals(pos) ) {
                    return m.getKey();
                }
            }
        }
        // if pos not found returns an empty ' '
        return EMPTY;
    }

    /**
     *
     * @param pos is a board position
     * @param player is the Player we want to check is at board position pos in Grid
     * @return true iff pos is found in list of BoardPositions found at key(Player)
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if ( grid.containsKey(player) ) {
            for (BoardPosition k : grid.get(player) ) {
                if ( k.equals(pos) ) { return true; }
            }
        }
        else if ( player == EMPTY ) { return true; }
        return false;
    }

    public int getNumRows() { return numRows; }

    public int getNumColumns() { return numColumns; }

    public int getNumToWin() { return numToWin; }

    public int getNumPlays() { return numPlays; }

    public int getNumSpaces() { return spaces; }

    public void addNumPlays() { numPlays++; }

}
