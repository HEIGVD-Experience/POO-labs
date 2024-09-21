package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;

/**
 * Representation of the rook in a chess game.
 */
public class Rook extends Piece {

    /**
     * Constructor.
     *
     * @param color the color of the piece
     */
    public Rook(PlayerColor color) {
        super(color);
    }

    /**
     * Constructor by copy.
     *
     * @param rook the rook to copy
     */
    public Rook(Rook rook){
        super(rook);
    }

    @Override
    public Rook getCopy() {
        return new Rook(this);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean isMoveValid(Board board, Position from, Position to) {

        // Pour que la tour puisse se déplacer il faut que soit la ligne soit la colonne
        // d'arrivée soit la même que l'initiale. De plus il faut que la case ou se déplace la tour
        // soit, soit vide, soit avec une pièce de la couleur adverse.
        if((from.column() == to.column() || from.line() == to.line()) && board.posIsEmptyOrDiffColor(from,to)) {
            return super.checkObstacle(board, from, to);
        }

        return false;
    }
}