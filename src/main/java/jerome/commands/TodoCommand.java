package jerome.commands;

import jerome.tasklist.Todo;

/**
 * Represents the command to create to do event to the data storage.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class TodoCommand extends Command {
    /**
     * Represents the command word for the To-do command.
     */
    public static final String COMMAND_WORD = "todo";

    /**
     * Represents the usage message for the To-do command.
     * Usage Example: todo eventName
     */
    public static final String MESSAGE_USAGE = "\t" + COMMAND_WORD + ": Adds a todo task. \n"
            + "\t Example: " + COMMAND_WORD
            + " boil coconut milk";

    /**
     * Represents the message after the event is successfully added.
     * Contains the added event details and the current number of tasks in the database.
     */
    public static final String MESSAGE_SUCCESS = "\t Got it. I've added this task:\n"
            + "\t %s\n"
            + "\t Now you have %d task(s) available!";

    /**
     * Represents error message when the event name is blank.
     */
    public static final String MESSAGE_BLANK_EVENT = "\t Error: The event name cannot be empty!\n" + MESSAGE_USAGE;

    /**
     * Represents the To-do object that is to be stored.
     */
    private final Todo toAdd;

    /**
     * Represents the TodoCommand, which adds a to-do task to the data storage.
     * This command takes in a task name and creates a new To-do object with the task name.
     *
     * @param taskName the name of the task to be added.
     */
    public TodoCommand(String taskName) {
        this.toAdd = new Todo(taskName, false);
    }

    /**
     * Executes the command by adding a task to the data storage and returning a command result.
     * @return CommandResult object containing success message and task count in the custom format above.
     */
    @Override
    public CommandResult execute() {
        dataStorage.addTaskToTextFile(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString(),
                dataStorage.getTaskCount()));
    }
}
