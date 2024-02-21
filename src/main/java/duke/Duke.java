package duke;

import duke.commands.Command;
import duke.utils.Ui;

/**
 * The main class for the Duke chatbot application.
 * Duke is a simple chatbot designed to manage tasks.
 */
public class Duke {
    /**
     * The file path to load and save task data.
     */
    private static final String DEFAULT_PATH = "./data/data.txt";

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke instance with a specified file path for task data.
     */
    public Duke() {
        try {
            this.storage = new Storage(DEFAULT_PATH);
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }

        assert this.tasks != null : "Task should not be null";
    }

    /**
     * Runs the Duke chatbot in CLI.
     * Processes user input until termination keywords are entered
     */
    public void run() {
        // programme start
        System.out.println(Ui.LINE + Ui.greet() + Ui.LINE);
        String input = Parser.getUserInput();
        boolean isExit = Parser.isExit(input);

        // programme
        while (!isExit) {
            try {
                Command cmd = Parser.parse(input, this.tasks);
                System.out.println(cmd.execute());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(Ui.LINE);
                // prompt and get next input
                input = Parser.getUserInput();
                isExit = Parser.isExit(input);
            }
        }

        // save and exit
        this.storage.writeTasks(this.tasks);
        Ui.goodbye();
    }

    /**
     * Launches the Duke chatbot application in CLI.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke chatbot in GUI.
     */
    public String getResponse(String input) {
        try {
            if (Parser.isExit(input)) {
                this.storage.writeTasks(this.tasks);
                javafx.application.Platform.exit();
            }
            Command cmd = Parser.parse(input, this.tasks);
            String response = cmd.execute();
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
