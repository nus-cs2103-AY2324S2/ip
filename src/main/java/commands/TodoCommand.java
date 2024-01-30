package commands;

import tasklist.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task. \n"
            + "Example: " + COMMAND_WORD
            + " boil coconut milk";

    // TODO: Using string interpolation to make it better!
    public static final String MESSAGE_SUCCESS = "\t Got it. I've added this task:\n";

    public static final String MESSAGE_INVALID_COMMAND = "\t Invalid Command Format.";
    public static final String MESSAGE_BLANK_EVENT = "\t The event name must not only contain white space.";

    private final Todo toAdd;

    public TodoCommand(String taskName) {
        this.toAdd = new Todo(taskName, false);
    }

    @Override
    public CommandResult execute() {
        dataStorage.addTask(this.toAdd);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
