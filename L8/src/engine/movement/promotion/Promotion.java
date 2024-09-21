package engine.movement.promotion;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;
import engine.piece.*;

import java.security.InvalidParameterException;

/**
 * Manage the promotion of the pieces in a chess game.
 */
public class Promotion {

    /**
     * Checks if the promotion is available.
     *
     * @param board the chess board
     * @param to the position of the piece
     * @return true if the piece can be promoted.
     */
    public static boolean isPromotionAvailable(Board board, Position to) {
        Piece p = board.at(to);

        if(p != null && p.getType() == PieceType.PAWN) {
            if(p.getColor() == PlayerColor.WHITE && to.line() == 7) {
                return true;
            } else return p.getColor() == PlayerColor.BLACK && to.line() == 0;
        }
        return false;
    }

    /**
     * Get the promoted piece.
     *
     * @param puc the user choice for the promotion
     * @param actualColor the color of the player
     * @return the new piece.
     */
    public static Piece getNewPromotedPiece(PromotionUserChoice puc, PlayerColor actualColor) {
        return switch (puc.getUserChoice()) {
            case QUEEN -> new Queen(actualColor);
            case ROOK -> new Rook(actualColor);
            case BISHOP -> new Bishop(actualColor);
            case KNIGHT -> new Knight(actualColor);
            default -> throw new InvalidParameterException("Unexpected value: " + puc.getUserChoice());
        };
    }
}
