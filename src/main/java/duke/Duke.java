package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class represents a simple task management program.
 * Users can add, mark as done, unmark, list, delete and exit tasks.
 * Supports three types of tasks: Todo, Deadline, and Event.
 * Provides a command-line interface for user interaction.
 *
 * <p>
 * The program initializes a user interface, a task list, and a storage mechanism.
 * Users interact with the program through the command line, providing input to perform various tasks.
 * The program handles tasks such as adding, marking as done, unmarking, listing, deleting, and exiting tasks.
 * Task data is stored and loaded using the Storage class.
 * <p>
 *
 * <p>
 * The main method creates an instance of the Duke class, initializing it with the specified storage file path.
 * The program then enters a loop, continuously reading user input, parsing commands, and updating the task list.
 * The loop continues until the user chooses to exit the program.
 * <p>
 *
 * @author Kailin Teo
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke instance with the specified storage file path.
     *
     * @param filePath The file path for storing and loading tasks.
     */

    public Duke(Storage filePath) {
        ui = new Ui();
        ui.message();

        // Create an ArrayList to store tasks
        //ArrayList<Task> myList = new ArrayList<>();
        ArrayList<Task> myList = Storage.loadTasks();

        if (myList == null) {
            myList = new ArrayList<>();
        }

        // Initialize Scanner for user input
        Scanner sc = new Scanner(System.in);
        ui.blank();
        boolean result = true;

        while (result) {
            // Read user input
            String userInput = sc.nextLine();

            Parser parser = new Parser(userInput, myList);
            result = parser.parseCommand();

            Storage.saveTasks(myList);

            if (!result) {
                ui.finalMessage();
            }
        }
        ui.blank();
    }

    /**
     * The main method to start the Duke program.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Storage storage = new Storage("./data/duke.txt");
        new Duke(storage);
    }
}
