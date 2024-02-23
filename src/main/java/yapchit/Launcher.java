package yapchit;
import javafx.application.Application;
import yapchit.yapchitui.Main;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Entry point into the program.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

