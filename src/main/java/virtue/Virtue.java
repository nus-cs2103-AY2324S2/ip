package virtue;

public class Virtue {
    /** The user interface that interacts with the user. */
    private Ui ui;

    /** The storage that handles file reading and writing. */
    private final Storage storage;

    /** The task list to be used by the chatbot. */
    private VirtueTaskList tasks;

    /** Creates a new Virtue class instance. */
    public Virtue() {
        storage = new Storage();
    }

    /** Runs the chatbot. */
    private void run() {
        try {
            tasks = storage.loadTaskList();
            ui = new Ui(tasks, storage);
            ui.run();
        } catch (VirtueDateTimeException e) {
            System.out.println("OOPS! There is a date not in the correct format.");
        }
    }

    public static void main(String[] args) {
        Virtue virtue = new Virtue();
        virtue.run();
    }
}
