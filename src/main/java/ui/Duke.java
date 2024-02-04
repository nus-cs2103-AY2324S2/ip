package ui;

import static parser.Parser.parseCommand;

import java.util.ArrayList;
import java.util.Scanner;

import actions.Action;
import actions.Greet;
import exceptionhandling.DukeException;
import storage.Storage;
import tasklist.TaskList;


/**
 * Duke is a simple chatbot application that allows users to manage a list of tasks.
 * It provides features such as adding tasks, marking tasks as done, deleting tasks,
 * and saving tasks to and loading tasks from a file.
 */
public class Duke {
    private String name;

    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Duke chatbot with the specified name.
     *
     * @param name The name of the Duke chatbot.
     */
    public Duke(String name) {
        this.name = name;
        this.taskList = new TaskList(new ArrayList<>());
        this.storage = new Storage();
    }

    /**
     * Gets the name of the Duke chatbot.
     *
     * @return The name of the Duke chatbot.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Exits the Duke chatbot application, saving the current task list to a file.
     * Displays a farewell message before terminating the program.
     */
    public void exit() {
        this.storage.save(this.taskList);
        System.out.println("Bye. Hope to see you again soon!\n");
        System.exit(0);
    }

    /**
     * Gets the current task list of the Duke chatbot.
     *
     * @return The TaskList containing the tasks managed by the Duke chatbot.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * The main method that initializes the Duke chatbot, loads existing tasks from a file,
     * greets the user, and starts an interactive command-line interface for user input.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        Duke chatbot = new Duke("Bob");
        chatbot.storage.load(chatbot);
        Greet g = new Greet();
        g.execute(chatbot);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            try {
                Action action = parseCommand(command);
                action.execute(chatbot);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number as your index");
            }
        }
    }
}
