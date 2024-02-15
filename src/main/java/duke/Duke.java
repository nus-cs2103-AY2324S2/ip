package duke;

import java.util.ArrayList;
import java.util.Scanner;
import duke.Command.Command;

import duke.Task.Task;


/**
 * The Duke class represents a task management application.
 * It allows users to add, delete, and mark tasks as done.
 */
public class Duke {
    private TaskList taskList;

    /**
     * Constructs a Duke object.
     * Loads tasks from file and initializes the task list.
     */
    Duke() {
        ArrayList<Task> tasks = Storage.loadTasksFromFile();
        taskList = new TaskList(tasks);
    }

    /**
     * Runs the Duke application.
     * Prompts the user for commands and executes them until the user enters "bye".
     * Saves tasks to file before exiting.
     */
    void run() {
        Ui.printLogo();
        Ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String command = Ui.getUserCommand(scanner);

        while (!command.equals("bye")) {
            try {
                Command cmd = Parser.parseCommand(command);
                Ui.printMessage(cmd.execute(taskList, command));
            } catch (DukeException e) {
                Ui.printMessage("OOPS!!! " + e.getMessage());
            }
            command = Ui.getUserCommand(scanner);
        }

        Storage.saveTasksToFile(taskList.getTasks());
        Ui.printGoodbyeMessage();
    }

    /**
     * The entry point of the Duke application.
     * Creates a Duke object and runs the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}