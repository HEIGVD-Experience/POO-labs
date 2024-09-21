package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.movement.Castling;
import engine.board.Board;
import engine.board.BoardFactory;
import engine.board.StandardBoardFactory;
import engine.fen.FENParser;
import engine.movement.promotion.Promotion;
import engine.movement.promotion.PromotionUserChoice;
import engine.movement.Check;
import engine.piece.*;

import static engine.movement.EnPassant.getEnPassantNewPosition;
import static engine.movement.EnPassant.isEnPassantAvailable;

/**
 * ChessGame is a controller class for a chess game, implementing the ChessController interface.
 * It integrates the model-view-controller (MVC) design pattern by interacting with ChessView (the view) and Board (the model).
 * This class handles the main game logic, including initializing new games, managing player moves, and enforcing chess rules.
 */
public class ChessGame implements ChessController {

  /**
   * The view (MVC)
   */
  private ChessView view;
  /**
   * The board of the game
   */
  private Board board;
  /**
   * A factory to create the board
   */
  private BoardFactory boardFactory = new StandardBoardFactory();

  @Override
  public void start(ChessView view) {
    this.view = view;
    view.startView();
  }
  public void start(ChessView view, BoardFactory boardFactory) {
    this.boardFactory = boardFactory;
    start(view);
  }

  @Override
  public void newGame() {
    board = boardFactory.createBoard();

    // Copy the board to the view
    for (int col = 0; col < Board.BOARD_HEIGHT; ++col) {
      for (int line = 0; line < Board.BOARD_WIDTH; ++line) {
        Piece piece = board.at(col, line);
        if (piece == null) continue;
        view.putPiece(piece.getType(), piece.getColor(), col, line);
      }
    }
  }

  @Override
  public boolean move(int fromX, int fromY, int toX, int toY) {

    // Get positions and the piece that was selected
    Position from = new Position(fromX, fromY);
    Position to = new Position(toX, toY);
    Piece selectedPiece = board.at(from);

    if(isCastlingWanted(selectedPiece, from, to)){
      doCastlingMovement(board, from, to);
    }
    else{
      // Return if the move is not valid
      if(!isMoveValid(selectedPiece, from, to)) return false;

      // If En Passant can be done, delete the piece that has been eaten
      if(isEnPassantAvailable(board, from, to)) doEnPassantMovement(from, to);
      // If it's valid move the piece and
      movePiece(selectedPiece, from, to);

      // If promotion can be done, require user to do the promotion
      promoteIfAvailable(to);
    }

    // Swap player turns
    if(board.getCurrentPlayer() == PlayerColor.WHITE)
      board.setCurrentPlayer(PlayerColor.BLACK);
    else
      board.setCurrentPlayer(PlayerColor.WHITE);

    // If it's a checkmate, write the corresponding message
    // If it's a check, write the corresponding message
    // If it's a pat, write the corresponding message
    if(Check.isKingMat(board, board.getCurrentPlayer())) {
      view.displayMessage(String.format("Checkmate ! %s won the game", board.getCurrentPlayer()));
    }
    else if(Check.isKingCheck(board, board.getCurrentPlayer())){
      view.displayMessage("Check !");
    }
    else if(Check.isKingPat(board, board.getCurrentPlayer())) {
      view.displayMessage("Pat !");
    }

    // Finally return true to indicates the move was successful
    return true;
  }

  /**
   * Getter for the FEN code of the whole board.
   *
   * @return the FEN code.
   */
  public String getFENCode() {
    return FENParser.getFENCodeFromBoard(board);
  }

  /**
   * Promote the piece of the player
   *
   * @param to the position after the last move of the player
   */
  private void promoteIfAvailable(Position to) {
    if(Promotion.isPromotionAvailable(board, to)) {
      PromotionUserChoice puc = view.askUser("Promotion",
              "En quoi voulez-vous promouvoir votre pion?",
              new PromotionUserChoice(PieceType.QUEEN),
              new PromotionUserChoice(PieceType.ROOK),
              new PromotionUserChoice(PieceType.BISHOP),
              new PromotionUserChoice(PieceType.KNIGHT));

      Piece piece = Promotion.getNewPromotedPiece(puc, board.getCurrentPlayer());

      board.put(piece, to);
      view.putPiece(piece.getType(), piece.getColor(), to.column(), to.line());
    }
  }

  /**
   * Do the special move En Passant.
   *
   * @param from the initial position
   * @param to the final position
   */
  private void doEnPassantMovement(Position from, Position to) {
    Position deletedEnPassantTarget = getEnPassantNewPosition(board, from, to);
    if(deletedEnPassantTarget != null) {
      board.remove(deletedEnPassantTarget);
      view.removePiece(deletedEnPassantTarget.column(), deletedEnPassantTarget.line());
    }
  }

  /**
   * Checks if the player wants to castle.
   *
   * @param piece the piece the player wants to move
   * @param from the position of the piece
   * @param to the arrival position of the piece
   * @return true if the castling is wanted.
   */
  private boolean isCastlingWanted(Piece piece, Position from, Position to) {
    if(piece != null && piece.getType() != PieceType.KING) return false;
    return Castling.isCastlingAvailable(board, from, to);
  }

  /**
   * Castling movement.
   *
   * @param board the game board
   * @param from the position of the king
   * @param to the arrival position of the king
   */
  private void doCastlingMovement(Board board, Position from, Position to){
    boolean bigCastling = from.column() - to.column() > 0;
    Position rookPos;

    if(bigCastling)
      rookPos = new Position(0, from.line());
    else
      rookPos = new Position(Board.BOARD_WIDTH - 1, from.line());

    // Determine new positions for the king and the rook based on the type of castling
    Position newKingPos = bigCastling ? new Position(from.column() - 2, from.line()) : new Position(from.column() + 2, from.line());
    Position newRookPos = bigCastling ? new Position(from.column() - 1, from.line()) : new Position(from.column() + 1, from.line());

    // Move the king and the rook to their new positions
    Piece king = board.at(from);
    Piece rook = board.at(rookPos);

    board.move(from, newKingPos);
    view.removePiece(from.column(), from.line());
    view.putPiece(king.getType(), king.getColor(), newKingPos.column(), newKingPos.line());

    board.move(rookPos, newRookPos);
    view.removePiece(rookPos.column(), rookPos.line());
    view.putPiece(rook.getType(), rook.getColor(), newRookPos.column(), newRookPos.line());
  }

  /**
   * Move the piece.
   *
   * @param piece the piece to move
   * @param from the position of the piece
   * @param to the arrival position
   */
  private void movePiece(Piece piece, Position from, Position to) {
    view.removePiece(from.column(), from.line());
    view.putPiece(piece.getType(), piece.getColor(), to.column(), to.line());
    board.move(from, to);
  }

  /**
   * Checks whether a move is valid.
   *
   * @param piece the piece to move
   * @param from the position of the piece
   * @param to the arrival position
   * @return true if the move is valid
   */
  private boolean isMoveValid(Piece piece, Position from, Position to) {
    return board.at(from.column(), from.line()) != null &&                    // The piece cannot be null
           !from.equals(to) &&                                                // The positions cannot be equal
           board.getCurrentPlayer() == piece.getColor() &&                    // The player has to move its own pieces
           piece.isMoveValid(board, from, to) &&                              // The move has to be valid
           !Check.willKingBeCheck(board, board.getCurrentPlayer(), from, to); // The move cannot leave the king in check
  }
}
