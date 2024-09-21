package engine.board;

import engine.fen.FENParser;

/**
 * StandardBoardFactory is a concrete implementation of the BoardFactory interface.
 * It provides a way to create a standard chess board with the pieces in their initial positions.
 */
public class StandardBoardFactory implements BoardFactory {

    /**
     * THe initial position of a chess game.
     */
    private static final String STANDARD_INITIAL_POS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    @Override
    public Board createBoard() {
        return FENParser.getBoardFromFENCode(STANDARD_INITIAL_POS);
    }
}
