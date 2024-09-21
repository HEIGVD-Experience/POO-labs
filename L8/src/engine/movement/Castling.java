package engine.movement;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;
import engine.piece.Piece;

/**
 * Checks if the castling move is possible
 */
public class Castling {

    /**
     * Checks if the castling is available.
     *
     * @param board the chess board
     * @param from the position of the king
     * @param to the arrival position
     * @return true if the castling is possible.
     */
    public static boolean isCastlingAvailable(Board board, Position from, Position to){
        Piece fromPiece = board.at(from);
        PlayerColor playerColor = board.getCurrentPlayer();

        // Check if the piece is a king, if he has moved
        if(fromPiece == null || fromPiece.getHasMoved() || fromPiece.getType() != PieceType.KING || Math.abs(from.column()-to.column()) != 2) {
            return false;
        }

        //Checks if it is a big castling or not.
        boolean bigCastling = from.column() - to.column() > 0;
        Position rookPos;

        if(bigCastling)
            rookPos = new Position(0, from.line());
        else
            rookPos = new Position(Board.BOARD_WIDTH - 1, from.line());

        //Checks for the rook position, if there is actually a rook and if it has moved
        if(board.at(rookPos) == null || board.at(rookPos).getType() != PieceType.ROOK || board.at(rookPos).getHasMoved())
            return false;

        // Checks if there are no obstacles between the king and the rook
        if(!fromPiece.checkObstacle(board, from, rookPos)) return false;

        // Checks if the king is currently in check
        if(Check.isKingCheck(board, playerColor)) return false;

        // Checks if the king will be in check during the move
        int step = bigCastling ? -1 : 1;
        for (int i = 1; i <= 2; i++) {
            if(Check.willKingBeCheck(board, playerColor, from, new Position(from.column() + i * step, from.line()))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the king side castling is available. Used for FEN notation.
     *
     * @param board the chess board
     * @param playerColor the player color
     * @return true if the castling can be made.
     */
    public static boolean isKingSideCastlingAvailable(Board board, PlayerColor playerColor) {
        int line = playerColor == PlayerColor.WHITE ? 0 : Board.BOARD_HEIGHT - 1;
        Piece expectedRook = board.at(Board.BOARD_WIDTH - 1, line);
        Piece expectedKing = board.at(4, line);
        return expectedRook != null && !expectedRook.getHasMoved() && expectedKing != null && !expectedKing.getHasMoved();
    }

    /**
     * Checks if the queen side castling is available. Used for FEN notation.
     *
     * @param board the chess board
     * @param playerColor the player color
     * @return true if the castling can be made.
     */
    public static boolean isQueenSideCastlingAvailable(Board board, PlayerColor playerColor) {
        int line = playerColor == PlayerColor.WHITE ? 0 : Board.BOARD_HEIGHT - 1;
        Piece expectedRook = board.at(0, line);
        Piece expectedKing = board.at(4, line);
        return expectedRook != null && !expectedRook.getHasMoved() && expectedKing != null && !expectedKing.getHasMoved();
    }
}
