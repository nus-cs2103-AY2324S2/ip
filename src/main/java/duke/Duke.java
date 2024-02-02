package duke;

import duke.utils.Ui;

import java.util.*;

/**
 * The main class for the Duke chatbot application.
 * Duke is a simple chatbot designed to manage tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Keywords to terminate the Duke chatbot.
     */
    private static final String[] terminateKeywords = {"bye", "BYE", "Bye"};

    /**
     * List of keywords that trigger the termination of the Duke chatbot.
     */
    private static final List<String> exitProgramme = Arrays.asList(terminateKeywords);


    /**
     * Constructs a Duke instance with a specified file path for task data.
     *
     * @param filePath The file path to load and save task data.
     */
    public Duke(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot.
     * Processes user input until termination keywords are entered
     */
    public void run() {
        // programme start
        Ui.greet();
        Scanner input = new Scanner(System.in);
        String[] currInput = input.nextLine().split(" ", 2);

        // programme
        while (!exitProgramme.contains(currInput[0])) {
            Parser.parse(this.tasks, currInput);
            currInput = input.nextLine().split(" ", 2);
        }

        // save and exit
        this.storage.writeTasks(this.tasks);
        Ui.goodbye();
    }

    /**
     * The main method to launch the Duke chatbot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }
}
