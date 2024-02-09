package duchess.launcher;

import duchess.Duchess;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duchess.class, args);
    }
}
