package duke;

import duke.ui.gui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Entry point to the Duke app.
 */
public class Launcher {
    public static void main(String[] args) {
        checkIsAssertionOn();
        Application.launch(Main.class, args);
    }

    private static void checkIsAssertionOn() {
        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect!!!
        if (!assertsEnabled) {
            throw new RuntimeException("Asserts must be enabled!!!");
        }
    }
}