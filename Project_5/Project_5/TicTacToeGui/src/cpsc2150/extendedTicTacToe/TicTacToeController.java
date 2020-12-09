package cpsc2150.extendedTicTacToe;


/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    //the number of players in the game
    private int numPlayers;

    //index of player token
    private int tokenIndex;

    //flags to end game
    private boolean endGame = false;

    //list of available players
    private static final Character[] tokens = new Character[]{ 'X', 'O', 'B', 'Y', 'W', 'C', 'A', 'U', 'Z', 'I'};


    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
        this.tokenIndex = 0;
        // Some code is needed here.
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {

        //board position created from the button click
        BoardPosition pos = new BoardPosition(row, col);

        //check if the game has been ended when user clicks
        //if it is then start a new game
        if ( endGame ) { this.newGame(); }

        //place player token if space is available
        if ( curGame.checkSpace(pos) ) {
            screen.setMarker( row, col, getPlayer() );
            curGame.placeMarker( pos, getPlayer());

            //check if recently placed token results in win
            if ( curGame.checkForWinner( pos ) ) {
                screen.setMessage("Player " + getPlayer() + " won! Click any space to continue playing!");
                endGame = true;
            }
            //check if recently placed token results in draw
            else if ( curGame.checkForDraw() ) {
                screen.setMessage("Game ends in draw! Click any space to continue playing!");
                endGame = true;
            }
            //if there is not a win or draw set the next player and prompt for their turn
            else {
                setPlayer();
                screen.setMessage("It is player " + getPlayer() + "\'s turn");
            }
        }
        //alert user the space is unavailable and do nothing
        else {
            screen.setMessage("That space is not available!");
        }

    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }

    //return player in player list
    private Character getPlayer() { return tokens[tokenIndex]; }

    //sets token index to next in the array
    private void setPlayer() { tokenIndex = (tokenIndex + 1) % numPlayers; }

}