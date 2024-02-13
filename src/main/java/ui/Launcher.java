package ui;
import javafx.application.Application;
import lelu.Lelu;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(lelu.Main.class, args);
    }
}

