package engine.board;

import chess.PieceType;
import chess.PlayerColor;
import engine.Position;
import engine.piece.Piece;

/**
 * Represents the chess board in a game of chess.
 * This class is responsible for managing the state and layout of the chess pieces on the board.
 * It provides functionality to place, move, and remove pieces,as well as to check various
 * conditions relevant to the game rules.
 */
public class Board {

    /**
     * The board width
     */
    public static final int BOARD_WIDTH = 8;

    /**
     * The board height
     */
    public static final int BOARD_HEIGHT = 8;

    /**
     * A two dimensions arrays representing the chess board.
     */
    private final Piece[][] board;

    /**
     * The current player color.
     */
    private PlayerColor currentPlayer;

    /**
     * The position of the En Passant target
     */
    private Position enPassantTarget;

    /**
     * Constructor
     */
    public Board() {
        board = new Piece[BOARD_WIDTH][BOARD_HEIGHT];
    }

    /**
     * Copy constructor.
     *
     * @param board the board to copy
     */
    public Board(Board board) {
        if(board == null) throw new NullPointerException("Cannot make a copy of a null value");
        this.board = new Piece[BOARD_WIDTH][BOARD_HEIGHT];

        // Loop trough every position in the board
        for(int column = 0; column < board.board.length; ++column) {
            for (int line = 0; line < board.board[column].length; ++line) {
                if(board.board[column][line] == null) continue;
                this.board[column][line] = board.board[column][line].getCopy();
            }
        }
        this.enPassantTarget = board.getEnPassantTarget();
        this.currentPlayer = board.getCurrentPlayer();
    }

    /**
     * Retrieves the piece at the specified position on the board.
     * This method is used to access a piece based on its column and line indices.
     *
     * @param column The column index of the position to be checked on the board.
     * @param line The line index of the position to be checked on the board.
     * @return The chess piece at the specified position, or null if there is no piece.
     */
    public Piece at(int column, int line) {
        return board[column][line];
    }

    /**
     * Retrieves the piece at the specified position on the board.
     * This overloaded method allows accessing a piece using a Position object.
     *
     * @param pos The position on the board to check for a piece.
     * @return The chess piece at the specified position, or null if there is no piece.
     */
    public Piece at(Position pos) {
        return board[pos.column()][pos.line()];
    }

    /**
     * Places a piece at the specified position on the board.
     *
     * @param piece The chess piece to be placed.
     * @param column The column index of the board where the piece is to be placed.
     * @param line The line index of the board where the piece is to be placed.
     */
    public void put(Piece piece, int column, int line) {
        board[column][line] = piece;
    }

    /**
     * Places a piece at the specified position on the board.
     *
     * @param piece The chess piece to be placed.
     * @param position The position on the board where the piece is to be placed.
     */
    public void put(Piece piece, Position position) {
        board[position.column()][position.line()] = piece;
    }

    /**
     * Moves a piece from one position to another on the board.
     *
     * @param from The starting position of the piece.
     * @param to The destination position of the piece.
     */
    public void move(Position from, Position to) {
        Piece piece = at(from);
        if(piece == null) return;
        piece.setHasMoved();

        updateEnPassantTarget(from,to);

        put(at(from), to);
        remove(from);
    }

    /**
     * Updates the En Passant target position after a pawn move.
     *
     * @param from The starting position of the pawn.
     * @param to The destination position of the pawn.
     */
    private void updateEnPassantTarget(Position from, Position to) {
        int forwardDirection = currentPlayer == PlayerColor.WHITE ? 1 : -1;
        Piece piece = at(from);
        if(piece != null && piece.getType() == PieceType.PAWN && to.line() - from.line() == 2 * forwardDirection)
            setEnPassantTarget(new Position(from.column(), from.line() + forwardDirection));
        else
            setEnPassantTarget(null);
    }

    /**
     * Removes a piece from the specified position on the board.
     *
     * @param column The column index of the board from which the piece is to be removed.
     * @param line The line index of the board from which the piece is to be removed.
     */
    public void remove(int column, int line) {
        put(null, column, line);
    }

    /**
     * Removes a piece from the specified position on the board.
     *
     * @param position The position on the board from which the piece is to be removed.
     */
    public void remove(Position position) {
        put(null, position.column(), position.line());
    }

    /**
     * Checks if the piece at the destination position is of a different color than
     * the piece at the source position.
     *
     * @param from The source position.
     * @param to The destination position.
     * @return True if the piece at the destination is of a different color; otherwise, false.
     */
    public boolean posIsDiffColor(Position from, Position to) {
        return this.at(to) != null && this.at(from).getColor() != this.at(to).getColor();
    }

    /**
     * Checks if the destination position is empty or contains a piece of a different
     * color than the piece at the source position.
     *
     * @param from The source position.
     * @param to The destination position.
     * @return True if the destination is empty or contains a piece of a different color; otherwise, false.
     */
    public boolean posIsEmptyOrDiffColor(Position from, Position to) {
        return this.at(to) == null || this.at(from).getColor() != this.at(to).getColor();
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Getter for the king position.
     *
     * @param playerColor The king color
     * @return The position of the king.
     */
    public Position getKingPosition(PlayerColor playerColor) {

        // Loop trough every position in the board
        for(int column = 0; column < Board.BOARD_HEIGHT; ++column) {
            for(int line = 0; line < Board.BOARD_WIDTH; ++line) {

                // If the piece is the king of the given color, return its position
                Piece piece = at(column, line);
                if (piece != null && piece.getType() == PieceType.KING && piece.getColor() == playerColor)
                    return new Position(column,line);
            }
        }

        // If no king of the given color was found return null
        return null;
    }


    /**
     * Retrieves the current En Passant target position on the board.
     *
     * @return The current En Passant target position, or null if there is no valid En Passant target.
     */
    public Position getEnPassantTarget() {
        return enPassantTarget;
    }

    /**
     * Sets the En Passant target position on the board.
     *
     * @param enPassantTarget The position to set as the En Passant target, or null to clear the current target.
     */
    public void setEnPassantTarget(Position enPassantTarget) {
        this.enPassantTarget = enPassantTarget;
    }
}