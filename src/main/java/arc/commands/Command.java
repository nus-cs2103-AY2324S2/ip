package arc.commands;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.TaskList;

/**
 * Represents an abstract command in the Arc application. All specific command
 * classes should extend this base class and implement the execute() method.
 * <p>
 * Commands are responsible for performing actions in response to user input.
 * They interact with the task list, user interface (UI), and storage components
 * to carry out their respective tasks. Each command may have a specific action
 * associated with it, such as adding a task, listing tasks, marking tasks as done,
 * and more.
 */
public abstract class Command {

    public static final String HELP_MESSAGE = "Here are examples of the commands I understand:\n\n"
        + "todo <todo_name>\n"
        + "deadline <deadline_name> /by 2024-02-20\n"
        + "event <event_name> /from 2024-02-20 /to 2025-02-20\n"
        + "list\n"
        + "find\n"
        + "sort-deadlines\n"
        + "mark <task_number>\n"
        + "unmark <task_number>\n"
        + "delete <task_number>\n"
        + "bye\n";

    /**
     * Enumeration representing the different types of commands available in the Arc application.
     * These commands are used to perform various actions and operations within the application.
     */
    public enum CommandType {
        /**
         * Command to list all tasks in the task list.
         */
        LIST,

        /**
         * Command to exit or quit the Arc application.
         */
        BYE,

        /**
         * Command to mark a task as completed.
         */
        MARK,

        /**
         * Command to unmark or mark a task as incomplete.
         */
        UNMARK,

        /**
         * Command to create and add a "To-Do" task to the task list.
         */
        TODO,

        /**
         * Command to create and add a "Deadline" task to the task list.
         */
        DEADLINE,

        /**
         * Command to create and add an "Event" task to the task list.
         */
        EVENT,

        /**
         * Command to delete a task from the task list.
         */
        DELETE,

        /**
         * Command to find and display tasks that match a specific keyword.
         */
        FIND,

        /**
         * Command to sort deadline tasks chronologically.
         */
        SORT_DEADLINES;

        /**
         * Converts a given string representing a command to its corresponding {@code CommandType} enum constant.
         * This method allows for flexible string formats by replacing hyphens with underscores and converting
         * the string to uppercase, which matches the enum naming convention in Java. This is particularly useful
         * when the input string format does not directly match the naming of enum constants.
         *
         * @param command The string representation of the command type. It is case-insensitive and can contain
         *                hyphens as separators.
         * @return The {@code CommandType} enum constant that matches the input string.
         * @throws IllegalArgumentException if the input string does not match any {@code CommandType} enum constant.
         */
        public static CommandType fromString(String command) throws IllegalArgumentException {
            // Replace hyphens with underscores and make uppercase to match enum naming convention
            String formattedCommand = command.replace('-', '_').toUpperCase();
            return CommandType.valueOf(formattedCommand);
        }
    }


    /**
     * Constructs a new Command object.
     * This constructor is empty and serves as a placeholder.
     */
    public Command() {}

    /**
     * Indicates whether executing this command should result in exiting the Arc application.
     * By default, commands do not trigger an exit.
     *
     * @return true if executing this command should exit the application, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command's action, which may involve interacting with the task list,
     * user interface (UI), and storage. Specific command classes must implement this
     * method to define their behavior when executed.
     *
     * @param tasks   The task list containing tasks managed by the application.
     * @param storage The storage component responsible for saving and loading tasks.
     * @throws ArcException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws ArcException;
}
