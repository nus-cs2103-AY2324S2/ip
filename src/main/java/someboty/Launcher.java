package someboty;

import javafx.application.Application;
import someboty.gui.Main;

/**
 * Entry-point to the application.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            // starts the application at given class.
            Application.launch(Main.class, args);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}