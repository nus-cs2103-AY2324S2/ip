package dude.commands;

import dude.exceptions.DudeException;
import dude.exceptions.InvalidFormatException;
import dude.tasks.TaskList;
import dude.tasks.Todo;

/**
 * The TodoCommand class represents a command to add a todo task to the TaskList object.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_FORMAT = "todo <description>";
    private final String input;
    private final TaskList taskList;

    /**
     * Constructor for the TodoCommand class. Returns a command object to add a todo task to the TaskList object
     * upon execution.
     *
     * @param input    The input string that resulted in the creation of this command.
     * @param tasklist The TaskList object to which the todo task is to be added.
     */
    public TodoCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "todo .*");

        assert(input != null);
        assert(tasklist != null);
        assert(input.contains("todo"));

        this.input = input;
        this.taskList = tasklist;
    }

    /**
     * Add a todo task to the TaskList object.
     *
     * @return The string message from the execution of the command.
     * @throws DudeException If the command execution fails.
     */
    public String execute() throws DudeException {
        boolean doesInputMatch = input.matches(this.getRegex());

        if (!doesInputMatch) {
            throw new InvalidFormatException("todo", COMMAND_FORMAT);
        }

        Todo todo = Todo.from(input); //throws InvalidDescriptionException
        this.taskList.add_task(todo); //throws TaskListFullException
        return "Got it. I've added this task:\n"
                + "\t  " + todo.toString() + "\n"
                + "\tNow you have " + this.taskList.getSize() + " tasks in the list.";
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.TODO;
    }
}
