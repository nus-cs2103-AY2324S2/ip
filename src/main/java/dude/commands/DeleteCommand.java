package dude.commands;

import dude.exceptions.DudeException;
import dude.exceptions.InvalidFormatException;
import dude.tasks.Task;
import dude.tasks.TaskList;

/**
 * The DeleteCommand class represents a command to delete a task from the TaskList object.
 */
public class DeleteCommand extends Command {

    private static final String COMMAND_FORMAT = "delete <id>";
    private final String input;
    private final TaskList taskList;


    /**
     * Constructor for the DeleteCommand class. Returns a command object to delete a task from the TaskList object upon
     * execution.
     *
     * @param input    The input string that resulted in the creation of this command.
     * @param tasklist The TaskList object from which the task is to be deleted.
     */
    public DeleteCommand(String input, TaskList tasklist) {
        super(COMMAND_FORMAT, "delete \\d+");

        assert(input != null);
        assert(tasklist != null);
        assert(input.contains("delete"));

        this.input = input.trim();
        this.taskList = tasklist;
    }

    /**
     * Deletes a task from the TaskList object.
     *
     * @return The string message from the execution of the command.
     * @throws DudeException If the command execution fails.
     */
    public String execute() throws DudeException {
        boolean inputMatches = input.matches(this.getRegex());

        //throw error if input does not match the format
        if (!inputMatches) {
            throw new InvalidFormatException("delete", COMMAND_FORMAT);
        }

        int id = Integer.parseInt(this.input.split(" ")[1]);

        //re-throw exception if task does not exist
        try {
            Task task = this.taskList.getTask(id);

            this.taskList.remove_task(id);

            String msg = "Noted. I've removed this task:\n"
                    + "\t  " + id + ". " + task.toString() + "\n"
                    + "\tNow you have " + this.taskList.getSize() + " tasks in the list.";

            return msg;
        } catch (DudeException e) {
            throw new DudeException(e.getMessage());
        }
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.DELETE;
    }
}
