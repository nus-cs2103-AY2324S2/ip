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
    private Storage storage;
    /** Keeps track of the user tasks */
    private TaskList tasks;
    /** Displays the chatbot's user interface to the user */
    private Ui ui;
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
        }
    }

    /**
     * Sets Bartender Bob to begin receiving and responding to user inputs.
     */
    public void run() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.handleInput(tasks, ui);
    }

    /**
     * Initialises and runs the BartenderBob chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new BartenderBob("./data/tasks.txt").run();
    }
}
