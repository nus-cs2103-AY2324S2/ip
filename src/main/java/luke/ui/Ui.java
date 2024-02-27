package luke.ui;

import java.util.Scanner;

import luke.task.Task;
import luke.task.TaskList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs an Ui object, where the scanner is initialised.
     */
    public Ui() {
        sc = new Scanner(System.in);

    }

    /**
     * Prints the welcome message when the application starts.
     */
    public String welcome() {

        return "\nHello! I'm Luke!\nWhat can I do for you?\n";
    }

    /**
     * Prints the goodbye message when the application exits.
     */
    public String goodbye() {
        return "\nBye! Hope to see you again soon!\n";

    }

    /**
     * Prints the error message when an error occurs.
     *
     * @param message The error message to be printed.
     */
    public String getErrorMessage(String message) {
        return "\n" + message + "\n";

    }

    /**
     * Prints the list of tasks in the TaskList.
     *
     * @param tasks The TaskList to be printed.
     */
    public String printList(TaskList tasks) {
        StringBuilder listString = new StringBuilder();
        listString = new StringBuilder("\nHere are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                String taskString = i + 1 + "." + tasks.get(i);
                listString.append(taskString).append("\n");

            }
        }
        return listString.toString();
    }

    /**
     * Prints the task when the task just got marked or unmarked.
     *
     * @param task The task that is marked or unmarked.
     */
    public String printTaskMarked(Task task) {
        String status = "";
        if (task.getIsDone()) {
            status = "done:";
        } else {
            status = "not done yet:";
        }

        return "\nNice! I've marked this task as " + status + "\n" + task + "\n";

    }

    /**
     * Prints the task when added to the TaskList.
     *
     * @param task The task that is added.
     * @param size The size of the TaskList.
     */
    public String printTaskAdded(Task task, int size) {
        String taskStringType = "";
        if (size > 1) {
            taskStringType = "tasks";
        } else {
            taskStringType = "task";
        }

        return "\nGot it! I've added this task:\n" + task + "\nNow you have " + size + " "
                + taskStringType + " in the list.\n";

    }

    /**
     * Prints the task when deleted from the TaskList.
     *
     * @param task The task that is deleted.
     * @param size The size of the TaskList.
     */
    public String printTaskDeleted(Task task, int size) {
        String taskStringType = "";
        if (size > 1) {
            taskStringType = "tasks";
        } else {
            taskStringType = "task";
        }

        return "\nNoted! I've removed this task:\n" + task + "\nNow you have " + size + " "
                + taskStringType + " in the list.\n";

    }

    /**
     * Reads the command from the user.
     *
     * @return The command from the user in String format after trimming.
     */
    public String readCommand() {
        return sc.nextLine().trim();

    }

    /**
     * Prints the tasks found in the TaskList.
     *
     * @param tasks The TaskList of tasks found.
     * @return The tasks found in the TaskList as a String.
     */
    public String printTaskFound(TaskList tasks) {
        StringBuilder foundString = new StringBuilder();
        foundString = new StringBuilder("\nHere are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                String taskString = i + 1 + "." + tasks.get(i);
                foundString.append(taskString).append("\n");
            }
        }
        return foundString.toString();

    }

    /**
     * Prints the help message when the user types "help".
     *
     * @return The help message as a String.
     */
    public String help() {
        return "\nHere are the commands you can use:\n"
                + "1. todo <description> - Adds a todo task to the list.\n"
                + "2. deadline <description> /by <date> - Adds a deadline task to the list.\n"
                + "3. event <description> /from <date> /to <date> - Adds an event task to the list.\n"
                + "4. list - Lists all the tasks in the list.\n"
                + "5. find <keyword> - Finds all the tasks with the keyword in the list.\n"
                + "6. delete <index> - Deletes the task at the index from the list.\n"
                + "7. mark <index> - Marks the task at the index as done.\n"
                + "8. unmark <index> - Marks the task at the index as not done yet.\n"
                + "9. bye - Exits the application.\n"
                + "10. help - Shows the list of commands you can use.\n"
                + "\nExamples of date and time formats:\n"
                + "1. dd/MM/yyyy HH:mm\n"
                + "2. yyyy-MM-dd HH:mm\n"
                + "3. yyyy/MM/dd HH:mm\n"
                + "4. dd-MM-yyyy HH:mm\n"
                + "You can choose to enter the time or not, the default time is 00:00.\n"
                + "If you wish to enter the time, "
                + "please enter the time in 24-hour format such as HH[:mm] after the date.\n";

    }
}
