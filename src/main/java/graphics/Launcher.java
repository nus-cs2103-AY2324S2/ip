package graphics;

import bytetalker.ByteTalker;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(ByteTalker.class, args);
    }
}
