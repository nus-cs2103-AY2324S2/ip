package sylvia.command;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.Task;
import sylvia.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command implements Undoable {
    public static final String MANUAL = "Usage: delete <task number>\n\n" + "Deletes a task from the task list.\n"
            + "The task number must be specified as an integer.\n\n" + "Example: delete 3\n\n" + "Aliases: d, del";
    private int index;
    private Task task;

    /**
     * Creates a new delete command.
     *
     * @param body The body of the command.
     * @throws SylviaException If the body is invalid.
     */
    public DeleteCommand(String body) throws SylviaException {
        super(body);
        try {
            this.index = Integer.parseInt(body);
        } catch (NumberFormatException e) {
            if (body == null || body.equals("")) {
                throw new EmptyTaskDescriptionException("The index of a task cannot be empty.",
                        "Sorry, but I don't understand what you mean by task number \"" + body + "\".");
            }
            throw new InvalidTaskIndexException("The index of a task must be an integer.",
                    "Sorry, but I don't understand what you mean by task number \"" + body + "\".");
        }
    }

    /**
     * Gets the manual for the delete command.
     *
     * @return The manual for the delete command.
     */
    public static String getManual() {
        return MANUAL;
    }

    /**
     * Executes the command. This command deletes a task from the task list. The
     * program state is set to normal after the command is executed, even if the
     * command fails to execute.
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
        Task task = list.deleteTask(index);
        this.task = task;
        String response = ("Deleted: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        state.setNormal();
        state.addCommandToHistory(this);
        return response;
    }

    @Override
    public String undo(TaskList list, ProgramState state) throws SylviaException {
        list.addTask(this.task);
        state.setNormal();
        return "Task re-added: " + task + "\nYou now have " + list.size() + " tasks in the list.";
    }

    @Override
    public String redo(TaskList list, ProgramState state) throws SylviaException {
        return this.execute(list, state);
    }
}
