package dude.commands;

import dude.exceptions.DudeException;
import dude.exceptions.InvalidFormatException;
import dude.tasks.Task;
import dude.tasks.TaskList;

/**
 * The MarkCommand class represents a command to mark a task as done in the TaskList object.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_FORMAT = "mark <id>";
    private final String input;
    private final TaskList taskList;

    /**
     * Constructor for the MarkCommand class. Returns a command object to mark a task as done in the TaskList
     * object upon execution.
     *
     * @param input    The input string that resulted in the creation of this command.
     * @param tasklist The TaskList object in which the task is to be marked as done.
     */
    public MarkCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "mark \\d+");

        assert(input != null);
        assert(tasklist != null);
        assert(input.contains("mark"));

        this.input = input.trim();
        this.taskList = tasklist;
    }

    /**
     * Marks a task as done in the TaskList object.
     *
     * @return The string message from the execution of the command.
     * @throws DudeException If the command execution fails.
     */
    @Override
    public String execute() throws DudeException {
        boolean inputMatches = input.matches(this.getRegex());

        //throw error if input does not match the format
        if (!inputMatches) {
            throw new InvalidFormatException("mark", COMMAND_FORMAT);
        }

        int id = Integer.parseInt(this.input.split(" ")[1]);
        try {
            Task task = this.taskList.getTask(id);
            this.taskList.mark_as_done(id);

            return "Nice! I've marked this task as done:\n"
                    + "\t  " + id + ". " + task.toString();
        } catch (DudeException e) {
            throw new DudeException(e.getMessage());
        }
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.MARK;
    }
}
