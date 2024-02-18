package sylvia.command;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    public static final String MANUAL = "Usage: mark <index>\n" + "Marks a task as done in the task list.\n"
            + "The index must be a positive integer.\n" + "Example: mark 3\n" + "Aliases: mk";
    private int index;

    /**
     * Gets the manual for the mark command.
     *
     * @return The manual for the mark command.
     */
    public static String getManual() {
        return MANUAL;
    }

    /**
     * Creates a new mark command.
     *
     * @param body The body of the command.
     * @throws SylviaException If the body cannot be parsed as an integer.
     */
    public MarkCommand(String body) throws SylviaException {
        super(body);
        try {
            this.index = Integer.parseInt(body);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("The index of a task must be an integer.",
                    "Sorry, but I don't know which task you want to mark as done.");
        }
    }

    /**
     * Executes the command. This command marks a task as done. The program state is
     * set to normal after the command is executed, even if the command fails to
     * execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws SylviaException If the index provided falls outside the range of the
     *                         task list, or if the command fails to execute.
     */
    public String execute(TaskList list, ProgramState state) throws SylviaException {
        if (index < 1 || index > list.size()) {
            throw new InvalidTaskIndexException(
                    "The index of a task cannot be less than 1 or greater than the number of tasks.",
                    "Sorry, but task number " + index + " does not exist. You only have " + list.size() + " tasks.");
        }
        list.markTaskAsDone(index);
        state.setNormal();
        String response = ("Done: " + list.get(index - 1));
        return response;
    }
}
