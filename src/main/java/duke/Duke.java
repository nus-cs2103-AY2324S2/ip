
package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.task.TaskList;

/**
 * The Duke class is the main class of the FICIN Chat bot.
 */
public class Duke {
    private static TaskList taskList; // Using duke.task.TaskList to manage tasks
    private static Ui ui;

    /**
     * The main method is the entry point of the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        ui = new Ui();
        ui.showWelcome();
        taskList = new TaskList(); // Initialize the duke.task.TaskList
        try {
            taskList.getTasks().addAll(Storage.loadTasks()); // Load tasks from storage
        } catch (DukeException e) {
            System.out.println(e);
        }
        new Duke().run();
    }

    /**
     * The run method handles the main functionality of the Duke application.
     * It continuously reads user input, processes commands, and executes tasks until the user enters "bye".
     */
    public void run() {
        String userInput;

        do {
            ui.showLine();
            userInput = ui.readCommand();
            try {
                Parser.processCommand(userInput).execute(taskList, ui, userInput);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        } while (!userInput.equalsIgnoreCase("bye"));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream previous = System.out;
        System.setOut(new PrintStream(baos));
        if (ui == null) {
            ui = new Ui();
        }
        if (taskList == null) {
            taskList = new TaskList();
            try {
                taskList.getTasks().addAll(Storage.loadTasks()); // Load tasks from storage
            } catch (DukeException e) {
                return "Failed to load tasks: " + e.getMessage();
            }
        }

        try {
            Parser.processCommand(input).execute(taskList, ui, input);
        } catch (DukeException e) {
            System.out.println(e.getMessage()); // This goes to the ByteArrayOutputStream
        }

        // Restore original System.out
        System.out.flush();
        System.setOut(previous);

        // Return captured output as a string
        return baos.toString().trim();
    }
}
