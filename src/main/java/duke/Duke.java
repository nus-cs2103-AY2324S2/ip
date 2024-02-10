package duke;

import java.util.Scanner;

/**
 * Represents the main class for the Duke chatbot application.
 * Duke handles user input, manages tasks, and interacts with the user through a command-line interface.
 */
public class Duke {
    /**
     * The file path for storing tasks.
     */
    private String FILE_PATH = System.getProperty("user.dir") + "/src/main/java/data/duke.txt";
    /**
     * The storage component responsible for reading and writing tasks to a file.
     */
    private Storage storage;
    /**
     * The task list containing the user's tasks.
     */
    private TaskList tasks;
    /**
     * The user interface for interacting with the user.
     */
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(filePath);
    }
    /**
     * Runs the Duke chatbot application, processing user input and executing commands until the user exits.
     */
    public void run() {
        String line = "------------------------------";
        Scanner obj = new Scanner(System.in);
        System.out.println("\n Hello! I'm Leo\n" +
                " What can I do for you?");
        System.out.println(line);

        while(obj.hasNextLine()){
            String res = obj.nextLine();
            if (res.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            }

            Parser parser = new Parser(res);
            parser.execute(tasks, ui);


        }
    }
    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
