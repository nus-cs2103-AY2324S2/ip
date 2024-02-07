package duke.gui;

import javafx.application.Application;

import duke.Main;

/**
 * A Launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
