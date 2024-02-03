
package duke;

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
}
