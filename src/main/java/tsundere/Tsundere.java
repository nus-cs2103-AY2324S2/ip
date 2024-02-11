package tsundere;

import javafx.application.Application;
import tsundere.task.Storage;

/**
 * Encapsulates the main Tsundere chatbot object.
 */
public class Tsundere {

    private static Storage storage;

    /**
     * Initializes Tsundere with Storage.
     */
    public Tsundere() {
        storage = new Storage();
    }

    public static Storage getStorage() {
        return Tsundere.storage;
    }

    /**
     * Launches the GUI application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
