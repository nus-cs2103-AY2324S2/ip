package bartenderbob;

/**
 * Represents a BartenderBob chatbot that stores tasks.
 * @author Gavin Goh
 * @version 0.1
 */
public class BartenderBob {
    /** Name of the chatbot */
    private static final String NAME = "BartenderBob";
    /** Save tasks to the user's hard disk */
    private static Storage storage;
    /** Keeps track of the user tasks */
    private static TaskList tasks;
    /** Displays the chatbot's user interface to the user */
    private static Ui ui;
    public BartenderBob() {

    }
    /**
     * Creates a BartenderBob with the specified file path.
     *
     * @param filePath The file path of the stored tasks.
     */
    public BartenderBob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showWelcomeMessage(NAME);
        } catch (BartenderBobException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        assert tasks != null : "tasks cannot be null";
    }
    String getResponse(String input) {
        return InputHandler.handleInput(tasks, ui, input);
    }
}
