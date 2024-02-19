package arc.ui.gui;

import arc.Arc;
import javafx.application.Application;

/**
 * The Gui class is responsible for launching the JavaFX GUI application for Arc.
 * It provides a method to start the GUI application by invoking the main method of the Main class.
 */
public class Gui {
    /**
     * Runs the Arc GUI application.
     * Initializes the Arc instance and invokes the main method of the Main class to start the JavaFX application.
     *
     * @param arc The Arc instance to be used by the GUI application.
     */
    public void run(Arc arc) {
        // Initialize the Arc instance
        Main.init(arc);

        // Launch the JavaFX application
        Application.launch(Main.class);
    }
}
