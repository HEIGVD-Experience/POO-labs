package engine.movement.promotion;

import chess.ChessView;
import chess.PieceType;

public class PromotionUserChoice implements ChessView.UserChoice {

    /**
     * The user choice.
     */
    private final PieceType userChoice;

    /**
     * The user choice text.
     */
    private final String userChoiceText;

    /**
     * Constructor
     */
    public PromotionUserChoice(PieceType userChoice) {
        this.userChoice = userChoice;
        this.userChoiceText = userChoice.toString();
    }

    @Override
    public String textValue() {
        return userChoiceText;
    }

    /**
     * Getter for the user choice for the promotion.
     *
     * @return the piece type of the promoted piece.
     */
    public PieceType getUserChoice() {
        return userChoice;
    }
}
