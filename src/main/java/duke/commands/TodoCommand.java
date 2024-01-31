package duke.commands;

import duke.tasklist.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task. \n"
            + "Example: " + COMMAND_WORD
            + " boil coconut milk";

    public static final String MESSAGE_SUCCESS = "\t Got it. I've added this task:\n" +
            "\t %s\n" +
            "\t Now you have %d task(s) available!";

    public static final String MESSAGE_BLANK_EVENT = "\t The event name cannot be empty!";

    private final Todo toAdd;

    public TodoCommand(String taskName) {
        this.toAdd = new Todo(taskName, false);
    }

    @Override
    public CommandResult execute() {
        dataStorage.addTask(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString(),
                dataStorage.getTaskCount()));
    }
}
