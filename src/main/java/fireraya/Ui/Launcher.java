package fireraya.Ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Entry point into the program.
     *
     * @param args user input.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

