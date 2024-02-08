package duke;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot in CLI.
     * Processes user input until termination keywords are entered
     */
    public void run() {
        // programme start
        Ui.greet();
        String input = Parser.getUserInput();
        boolean isExit = Parser.isExit(input);

        // programme
        while (!isExit) {
            try {
                Command cmd = Parser.parse(input, this.tasks);
                cmd.execute();
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
     * The main method to launch the Duke chatbot in CLI.
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
        String[] currInput = input.split(" ", 2);
        return "Duke heard: " + input;
    }

}
