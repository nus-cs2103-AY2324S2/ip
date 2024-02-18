package someboty;

import javafx.application.Application;

import someboty.GUI.Main;

/**
 * A launcher class to workaround classpath issues.
 * This is directly sourced from: https://se-education.org/guides/tutorials/javaFxPart1.html
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}