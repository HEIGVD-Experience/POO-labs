package engine.board;

import engine.fen.FENParser;

/**
 * FENBoardFactory is a concrete implementation of the BoardFactory interface,
 * specifically for creating chess boards from FEN (Forsyth-Edwards Notation) strings.
 */
public class FENBoardFactory implements BoardFactory {

    /**
     * The FEN code
     */
    private final String FENCode;

    /**
     * Constructs a FENBoardFactory with the given FEN string.
     *
     * @param FENCode The FEN string representing the board position.
     */
    public FENBoardFactory(String FENCode) {
        this.FENCode = FENCode;
    }

    @Override
    public Board createBoard() {
        return FENParser.getBoardFromFENCode(FENCode);
    }
}
