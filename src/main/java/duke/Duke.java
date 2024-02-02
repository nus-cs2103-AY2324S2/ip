package duke;

import javafx.application.Platform;

import java.io.IOException;
import java.util.Scanner;


/**
 * The Duke class is the main class of the Duke application.
 * It is responsible for the initialization of the Storage and TaskList instances.
 * It also contains the main method that serves as the entry point for the Duke application.
 */
public class Duke {
    private static TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Duke.
     * Initializes the Storage and TaskList instances.
     *
     * @param filePath The path to the file where the task list is stored.
     * @throws DukeException If an error occurs while loading the task list.
     */
    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The main method that serves as the entry point for the Duke application.
     * Initializes the Duke instance and processes the user input until the "bye" command is given.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException, DukeException {
        try {
            Duke duke = new Duke("./data/duke.txt");
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }

        Scanner scanner = new Scanner(System.in);
        Ui.printWithLines("Hello! I'm Bob!", "What can I do for you?");

        boolean isRunning = true;
        while (isRunning) {
            String message = scanner.nextLine();
            Ui.parse(tasks, message);
            isRunning = !message.equals("bye");
        }

        Storage.saveCurrentList(tasks);
        Ui.printWithLines("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;

        if (input.equals("bye")) {
            try {
                Storage.saveCurrentList(tasks);
                Platform.exit();
            } catch (IOException e) {
                return "Error saving file";
            }
            return "Bye. Hope to see you again soon!";
        } else {
            try {
                response = Ui.parse(tasks, input);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return response;
    }


}
