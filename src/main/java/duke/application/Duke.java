package duke.application;
import duke.io.Storage;
import duke.io.Ui;

/**
 * The Duke class represents the main application that manages user interactions
 * for a task management system.
 */
public class Duke {
    private static TaskList taskList;
    private static Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke instance, initializing the user interface, task list, and parser.
     * Creates the necessary storage folder and loads existing tasks from storage.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        Storage.createFolder();
        Storage.loadFile(taskList.getTaskList());
    }

    /**
     * Runs the Duke application, displaying a welcome message and handling user input in a loop.
     * Runs application until the user enters the "bye" command, saving tasks to storage
     * before exiting.
     */
    public void run() {
        ui.printLine();
        ui.showWelcomeMessage();

        while (true) {
            String input = ui.getUserInput();

            if ("bye".equalsIgnoreCase(input)) {
                ui.showByeMessage();
                Storage.saveTasks(taskList.getTaskList());
                break;
            }

            handleUserInput(input, parser);
        }
    }

    /**
     * Handles user input by delegating the processing to the parser, which interprets
     * and executes the corresponding commands on the task list.
     *
     * @param input   The user input to be processed.
     * @param parser  The parser responsible for interpreting the user input.
     */
    public static void handleUserInput(String input, Parser parser) {
        parser.handleCommand(input, taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
