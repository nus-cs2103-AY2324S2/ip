package dude.commands;

import dude.exceptions.DudeException;
import dude.exceptions.InvalidFormatException;
import dude.tasks.Deadline;
import dude.tasks.TaskList;

/**
 * The DeadlineCommand class represents a command to add a deadline task to the TaskList object.
 */
public class DeadlineCommand extends Command {

    static final String COMMAND_FORMAT = "deadline <description> /by <date>";
    private final String input;
    private final TaskList taskList;

    /**
     * Constructor for the DeadlineCommand class. Returns a command object to add a deadline task to
     * the TaskList object upon execution.
     *
     *
     * @param input    The input string that resulted in the creation of this command.
     * @param tasklist The TaskList object to which the deadline task is to be added.
     */
    public DeadlineCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "deadline .* /by .*");

        assert(input != null);
        assert(tasklist != null);
        assert(input.contains("deadline"));

        this.input = input;
        this.taskList = tasklist;
    }

    /**
     * Add a deadline task to the TaskList object.
     *
     * @return The string message from the execution of the command.
     * @throws DudeException If the command execution fails.
     */
    public String execute() throws DudeException {
        boolean doesInputMatch = input.matches(this.getRegex());

        //only catches basic format errors. date format errors are caught by Deadline.from()
        if (!doesInputMatch) {
            throw new InvalidFormatException("deadline", COMMAND_FORMAT);
        }

        Deadline deadline = Deadline.from(input); //throws different DukeExceptions
        this.taskList.add_task(deadline); //throws TaskListFullException
        return "Got it. I've added this task:\n"
                + "\t  " + deadline.toString() + "\n"
                + "\tNow you have " + this.taskList.getSize() + " tasks in the list.";
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.DEADLINE;
    }

}
