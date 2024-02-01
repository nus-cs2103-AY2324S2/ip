package duke;

/**
 * This class represents the main driver of the application.
 * It initializes the task list, storage, and user interface, and starts the user interface.
 */
public class Bird {
    private Ui ui;

    /**
     * The main method of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Bird();
    }

    /**
     * Constructs a new Bird ChatBot.
     * Initializes the task list, storage, and user interface, and starts the user interface.
     */
    public Bird() {;
        TaskList.create();
        Storage.init();
        this.ui = new Ui();
        ui.run();
    }
}