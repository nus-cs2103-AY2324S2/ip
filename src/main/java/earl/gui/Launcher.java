package earl.gui;

import earl.Earl;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("nogui")) {
            Earl earl = new Earl("data/earl.txt");
            earl.run();
            return;
        }
        Application.launch(Main.class, args);
    }
}
