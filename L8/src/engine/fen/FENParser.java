package engine.fen;

import chess.PlayerColor;
import engine.Position;
import engine.board.Board;
import engine.movement.Castling;
import engine.piece.*;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

/**
 * The FENParser class is responsible for converting Forsyth-Edwards Notation (FEN)
 * strings to Board objects and vice versa.
 */
 public class FENParser {

    /**
     * Converts a FEN string into a Board object.
     * Parses the FEN string to set up the chess pieces on the board according to the notation.
     *
     * @param FENCode The FEN string representing a chess board state.
     * @return A Board object representing the state described in the FEN string.
     */
    public static Board getBoardFromFENCode(String FENCode) {

        Board board = new Board();

        String[] parts = FENCode.split(" ");
        String[] rows = parts[0].split("/");

        // Start from the last rank
        int row = Board.BOARD_HEIGHT - 1;

        for (String rowString : rows) {
            int col = 0;
            for (char c : rowString.toCharArray()) {
                if (Character.isDigit(c)) {
                    // Empty squares
                    int emptySpaces = Character.getNumericValue(c);
                    col += emptySpaces;
                } else {
                    // Piece on the square
                    PlayerColor color = (Character.isUpperCase(c) ? PlayerColor.WHITE : PlayerColor.BLACK);
                    Piece piece = getPieceFromFENChar(c, color);
                    board.put(piece, col, row);
                    col++;
                }
            }
            row--;
        }

        // The correct player turn is determined after parsing
        PlayerColor currentPlayerColor = parts[1].equals("w") ? PlayerColor.WHITE : PlayerColor.BLACK;
        board.setCurrentPlayer(currentPlayerColor);

        // Set the castling availability
        boolean whiteKingSideCastling = parts[2].contains("K");
        boolean whiteQueenSideCastling = parts[2].contains("Q");
        boolean blackKingSideCastling = parts[2].contains("k");
        boolean blackQueenSideCastling = parts[2].contains("q");

        updatePieceHasMoved(board, whiteKingSideCastling, Board.BOARD_WIDTH - 1, 0);
        updatePieceHasMoved(board, whiteQueenSideCastling, 0, 0);
        updatePieceHasMoved(board, blackKingSideCastling, Board.BOARD_WIDTH - 1, Board.BOARD_HEIGHT - 1);
        updatePieceHasMoved(board, blackQueenSideCastling, 0, Board.BOARD_HEIGHT - 1);


        // Check if en passant is possible and set the target pawn accordingly
        if (parts.length >= 4 && parts[3] != null && parts[3].length() >= 2) {
            int[] targetSquare = getSquareIndices(parts[3]);
            board.setEnPassantTarget(new Position(targetSquare[0], targetSquare[1]));
        }

        return board;
    }

    /**
     * Converts the current state of a Board object into a FEN string.
     * This method is used for generating a FEN string that represents
     * the current arrangement of pieces on the given board.
     *
     * @param board The Board object whose state is to be converted into a FEN string.
     * @return A FEN string representing the current state of the board.
     */
    public static String getFENCodeFromBoard(Board board) {
        StringBuilder fenBuilder = new StringBuilder();

        // Iterate through each row, starting from the top
        for (int row = Board.BOARD_HEIGHT - 1; row >= 0; row--) {
            int emptyCount = 0;

            // Iterate through each column
            for (int col = 0; col < Board.BOARD_WIDTH; col++) {
                Piece piece = board.at(col, row);

                if (piece == null) {
                    // Empty square
                    emptyCount++;
                } else {
                    // Piece on the square
                    if (emptyCount > 0) {
                        fenBuilder.append(emptyCount);  // Append as a digit
                        emptyCount = 0;  // Reset emptyCount after appending
                    }

                    char fenChar = getFENCharFromPiece(piece);
                    fenChar = piece.getColor() == PlayerColor.WHITE ? Character.toUpperCase(fenChar) : fenChar;

                    fenBuilder.append(fenChar);
                }
            }

            // Add the count of empty squares at the end of each row
            if (emptyCount > 0) {
                fenBuilder.append(emptyCount);  // Append as a digit
            }

            // Add the '/' to separate rows, except for the last row
            if (row > 0) {
                fenBuilder.append("/");
            }
        }

        // Add the player color turn
        fenBuilder.append(" ").append(board.getCurrentPlayer() == PlayerColor.WHITE ? "w" : "b");

        // Add castling availability
        boolean whiteKingSideCastling = Castling.isKingSideCastlingAvailable(board, PlayerColor.WHITE);
        boolean whiteQueenSideCastling = Castling.isQueenSideCastlingAvailable(board, PlayerColor.WHITE);
        boolean blackKingSideCastling = Castling.isKingSideCastlingAvailable(board, PlayerColor.BLACK);
        boolean blackQueenSideCastling = Castling.isQueenSideCastlingAvailable(board, PlayerColor.BLACK);

        fenBuilder.append(" ");
        if(whiteKingSideCastling || whiteQueenSideCastling || blackKingSideCastling || blackQueenSideCastling) {
            if (whiteKingSideCastling)
                fenBuilder.append("K");
            if (whiteQueenSideCastling)
                fenBuilder.append("Q");
            if (blackKingSideCastling)
                fenBuilder.append("k");
            if (blackQueenSideCastling)
                fenBuilder.append("q");
        }
        else  {
            fenBuilder.append("-");
        }

        // Add en passant target square if available
        Position targetSquare = board.getEnPassantTarget();
        if (targetSquare != null) {
            char file = (char) ('a' + targetSquare.column());
            int rank = targetSquare.line() + 1;
            fenBuilder.append(" ").append(file).append(rank);
        } else {
            fenBuilder.append(" -");
        }

        return fenBuilder.toString();
    }

    /**
     * Converts a FEN character to a chess Piece object.
     * This method takes a character from the FEN notation and the color of the piece,
     * and returns the corresponding chess piece.
     *
     * @param c The character from the FEN string representing a chess piece.
     * @param color The color of the piece, either PlayerColor.WHITE or PlayerColor.BLACK.
     * @return The corresponding Piece object, or null if the character does not represent a valid piece.
     */
    private static Piece getPieceFromFENChar(char c, PlayerColor color) {
        return switch (toLowerCase(c)) {
            case 'p' -> new Pawn(color);
            case 'r' -> new Rook(color);
            case 'n' -> new Knight(color);
            case 'b' -> new Bishop(color);
            case 'q' -> new Queen(color);
            case 'k' -> new King(color);
            default -> null;
        };
    }

    /**
     * Converts a Piece object to a FEN character.
     * This method is used to represent a chess piece as a single character
     * according to FEN notation.
     *
     * @param piece The Piece object to be converted to a FEN character.
     * @return A character representing the piece in FEN notation.
     */
    private static char getFENCharFromPiece(Piece piece) {
        return switch (piece.getType()) {
            case PAWN -> 'p';
            case ROOK -> 'r';
            case KNIGHT -> 'n';
            case BISHOP -> 'b';
            case QUEEN -> 'q';
            case KING -> 'k';
        };
    }

    /**
     * Calculates the board indices for a given square in algebraic notation.
     * This method is used for converting a square in algebraic notation
     * (e.g., 'e4') into its corresponding board indices.
     *
     * @param square The square in algebraic notation.
     * @return An array of two integers where the first integer is the column index and the second is the row index.
     */
    private static int[] getSquareIndices(String square) {
        int col =  toUpperCase(square.charAt(0)) - 'A';
        int row = Integer.parseInt(square.substring(1)) - 1;
        return new int[]{col, row};
    }

    /**
     * Updates the movement state of a piece for castling.
     * This method is used to set the 'hasMoved' status of a rook or king when castling
     * rights are being determined from a FEN string.
     *
     * @param board The chess board on which the piece resides.
     * @param castlingAvailable A boolean indicating whether castling is available for the piece.
     * @param col The column index of the piece on the board.
     * @param row The row index of the piece on the board.
     */
    private static void updatePieceHasMoved(Board board, boolean castlingAvailable, int col, int row) {
        if (!castlingAvailable) {
            Piece pieceToCheck = board.at(col, row);
            if (pieceToCheck != null) {
                pieceToCheck.setHasMoved();
            }
        }
    }
}
