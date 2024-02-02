package duke;

//import java.io.IOException;
//import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aether is a task-management chatbot.
 * It allows users to add, delete, mark tasks as done, and view the task list.
 * It also supports todo, deadline and event tasks
 * Duke also supports saving and loading tasks from a file.
 */

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke object with the specified file path for task storage.
     *
     * @param filePath The file path for task storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the Duke program, starting the chatbot interaction.
     */
    public void run() {
        ui.showSeparator();
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;
        ui.showSeparator();

        do {
            input = scanner.nextLine();
            processCommand(input);
        } while (!input.equalsIgnoreCase("bye"));

        scanner.close();
    }
    /**
     * Processes the user command and executes the corresponding action.
     *
     * @param input The user's input command.
     */
    private void processCommand(String input) {
        try {
            Parser parser = new Parser(input);
            Command command = parser.parse();
            command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
    /**
     * The main method to start the Duke program.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
