package duke.ui.gui;

import duke.Duke;
import javafx.application.Application;

/**
 * The Gui class is responsible for launching the JavaFX GUI application for Duke.
 * It provides a method to start the GUI application by invoking the main method of the Main class.
 */
public class Gui {
    /**
     * Runs the Duke GUI application.
     * Initializes the Duke instance and invokes the main method of the Main class to start the JavaFX application.
     *
     * @param duke The Duke instance to be used by the GUI application.
     */
    public void run(Duke duke) {
        // Initialize the Duke instance
        Main.init(duke);

        // Launch the JavaFX application
        Application.launch(Main.class);
    }
}
