package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;

/**
 * Representation of the knight in a chess game.
 */
public class Knight extends Piece {

    /**
     * Constructor.
     *
     * @param color the color of the piece.
     */
    public Knight(PlayerColor color) {
        super(color);
    }

    /**
     * Constructor by copy.
     *
     * @param knight the piece to copy
     */
    public Knight(Knight knight){
        super(knight);
    }

    @Override
    public Knight getCopy() {
        return new Knight(this);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    @Override
    public boolean isMoveValid(Board board, Position from, Position to) {
        int diffX = Math.abs(from.column() - to.column());
        int diffY = Math.abs(from.line() - to.line());

        // Check que le cavalier se déplace uniquement avec des saut d'un décalage
        // de 2 et 1. Il n'y a pas de controle si le déplacement est possible car le
        // cavalier peut passer au dessus des pièces.
        if((diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2)) {
            return board.posIsEmptyOrDiffColor(from, to);
        }
        return false;
    }
}
