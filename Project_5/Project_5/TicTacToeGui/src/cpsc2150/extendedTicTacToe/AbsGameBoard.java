package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard {


    @Override
    public String toString() {

        String str = " ";

        str += "  ";

        //prints top label
        for (int i = 0; i < getNumColumns(); i++) {
            if ( i < 10 ) { str += " "; }
            str +=  i + "|";
        }
        str += "\n";

        //prints side label and contents of grid
        for (int i = 0; i < getNumRows(); i++) {
            if ( i < 10 ) { str += " "; }
            str += i + "|";

            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition pos = new BoardPosition(i,j);
                str += whatsAtPos(pos) + " |";
            }
            str += "\n";
        }

        return str;
    }
}
