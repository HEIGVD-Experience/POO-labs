package engine.tests;

import chess.ChessView;
import chess.PieceType;
import engine.ChessGame;
import engine.Position;
import engine.board.Board;
import engine.board.BoardFactory;
import engine.board.FENBoardFactory;
import engine.fen.FENImageDownloader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class ChessTestCases {

    /**
     * Set this constant to true if you want to enable the documentation generation
     */
    private static final boolean GENERATE_DOC = true;

    public void runAllTests() {

        // If the document generation was enabled
        if(GENERATE_DOC)
        {
            // Regenerate markdown
            regenerateOutputMarkdown();
        }

        // Run all tests
        testWhitePawnBackward();
        testBlackPawnBackward();
        testWhitePawnCapture();
        testBlackPawnCapture();
        testWhitePawnForward();
        testBlackPawnForward();
        testWhitePawnEnPassant();
        testBlackPawnEnPassant();
        testWhiteWrongPawnEnPassant();
        testBlackWrongPawnEnPassant();
        testWhitePawnDoubleMove();
        testBlackPawnDoubleMove();
        testWhitePawnBlocked();
        testBlackPawnBlocked();
        testPawnPromotion();
        testBishopLongMove();
        testBishopSmallMove();
        testBishopBlocked();
        testBishopWrongMove();
        testEscapeFromCheck();
        testMoveInCheck();
        testSelfDiscoverCheck();
        testWhiteKingSideCastling();
        testBlackKingSideCastling();
        testWhiteQueenSideCastling();
        testBlackQueenSideCastling();
        testWhiteKingSideNotAvailableCastling();
        testBlackKingSideNotAvailableCastling();
        testWhiteQueenSideNotAvailableCastling();
        testBlackKingSideNotAvailableCastling();
    }

    public void testWhitePawnForward() {

        // The pawn should have moved
        runTestCase("WhitePawnForward", "E2", "E3",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -",
                "rnbqkbnr/pppppppp/8/8/8/4P3/PPPP1PPP/RNBQKBNR b KQkq -");
    }

    public void testBlackPawnForward() {

        // The pawn should have moved
        runTestCase("BlackPawnForward", "E7", "E6",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq -",
                "rnbqkbnr/pppp1ppp/4p3/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -");
    }

    public void testWhitePawnBackward() {

        // The pawn should not have moved
        runTestCase("WhitePawnBackward", "E2", "E1",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -");
    }

    public void testBlackPawnBackward() {

        // The pawn should not have moved
        runTestCase("BlackPawnBackward", "E7", "E8",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq -",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq -");
    }

    public void testWhitePawnCapture() {

        // The white pawn should have captured the black pawn
        runTestCase("WhitePawnCapture", "D4", "E5",
                "rnbqkbnr/pppp1ppp/8/4p3/3P4/8/PPP1PPPP/RNBQKBNR w KQkq -",
                "rnbqkbnr/pppp1ppp/8/4P3/8/8/PPP1PPPP/RNBQKBNR b KQkq -");
    }

    public void testBlackPawnCapture() {

        // The black pawn should have captured the white pawn
        runTestCase("BlackPawnCapture", "E5", "D4",
                "rnbqkbnr/pppp1ppp/8/4p3/3P4/5N2/PPP1PPPP/RNBQKB1R b KQkq -",
                "rnbqkbnr/pppp1ppp/8/8/3p4/5N2/PPP1PPPP/RNBQKB1R w KQkq -");
    }

    public void testWhitePawnEnPassant() {

        // The pawn should perform en passant capture
        runTestCase("WhitePawnEnPassant", "F5", "G6",
                "rnbqkbnr/pppppp1p/8/5Pp1/8/8/PPPPP1PP/RNBQKBNR w KQkq g6",
                "rnbqkbnr/pppppp1p/6P1/8/8/8/PPPPP1PP/RNBQKBNR b KQkq -");
    }

    public void testBlackPawnEnPassant() {

        // The pawn should perform en passant capture
        runTestCase("BlackPawnEnPassant", "C4", "B3",
                "rnbqkbnr/pp1ppppp/8/8/1Pp5/8/P1PPPPPP/RNBQKBNR b KQkq b3",
                "rnbqkbnr/pp1ppppp/8/8/8/1p6/P1PPPPPP/RNBQKBNR w KQkq -");
    }

    public void testWhiteWrongPawnEnPassant() {

        // The pawn should not perform en passant capture
        runTestCase("WhiteWrongPawnEnPassant", "F5", "G6",
                "rnbqkbnr/pppppp1p/8/5Pp1/8/8/PPPPP1PP/RNBQKBNR w KQkq -",
                "rnbqkbnr/pppppp1p/8/5Pp1/8/8/PPPPP1PP/RNBQKBNR w KQkq -");
    }

    public void testBlackWrongPawnEnPassant() {

        // The pawn should not perform en passant capture
        runTestCase("BlackWrongPawnEnPassant", "C4", "B3",
                "rnbqkbnr/pp1ppppp/8/8/1Pp5/8/P1PPPPPP/RNBQKBNR b KQkq -",
                "rnbqkbnr/pp1ppppp/8/8/1Pp5/8/P1PPPPPP/RNBQKBNR b KQkq -");
    }

    public void testWhitePawnDoubleMove() {

        // The pawn should move two squares
        runTestCase("WhitePawnDoubleMove", "E2", "E4",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -",
                "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3");
    }

    public void testBlackPawnDoubleMove() {

        // The pawn should move two squares
        runTestCase("BlackPawnDoubleMove", "E7", "E5",
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq -",
                "rnbqkbnr/pppp1ppp/8/4p3/8/8/PPPPPPPP/RNBQKBNR w KQkq e6");
    }

    public void testWhitePawnBlocked() {

        // The pawn should not move
        runTestCase("WhitePawnBlocked", "D2", "D4",
                "rnbqkbnr/ppp1pppp/8/8/3p4/8/PPPPPPPP/RNBQKBNR w KQkq -",
                "rnbqkbnr/ppp1pppp/8/8/3p4/8/PPPPPPPP/RNBQKBNR w KQkq -");
    }

    public void testBlackPawnBlocked() {

        // The pawn should not move
        runTestCase("BlackPawnBlocked", "E7", "E5",
                "rnbqkbnr/pppppppp/8/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq -",
                "rnbqkbnr/pppppppp/8/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq -");
    }

    public void testPawnPromotion() {

        String from = "G7";
        String to = "G8";

        // The pawn should be promoted to Queen
        runTestCase("PawnQueenPromotion", from, to,
                "rnbqkb1r/ppp1n1Pp/3p4/8/8/8/PPP1PPPP/RNBQKBNR w KQkq -",
                "rnbqkbQr/ppp1n2p/3p4/8/8/8/PPP1PPPP/RNBQKBNR b KQkq -",
                PieceType.QUEEN);

        // The pawn should be promoted to Rook
        runTestCase("PawnRookPromotion", from, to,
                "rnbqkb1r/ppp1n1Pp/3p4/8/8/8/PPP1PPPP/RNBQKBNR w KQkq -",
                "rnbqkbRr/ppp1n2p/3p4/8/8/8/PPP1PPPP/RNBQKBNR b KQkq -",
                PieceType.ROOK);

        // The pawn should be promoted to Knight
        runTestCase("PawnKnightPromotion", from, to,
                "rnbqkb1r/ppp1n1Pp/3p4/8/8/8/PPP1PPPP/RNBQKBNR w KQkq -",
                "rnbqkbNr/ppp1n2p/3p4/8/8/8/PPP1PPPP/RNBQKBNR b KQkq -",
                PieceType.KNIGHT);

        // The pawn should be promoted to Bishop
        runTestCase("PawnBishopPromotion", from, to,
                "rnbqkb1r/ppp1n1Pp/3p4/8/8/8/PPP1PPPP/RNBQKBNR w KQkq -",
                "rnbqkbBr/ppp1n2p/3p4/8/8/8/PPP1PPPP/RNBQKBNR b KQkq -",
                PieceType.BISHOP);

        try {
            // The pawn should not be promoted to pawn and an error should be thrown
            runTestCase("PawnErrorPawnPromotion", from, to,
                    "rnbqkb1r/ppp1n1Pp/3p4/8/8/8/PPP1PPPP/RNBQKBNR w KQkq -",
                    "rnbqkb1r/ppp1n1Pp/3p4/8/8/8/PPP1PPPP/RNBQKBNR w KQkq -",
                    PieceType.PAWN);

            printFailMessage("PawnErrorPawnPromotion", "the promotion didn't threw an error", "an exception should be thrown");
        }
        catch (Exception ex) {
            printSuccessMessage("PawnErrorPawnPromotion");
        }

        try {
            // The pawn should not be promoted to king and an error should be thrown
            runTestCase("PawnErrorKingPromotion", from, to,
                    "rnbqkb1r/ppp1n1Pp/3p4/8/8/8/PPP1PPPP/RNBQKBNR w KQkq -",
                    "rnbqkb1r/ppp1n1Pp/3p4/8/8/8/PPP1PPPP/RNBQKBNR w KQkq -",
                    PieceType.KING);

            printFailMessage("PawnErrorKingPromotion", "the promotion didn't threw an error", "an exception should be thrown");
        }
        catch (Exception ex) {
            printSuccessMessage("PawnErrorKingPromotion");
        }
    }

    public void testBishopLongMove() {

        // The bishop should move
        runTestCase("BishopLongMove", "A1", "H8",
                "4k3/8/8/8/8/8/8/B3K3 w - -",
                "4k2B/8/8/8/8/8/8/4K3 b - -");
    }

    public void testBishopSmallMove() {

        // The bishop should move
        runTestCase("BishopSmallMove", "G8", "F7",
                "4k1b1/8/8/8/8/8/8/4K3 b - -",
                "4k3/5b2/8/8/8/8/8/4K3 w - -");
    }

    public void testBishopBlocked() {

        // The bishop should not move
        runTestCase("BishopBlocked", "G8", "F7",
                "4k3/6b1/8/8/8/8/1B6/4K3 w - -",
                "4k3/6b1/8/8/8/8/1B6/4K3 w - -");
    }

    public void testBishopWrongMove() {

        // The bishop should not move
        runTestCase("BishopWrongMove", "A1", "A8",
                "4k3/8/8/8/8/8/8/B3K3 b - -",
                "4k3/8/8/8/8/8/8/B3K3 b - -");
    }

    public void testEscapeFromCheck() {

        // The king should escape from check
        runTestCase("EscapeFromCheck", "E8", "D8",
                "4k3/8/8/8/8/8/4Q3/4K3 b - -",
                "3k4/8/8/8/8/8/4Q3/4K3 w - -");
    }

    public void testMoveInCheck() {

        // The king should not move in check
        runTestCase("MoveInCheck", "D8", "E8",
                "3k4/8/8/8/8/8/4Q3/4K3 b - -",
                "3k4/8/8/8/8/8/4Q3/4K3 b - -");
    }

    public void testSelfDiscoverCheck() {

        // The king should be left in check
        runTestCase("SelfDiscoverCheck", "E7", "B5",
                "4k3/4q3/8/8/8/8/4Q3/4K3 w - -",
                "4k3/4q3/8/8/8/8/4Q3/4K3 w - -");
    }

    public void testWhiteKingSideCastling() {

        // The king should castle king side
        runTestCase("WhiteKingSideCastling", "E1", "G1",
                "r3k2r/8/8/8/8/8/8/R3K2R w KQkq -",
                "r3k2r/8/8/8/8/8/8/R4RK1 b kq -");
    }

    public void testBlackKingSideCastling() {

        // The king should castle king side
        runTestCase("BlackKingSideCastling", "E8", "G8",
                "r3k2r/8/8/8/8/8/8/R3K2R b KQkq -",
                "r4rk1/8/8/8/8/8/8/R3K2R w KQ -");
    }

    public void testWhiteQueenSideCastling() {

        // The king should castle queen side
        runTestCase("WhiteQueenSideCastling", "E1", "C1",
                "r3k2r/8/8/8/8/8/8/R3K2R w KQkq -",
                "r3k2r/8/8/8/8/8/8/2KR3R b kq -");
    }

    public void testBlackQueenSideCastling() {

        // The queen should castle queen side
        runTestCase("BlackQueenSideCastling", "E8", "C8",
                "r3k2r/8/8/8/8/8/8/R3K2R b KQkq -",
                "2kr3r/8/8/8/8/8/8/R3K2R w KQ -");
    }

    public void testWhiteKingSideNotAvailableCastling() {

        // The king should not castle king side
        runTestCase("WhiteKingSideNotAvailableCastling", "E1", "G1",
                "r3k2r/8/8/8/8/8/8/R3K2R w - -",
                "r3k2r/8/8/8/8/8/8/R3K2R w - -");
    }

    public void testBlackKingSideNotAvailableCastling() {

        // The king should not castle king side
        runTestCase("BlackKingSideNotAvailableCastling", "E8", "G8",
                "r3k2r/8/8/8/8/8/8/R3K2R b - -",
                "r3k2r/8/8/8/8/8/8/R3K2R b - -");
    }

    public void testWhiteQueenSideNotAvailableCastling() {

        // The king should not castle queen side
        runTestCase("WhiteQueenSideNotAvailableCastling", "E1", "C1",
                "r3k2r/8/8/8/8/8/8/R3K2R w - -",
                "r3k2r/8/8/8/8/8/8/R3K2R w - -");
    }

    public void testBlackQueenSideNotAvailableCastling() {

        // The queen should not castle queen side
        runTestCase("BlackQueenSideNotAvailableCastling", "E8", "C8",
                "r3k2r/8/8/8/8/8/8/R3K2R b - -",
                "r3k2r/8/8/8/8/8/8/R3K2R b - -");
    }

    private static final String SUCCESS_MESSAGE = "The test %s was successful\n";
    private static final String FAIL_MESSAGE = "The test %s failed [result : %s, expected : %s]\n";
    private static final String IMAGE_PATH = "doc/img/tests/";
    private static final String OUTPUT_MARKDOWN_PATH = "doc/tests.md";

    private static Position pos(String pos) {
        if(pos.length() != 2) throw new IllegalArgumentException("The position should be made of 1 character and 1 number representing the column and line");

        int column = pos.charAt(0) - 'A';
        int line = pos.charAt(1) - '1';

        if(column < 0 || column >= Board.BOARD_WIDTH) throw new IllegalArgumentException("The first character is not valid");
        if(line < 0 || line >= Board.BOARD_HEIGHT) throw new IllegalArgumentException("The second character is not valid");

        return new Position(column, line);
    }

    private void runTestCase(String testName, String from, String to, String initialPosition, String expectedPosition) {
        runTestCase(testName, from, to, initialPosition, expectedPosition, PieceType.QUEEN);
    }

    private void runTestCase(String testName, String from, String to, String initialPosition, String expectedPosition, PieceType promotionType) {
        Position startPos = pos(from);
        Position endPos = pos(to);
        Consumer<ChessGame> action = (b) -> b.move(startPos.column(), startPos.line(), endPos.column(), endPos.line());
        runTestCase(testName, String.format("%s to %s", from, to), initialPosition, expectedPosition, action, promotionType);
    }

    private void runTestCase(String testName, String actionName, String initialPosition, String expectedPosition, Consumer<ChessGame> action, PieceType promotionType) {
        runTestCase(testName, actionName, new FENBoardFactory(initialPosition), expectedPosition, action, promotionType);
    }

    private void runTestCase(String testName, String actionName, BoardFactory boardFactory, String expectedFENPos, Consumer<ChessGame> action, PieceType promotionType) {

        // Initialize the chess game
        ChessView chessView = new MoqChessView(promotionType);
        ChessGame chessGame = new ChessGame();
        chessGame.start(chessView, boardFactory);
        chessGame.newGame();

        // Get the start FEN pos
        String startFENPos = chessGame.getFENCode();

        // Execute the actions
        action.accept(chessGame);

        // Get the end FEN pos
        String endFENPos = chessGame.getFENCode();

        // Test if the result was correct
        boolean wasSuccessful = endFENPos.equals(expectedFENPos);
        if(wasSuccessful)
            printSuccessMessage(testName);
        else
            printFailMessage(testName, endFENPos, expectedFENPos);

        // If the document generation was enabled
        if(GENERATE_DOC) {

            // Download the images
            String beforeImagePath = String.format("%s%s_Before.png", IMAGE_PATH, testName);
            String expectedImagePath = String.format("%s%s_Expected.png", IMAGE_PATH, testName);
            String resultImagePath = String.format("%s%s_result.png", IMAGE_PATH, testName);
            FENImageDownloader.SaveToPNG(startFENPos, beforeImagePath);
            FENImageDownloader.SaveToPNG(expectedFENPos, expectedImagePath);
            FENImageDownloader.SaveToPNG(endFENPos, resultImagePath);

            // Add tests to markdown
            addToOutputMarkdown(wasSuccessful, testName, actionName, beforeImagePath, expectedImagePath, resultImagePath);
        }
    }

    private void printSuccessMessage(String testName) {
        System.out.printf(SUCCESS_MESSAGE, testName);
    }

    private void printFailMessage(String testName, String result, String expectedResult) {
        System.out.printf(FAIL_MESSAGE, testName, result, expectedResult);
    }

    private void regenerateOutputMarkdown() {
        try {
            FileWriter writer = new FileWriter(OUTPUT_MARKDOWN_PATH);
            writer.write("### Test Cases\n\n");
            writer.write("| **Status** | **Test Name** | **Action** |**Starting position** | **Result Position** | **Expected Position** |\n");
            writer.write("|------------|---------------|------------|----------------------|---------------------|-----------------------|\n");
            writer.close();
        } catch (IOException e) {
            // ignore
        }
    }

    private void addToOutputMarkdown(boolean wasSuccessful, String testName, String actionName, String beforeImageURL, String expectedImageURL, String resultImageURL) {
        try {
            FileWriter writer = new FileWriter(OUTPUT_MARKDOWN_PATH, true);
            writer.write("|" + (wasSuccessful
                    ? "<font color=\"green\">**Successful**</font>"
                    : "<font color=\"red\">**Failed**</font>"));
            writer.write("|" + testName);
            writer.write("|" + actionName);
            writer.write("|<img src=\"../" + beforeImageURL + "\" alt=\"Before Image\" width=\"200\"/>");
            writer.write("|<img src=\"../" + expectedImageURL + "\" alt=\"Expected Image\" width=\"200\"/>");
            writer.write("|<img src=\"../" + resultImageURL + "\" alt=\"Result Image\" width=\"200\"/>");
            writer.write("|\n");
            writer.close();
        } catch (IOException e) {
            // ignore
        }
    }
}
