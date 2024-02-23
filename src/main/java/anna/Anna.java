package anna;

/**
* Represents a simple chatbot application called Anna.
*
* Anna is designed to handle and manage tasks through a command-line interface.
* It utilizes a {@link TaskList} to manage tasks, interacts with the user through a
* {@link Ui} for input/output, and persists task data using a {@link Storage} object.
*
* The main functionality of Anna involves processing user commands to perform various
* operations on the task list, such as adding, deleting, and marking tasks as done.
*
* The chatbot's behavior is encapsulated within the {@code run} method, where it
* initializes the necessary components, greets the user, and enters a loop to handle
* user commands until the user decides to exit.
*
* @see TaskList
* @see Ui
* @see Storage
*/
public class Anna {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean isContinue;

    /**
    * Constructs a Anna object with the specified folder path and file name for storage.
    *
    * @param folderPath The folder path for storing task data.
    * @param fileName   The file name for storing task data.
    */
    public Anna(String folderPath, String fileName) {
        ui = new Ui();
        storage = new Storage(folderPath, fileName);
        try {
            tasks = new TaskList(storage.load());
        } catch (AnnaException e) {
            tasks = new TaskList();
        }
    }

    /**
     * @return Greeting message
     */
    public String greet() {
        ui.greet();
        return ui.flushBuffer();
    }

    public boolean isContinue() {
        return isContinue;
    }

    /**
    * Runs the Anna chatbot application.
    */
    public String getReply(String userInput) {
        try {
            Command cmd = Parser.parseCommand(userInput);
            isContinue = ui.handleCommand(tasks, cmd);
            storage.save(tasks.getStoredTasks());
        } catch (AnnaException e) {
            ui.flushBuffer();
            ui.error(e.getMessage());
        }
        return ui.flushBuffer();
    }
}
