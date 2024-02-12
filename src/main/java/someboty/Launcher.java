package someboty;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * This is directly sourced from: https://se-education.org/guides/tutorials/javaFxPart1.html
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(someBOTy.class, args);
    }
}