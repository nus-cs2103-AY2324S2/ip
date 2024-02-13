import javafx.application.Application;
import ui.Main;

/**
 * A launcher class to workaround classpath issues.
 * Referenced from the tutorial provided for Level-10.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
