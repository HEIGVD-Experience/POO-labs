package engine.tests;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.movement.promotion.PromotionUserChoice;

public class MoqChessView implements ChessView {

    private final PieceType promotionType;

    public MoqChessView() {
        promotionType = PieceType.QUEEN;
    }

    public MoqChessView(PieceType promotionType) {
        this.promotionType = promotionType;
    }

    @Override
    public void startView() {
        // Nothing to do
    }

    @Override
    public void removePiece(int x, int y) {
        // Nothing to do
    }

    @Override
    public void putPiece(PieceType type, PlayerColor color, int x, int y) {
        // Nothing to do
    }

    @Override
    public void displayMessage(String msg) {
        // Nothing to do
    }

    @Override
    public <T extends UserChoice> T askUser(String title, String question, T... possibilities) {
        if (possibilities.length > 0 && possibilities[0] instanceof PromotionUserChoice) {
            // Workaround because of Java type erasure
            return (T)PromotionUserChoice.class.cast(askUser(title, question, (PromotionUserChoice) possibilities[0]));
        }
        return possibilities.length > 0 ? possibilities[0] : null;
    }

    public PromotionUserChoice askUser(String title, String question, PromotionUserChoice... possibilities) {
        return new PromotionUserChoice(promotionType);
    }
}
