package dude.commands;

import dude.exceptions.DudeException;
import dude.exceptions.InvalidFormatException;
import dude.tasks.Task;
import dude.tasks.TaskList;

/**
 * The UnmarkCommand class represents a command to mark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_FORMAT = "unmark <id>";
    private final String input;
    private final TaskList taskList;

    /**
     * Constructor for the UnmarkCommand class. Returns a command object to mark a task as not done in the task
     * list upon execution.
     *
     * @param input    The input string that resulted in the creation of this command.
     * @param tasklist The TaskList object in which the task is to be marked as not done.
     */
    public UnmarkCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "unmark \\d+");

        assert(input != null);
        assert(tasklist != null);
        assert(input.contains("unmark"));

        this.input = input.trim();
        this.taskList = tasklist;
    }

    /**
     * Marks a task as not done in the task list.
     *
     * @return The string message from the execution of the command.
     * @throws DudeException If the command execution fails.
     */
    @Override
    public String execute() throws DudeException {
        boolean inputMatches = input.matches(this.getRegex());

        //throw error if input does not match the format
        if (!inputMatches) {
            throw new InvalidFormatException("unmark", COMMAND_FORMAT);
        }

        int id = Integer.parseInt(this.input.split(" ")[1]);

        //re-throw exception if task does not exist
        try {
            Task task = taskList.getTask(id);
            this.taskList.mark_as_undone(id);

            return "Nice! I've unmarked this task as done:\n"
                    + "\t  " + id + ". " + task.toString();
        } catch (DudeException e) {
            throw new DudeException(e.getMessage());
        }
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.UNMARK;
    }
}
