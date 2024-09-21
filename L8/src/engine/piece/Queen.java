package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;

/**
 * Representation of the queen in a chess game.
 */
public class Queen extends Piece {

    /**
     * Constructor.
     *
     * @param color the color of the piece
     */
    public Queen(PlayerColor color) { super(color); }

    /**
     * Constructor by copy.
     *
     * @param queen the queen to copy
     */
    public Queen(Queen queen){
        super(queen);
    }

    @Override
    public Queen getCopy() {
        return new Queen(this);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    @Override
    public boolean isMoveValid(Board board, Position from, Position to) {
        int diffX = Math.abs(from.column() - to.column());
        int diffY = Math.abs(from.line() - to.line());

        if((diffX == diffY || diffX == 0 || diffY == 0) && board.posIsEmptyOrDiffColor(from, to)) {
            return super.checkObstacle(board, from, to);
        }
        return false;
    }
}