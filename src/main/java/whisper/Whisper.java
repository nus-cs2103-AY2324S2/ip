package whisper;

/**
 * The Whisper class represents the main application that interacts with the user,
 * manages tasks, and handles input/output.
 *
 * <p>It uses a simple chat-bot interface to receive user input, process commands,
 * and provide responses based on the underlying task management system.</p>
 */
public class Whisper {
    /**
     * The storage instance responsible for handling data persistence.
     */
    private Storage storage;

    /**
     * The task list that stores and manages tasks.
     */
    private TaskList tasks;

    /**
     * The user interface handler for input/output operations.
     */
    private Ui ui;

    /**
     * The file path for storing and retrieving task data.
     */
    private static final String FILE_PATH = "whisper.txt";

    /**
     * Constructs a new Whisper instance. Initializes the user interface, storage, and task list.
     * If a stored task list is available, it loads the data; otherwise, it initializes an empty task list.
     */
    public Whisper() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);

        assert storage != null : "Storage should not be null after initialization";

        try {
            tasks = new TaskList(storage.load());
        } catch (WhisperException e) {
            ui.printLoadFileError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input, executes commands, and returns the response.
     *
     * @param input The user input to process.
     * @return The response generated based on the processed input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, ui);
            command.execute(tasks.getTaskList(), ui, storage);
            tasks = new TaskList(storage.load());
            return ui.getResponse();
        } catch (WhisperException e) {
            return "Error: " + e.getMessage();
        }
    }
}
