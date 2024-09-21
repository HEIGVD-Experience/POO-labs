package engine.movement;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;

/**
 * Manage the en passant movement in a chess game.
 */
public class EnPassant {

    /**
     * Check if the En Passant move is possible with the given from and to positions.
     *
     * @param board = the game board
     * @param to = the final position
     * @return is true if the movement is possible otherwise is false
     */
    public static boolean isEnPassantAvailable(Board board, Position from, Position to) {
        Position enPassantTarget = board.getEnPassantTarget();
        boolean isAPawn = board.at(from).getType() == PieceType.PAWN;
        return isAPawn && enPassantTarget != null && to.column() == enPassantTarget.column()
                && to.line() == enPassantTarget.line();
    }

    /**
     * Method getting the final position of the pawn that could be eaten by a En Passant move to enable
     * the controller to update the view and the board.
     *
     * @param board = the game board
     * @param from = the initial position
     * @param to = the final position
     * @return a position containing null if there is no pawn that can be eaten by this En Passsant move.
     */
    public static Position getEnPassantNewPosition(Board board, Position from, Position to) {

        // Define the direction of where to check the opponent position
        int forwardDirection = (board.at(from).getColor() == PlayerColor.WHITE) ? -1 : 1;

        Position targetPosition = new Position(to.column(), to.line() + forwardDirection);

        // Check if there is a pawn at the position + forwardDirection
        if(board.at(targetPosition) != null) {

            // Check if the type of the piece at the checked position is a pawn
            if(board.at(targetPosition).getType() == PieceType.PAWN) {
                return targetPosition;
            }
        }

        return null;
    }
}
