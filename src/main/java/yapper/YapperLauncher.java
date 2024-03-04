package yapper;

import javafx.application.Application;

/**
 * The launcher class for the YapperGui application.
 * It uses JavaFX's Application's launch method to start the GUI.
 */
public class YapperLauncher {
    public static void main(String[] args) {
        // Initialize Yapper
        Application.launch(Main.class, args);
    }
}
