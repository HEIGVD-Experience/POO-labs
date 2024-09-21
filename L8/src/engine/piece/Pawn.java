package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;

import static engine.movement.EnPassant.isEnPassantAvailable;

/**
 * Representation of the pawn in a chess game.
 */
public class Pawn extends Piece {

    /**
     * Constructor.
     *
     * @param color the color of the piece.
     */
    public Pawn(PlayerColor color) {
        super(color);
    }

    /**
     * Constructor by copy.
     *
     * @param pawn the pawn to copy
     */
    public Pawn(Pawn pawn){
        super(pawn);
    }

    @Override
    public Pawn getCopy() {
        return new Pawn(this);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean isMoveValid(Board board, Position from, Position to) {
        int diffY = from.line() - to.line();

        int diffAbsX = Math.abs(from.column() - to.column());
        int diffAbsY = Math.abs(from.line() - to.line());

        // Check if the movement is horizontal
        if ((getColor() == PlayerColor.WHITE && diffY <= -1) || (getColor() == PlayerColor.BLACK && diffY >= 1)) {

            // If the movement is diagonal, there must be an opponent's pawn at the destination square
            if (diffAbsX == 1 && diffAbsY == 1) {
                // Check if the diagonal movement end on a square with a different color or
                // if the movement is authorized with an En Passant attack
                return board.posIsDiffColor(from, to) || isEnPassantAvailable(board, from, to);

            // To move two squares forward, isFirstMove must be true
            } else if (!super.getHasMoved() && diffAbsY == 2 && diffAbsX == 0) {
                return super.checkObstacle(board, from, to) && board.at(to) == null;

            // Move one square forward, isFirstMove state is not important
            } else if (diffAbsY == 1 && diffAbsX == 0) {
                return board.at(to) == null;
            }
        }

        return false;
    }
}
