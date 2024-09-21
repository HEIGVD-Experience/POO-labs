import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;

public class ChessConsole
{
    public static void main(String ... args) {
        // 1. Création du contrôleur pour gérer le jeu d’échec
        ChessController controller = new engine.ChessGame();

        // 2. Création de la vue
        ChessView view = new ConsoleView(controller);

        // 3 . Lancement du programme.
        controller.start(view);
    }
}
