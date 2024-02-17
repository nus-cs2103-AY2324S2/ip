package Commands;

import Exceptions.DudeException;
import Exceptions.InvalidDescriptionException;
import Exceptions.InvalidFormatException;
import Exceptions.TaskListFullException;
import Tasks.TaskList;
import Tasks.Todo;
import Utils.CommandTypes;

public class TodoCommand extends Command {
    private final String input;
    private final TaskList taskList;

    public TodoCommand(String input, TaskList tasklist) {
        super("todo <description>", "todo .*");
        this.input = input;
        this.taskList = tasklist;
    }

    public String execute() throws DudeException {
        boolean doesInputMatch = input.matches(this.getRegex());

        if (!doesInputMatch) {
            throw new InvalidFormatException("Invalid format for todo command. Please use this format: " + this.getFormat());
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
