package engine.movement;

import chess.PlayerColor;
import engine.board.Board;
import engine.Position;
import engine.piece.Piece;

/**
 * Manage check, mat and pat of the chess game.
 */
public class Check {

    /**
     * Checks if the king will be check in a particular position.
     *
     * @param board the chess board
     * @param kingColor the king color
     * @param from the actual position of the king
     * @param to the arrival position of the king
     * @return true if the king will be check.
     */
    public static boolean willKingBeCheck(Board board, PlayerColor kingColor, Position from, Position to) {

        // Create a copy of the board and move to piece to its target position
        Piece pieceToMove = board.at(from);
        Board targetBoard = new Board(board);

        // Move if it's valid
        if(pieceToMove.isMoveValid(board, from, to)) {
            targetBoard.move(from, to);
        }

        // Get the result based on the target board
        return isKingCheck(targetBoard, kingColor);
    }

    /**
     * Check if the king is check.
     *
     * @param board the chess board
     * @param kingColor the king color
     * @return true if the king is check
     */
    public static boolean isKingCheck(Board board, PlayerColor kingColor) {

        // Get king position
        Position kingPosition = board.getKingPosition(kingColor);
        if (kingPosition == null) return false;

        return isKingCheck(board, kingPosition);
    }

    /**
     * Checks if the king is check.
     *
     * @param board the chess board
     * @param kingPosition the king position
     * @return true if the king is check.
     */
    private static boolean isKingCheck(Board board, Position kingPosition) {

        // Loop through all the board positions and check if any piece can move to the king square
        for (int column = 0; column < Board.BOARD_HEIGHT; ++column) {
            for (int line = 0; line < Board.BOARD_WIDTH; ++line) {

                // If the piece is checking the king return true
                Piece piece = board.at(column, line);
                if (piece != null && piece.isMoveValid(board, new Position(column, line), kingPosition))
                    return true;
            }
        }

        // No piece is able to move to the king square
        return false;
    }

    /**
     * Check if the king is mat.
     *
     * @param board the chess board
     * @param playerColor the king color
     * @return true if the king is mat.
     */
    public static boolean isKingMat(Board board, PlayerColor playerColor) {

        // Check if a piece can block the check
        for (int col = 0; col < Board.BOARD_WIDTH; ++col) {
            for (int line = 0; line < Board.BOARD_HEIGHT; ++line) {
                if(canBlockCheck(board, playerColor, new Position(col, line)))
                    return false;
            }
        }

        // No piece can block the checkmate
        return true;
    }

    /**
     * Checks if the king is pat.
     *
     * @param board the chess board
     * @param playerColor the king color
     * @return true if the king is pat.
     */
    public static boolean isKingPat(Board board, PlayerColor playerColor) {

        // Check if a piece can move
        for (int col = 0; col < Board.BOARD_WIDTH; ++col) {
            for (int line = 0; line < Board.BOARD_HEIGHT; ++line) {
                if(canMoveAnywhere(board, playerColor, new Position(col, line)))
                    return false;
            }
        }

        // No move was available
        return true;
    }

    /**
     * Checks if a piece can block the check.
     *
     * @param board the chess board
     * @param playerColor the player color
     * @param position the position of the piece that could block the chess
     * @return true if the check can be blocked.
     */
    private static boolean canBlockCheck(Board board, PlayerColor playerColor, Position position) {

        Piece pieceToCheck = board.at(position);

        // Don't check other player pieces
        if (pieceToCheck == null || pieceToCheck.getColor() != playerColor) return false;

        // Test every move available
        for (int col = 0; col < Board.BOARD_WIDTH; ++col) {
            for (int line = 0; line < Board.BOARD_HEIGHT; ++line) {
                if(!willKingBeCheck(board, playerColor, position, new Position(col, line)))
                    return true;
            }
        }

        // No move was able to block the check
        return false;
    }

    /**
     * Checks if the piece at a particular can move anywhere.
     *
     * @param board the chess board.
     * @param playerColor the player color
     * @param position the piece position
     * @return true if the piece can move anywhere.
     */
    private static boolean canMoveAnywhere(Board board, PlayerColor playerColor, Position position) {

        Piece pieceToCheck = board.at(position);

        // Don't check other player pieces
        if (pieceToCheck == null || pieceToCheck.getColor() != playerColor) return false;

        // Test if there's any move available
        for (int col = 0; col < Board.BOARD_WIDTH; ++col) {
            for (int line = 0; line < Board.BOARD_HEIGHT; ++line) {
                Position newPosition = new Position(col, line);
                if(pieceToCheck.isMoveValid(board, position, newPosition) && !willKingBeCheck(board, playerColor, position, newPosition))
                    return true;
            }
        }

        // No available move was found
        return false;
    }
}