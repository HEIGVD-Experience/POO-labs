package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;

/**
 * Representation of a bishop in a chess game.
 */
public class Bishop extends Piece{

    /**
     * Constructor.
     *
     * @param color the color of the piece
     */
    public Bishop(PlayerColor color) {
        super(color);
    }

    /**
     * Constructor by copy.
     *
     * @param bishop the copy of the piece
     */
    public Bishop(Bishop bishop){ super(bishop); }

    @Override
    public Bishop getCopy() {
        return new Bishop(this);
    }

    @Override
    public PieceType getType() { return PieceType.BISHOP; }

    @Override
    public boolean isMoveValid(Board board, Position from, Position to) {

        int diffX = Math.abs(from.column() - to.column());
        int diffY = Math.abs(from.line() - to.line());

        // Controle si le déplacement est bien en diagonal, pour cela il faut que la différence de déplacement
        // sur l'axe X et Y soient le même. De plus on contrôle qu'il n'y ait pas de pièce sur la case d'arrivée
        // ou que la couleur de celle-ci soit différente de la pièce que l'on souhaite déplacer.
        if(diffX == diffY && board.posIsEmptyOrDiffColor(from, to)) {
            return super.checkObstacle(board, from, to);
        }
        return false;
    }
}
