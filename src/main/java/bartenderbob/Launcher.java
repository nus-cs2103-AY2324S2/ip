package bartenderbob;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        BartenderBob bartenderBob = new BartenderBob("./data/tasks.txt");
        Application.launch(Main.class, args);
    }
}
