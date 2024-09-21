package engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import engine.board.Board;
import engine.Position;

import java.security.InvalidParameterException;

/**
 * Representation of a piece in a chess game.
 */
public abstract class Piece{

    /**
     * Color of the piece.
     */
    private final PlayerColor color;

    /**
     * True if the piece has moved.
     */
    private boolean hasMoved;

    /**
     * Piece constructor
     * @param color = color of the piece you want to create
     */
    public Piece(PlayerColor color) {
        this.color = color;
        this.hasMoved = false;
    }

    /**
     * Piece copy constructor
     * @ Piece to make a copy of
     */
    protected Piece(Piece piece) {
        if(piece == null) throw new NullPointerException("Cannot make a copy of a null value");
        if(this.getType() != piece.getType()) throw new InvalidParameterException("The type of the piece should be the same as the new piece");
        this.hasMoved = piece.hasMoved;
        this.color = piece.color;
    }

    /**
     * Abstract method forcing child to implement a method returning a copy of their piece.
     *
     * @return the copy of the piece
     */
    public abstract Piece getCopy();

    /**
     * Abstract method forcing child to implement a method returning their type
     * @return a value from the enum PieceType
     */
    public abstract PieceType getType();

    /**
     * Abstract method forcing child to implement a method checking if the move of the piece is valid
     * @param board = board used to check positions and if the move is possible
     * @param from = actual position of the piece
     * @param to = desired position for the piece
     * @return a boolean, true if the move is valid otherwise false
     */
    public abstract boolean isMoveValid(Board board, Position from, Position to);

    /**
     * Method checking if there is no obstacles with the desired move.
     *
     * @param board = board used to check positions and if the move is possible
     * @param from = actual position of the piece
     * @param to = desired position for the piece
     * @return a boolean, true if there is no piece blocking the movement otherwise false
     */
    public boolean checkObstacle(Board board, Position from, Position to) {

        int columnIncrement = columnIncrement(from.column(), to.column());
        int lineIncrement = lineIncrement(from.line(), to.line());

        int x = from.column() + columnIncrement;
        int y = from.line() + lineIncrement;

        while (x != to.column() || y != to.line()) {
            if (board.at(x, y) != null) {
                // There is a piece blocking the movement
                return false;
            }
            x += columnIncrement;
            y += lineIncrement;
        }

        return true;
    }

    /**
     * Define the value to be incremented while moving a piece (concerning columns).
     *
     * @param fromColumn = actual column of the piece
     * @param toColumn = desired column for the piece
     * @return an int value containing 0, 1 or -1
     */
    private int columnIncrement(int fromColumn, int toColumn) {
        return Integer.compare(toColumn, fromColumn);
    }

    /**
     * Define the value to be incremented while moving a piece (concerning lines).
     *
     * @param fromLine = actual line of the piece
     * @param toLine = desired line for the piece
     * @return an int value containing 0, 1 or -1
     */
    private int lineIncrement(int fromLine, int toLine) {
        return Integer.compare(toLine, fromLine);
    }

    /**
     * Getter permitting to know the color of the piece.
     *
     * @return the color of the piece
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Setter for the hasMoved attribute of a piece.
     */
    public void setHasMoved() {
        this.hasMoved = true;
    }

    /**
     * Getter permitting to know if the piece has moved.
     *
     * @return true if the piece has moved.
     */
    public boolean getHasMoved(){
        return this.hasMoved;
     }
}