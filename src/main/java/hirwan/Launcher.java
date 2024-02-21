package hirwan;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * the main method
     * @param args the user input
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
