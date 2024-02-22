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
     * Entry point of the Duke application.
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
        if (taskList.getSize() == 0) {
            new Ui().showHelp();
        }
        assert taskList != null : "taskList should not be null after initialization";
        new Duke().run();
    }

    /**
     * Handles the main functionality of the Duke application.
     * Continuously reads user input, processes commands, and executes tasks until the user enters "bye".
     */
    public void run() {
        String userInput;

        do {
            ui.showLine();
            userInput = ui.readCommand();
            assert userInput != null && !userInput.trim().isEmpty() : "userInput should not be null or empty";
            try {
                Parser.processCommand(userInput).execute(taskList, ui, userInput);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        } while (!userInput.equalsIgnoreCase("bye"));
    }

    /**
     * Generates a response to user input.
     *
     * @param input User input.
     * @return Response as a string.
     */
    String getResponse(String input) {
        // Following has debugged by Chat GPT
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream previous = System.out;
        System.setOut(new PrintStream(baos));
        if (ui == null || taskList == null) {
            ui = new Ui();
            taskList = new TaskList();
            try {
                taskList.getTasks().addAll(Storage.loadTasks());
            } catch (DukeException e) {
                return "Failed to load tasks: " + e.getMessage();
            }
        }
        assert ui != null : "Ui should not be null";
        assert taskList != null : "TaskList should not be null";

        try {
            Parser.processCommand(input).execute(taskList, ui, input);
        } catch (DukeException e) {
            System.out.println(e.getMessage()); // This goes to the ByteArrayOutputStream
        }

        // Restore original System.out
        System.out.flush();
        System.setOut(previous);

        return baos.toString().trim();
    }
}
