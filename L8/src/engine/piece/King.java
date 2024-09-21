package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;

/**
 * Representation of the king in a chess game
 */
public class King extends Piece {

    /**
     * Constructor.
     *
     * @param color color of the piece
     */
    public King(PlayerColor color) {
        super(color);
    }

    /**
     * Constructor by copy.
     *
     * @param king the king piece to copy
     */
    public King(King king){
        super(king);
    }

    @Override
    public King getCopy(){
        return new King(this);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public boolean isMoveValid(Board board, Position from, Position to) {
        int diffX = Math.abs(from.column() - to.column());
        int diffY = Math.abs(from.line() - to.line());

        if((diffX == 1 && diffY == 1) || (diffX == 0 && diffY == 1) || (diffX == 1 && diffY == 0)) {
            return board.posIsEmptyOrDiffColor(from, to);
        }
        return false;
    }
}