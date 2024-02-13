package duke;

/**
 * Represents the main class for the Duke application.
 */
public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        String name = "Georgie";
        TaskList taskList = new TaskList();

        Storage storage = new Storage(FILE_PATH);
        storage.loadTasksFromFile(taskList.getTasks());

        Ui.showWelcomeMessage();

        while (true) {
            try {
                String userInput = Ui.getUserInput();
                CommandHandler.handleCommand(userInput, taskList);
                storage.saveTasksToFile(taskList.getTasks());
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            }
        }
    }
}