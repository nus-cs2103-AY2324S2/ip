package duke;
import duke.command.Command;

/**
 * The main class of the chatbot.
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;

    public Duke() {
        storage = new Storage("./data/storedTasks.txt");
        taskList = new TaskList();
        storage.load(taskList);
    }

    /**
     * Constructs the class Duke.
     *
     * @param filePath the path of the file that stores the chat history.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.load(taskList);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        System.out.println(Ui.showWelcomeMessage());
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = Ui.readCommand();
            Command command = Parser.parse(fullCommand);
            isExit = command.isExit();
        }

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        String response = command.execute(taskList, storage);
        if (command.isExit()) {
            javafx.application.Platform.exit();
        }
        return response;
    }

    /**
     * The main method.
     * Prepares the storage file and runs the chatbot.
     *
     * @param args The command line argument.
     */
    public static void main(String[] args) {
        new Duke("./data/storedTasks.txt").run();
    }
}
