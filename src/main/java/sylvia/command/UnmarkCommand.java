package sylvia.command;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.TaskList;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    public static final String MANUAL = "Usage: unmark <index>\n\n"
            + "Marks the task at the specified index as not done.\n\n" + "Aliases: um";
    private int index;

    /**
     * Creates a new command to mark a task as not done.
     *
     * @param body The body of the command.
     * @throws SylviaException If the body cannot be parsed as an integer.
     */
    public UnmarkCommand(String body) throws SylviaException {
        super(body);
        try {
            this.index = Integer.parseInt(body);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("The index of a task must be an integer.",
                    "Sorry, but I don't know which task you want to mark as undone.");
        }
    }

    /**
     * Gets the manual for the unmark command.
     *
     * @return The manual for the unmark command.
     */
    public static String getManual() {
        return MANUAL;
    }

    /**
     * Executes the command. This command marks a task as not done. The program
     * state is set to normal after the command is executed, even if the command
     * fails to execute.
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
        list.unmarkTaskAsDone(index);
        String response = ("Undone: " + list.get(index - 1));
        state.setNormal();
        return response;
    }
}
