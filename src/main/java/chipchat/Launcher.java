package chipchat;

import chipchat.ui.Gui;
import javafx.application.Application;
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Starts the main program of the app.
     *
     * @param args system input arguments
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
