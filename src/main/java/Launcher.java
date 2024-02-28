import javafx.application.Application;
import ui.Main;

/**
 * Acts as a launcher class to workaround classpath issues.
 *
 * References from the tutorial provided for Level-10.
 */
public class Launcher {
    /*
    * Main method to call the Main.class to initialize the UI package.
    */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
