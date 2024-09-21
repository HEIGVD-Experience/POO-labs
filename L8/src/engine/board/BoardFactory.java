package engine.board;

/**
 * BoardFactory is an interface defining a factory for creating instances of Board.
 */
public interface BoardFactory {

    /**
     * Creates and returns a new instance of a Board.
     * The specifics of the board, such as its size, initial piece setup, etc.,
     * are determined by the implementation.
     *
     * @return A new instance of Board.
     */
    Board createBoard();
}
