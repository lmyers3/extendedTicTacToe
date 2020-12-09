package cpsc2150.extendedTicTacToe;

public class BoardPosition {

    /**
     * @Invariants 0 <= row and
     * 0 <= column
     */

    private int row;
    private int column;

    /**
     * @param r is index inside of row
     * @param c is index inside of column
     * @pre
     * @post r = row and c = column
     */
    BoardPosition(int r, int c) {
        row = r;
        column = c;
    }

    /**
     * @return row index number
     * @pre
     * @post getRow() = row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return column index number
     * @pre
     * @post getColumn() = column
     */
    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) { return true; }
        if (!(obj instanceof BoardPosition)) { return false; }

        BoardPosition pos = (BoardPosition) obj;
        // Compare rows and columns
        return this.getRow() == pos.getRow() && this.getColumn() == pos.getColumn();
    }

}