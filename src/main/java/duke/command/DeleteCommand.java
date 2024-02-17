package duke.command;

import duke.DukeException;
import duke.state.ProgramState;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a new delete command.
     *
     * @param body The body of the command.
     * @throws DukeException If the body is invalid.
     */
    public DeleteCommand(String body) throws DukeException {
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
     * Executes the command. This command deletes a task from the task list. The
     * program state is set to normal after the command is executed, even if the
     * command fails to execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws DukeException If the index provided falls outside the range of the
     *                       task list, or if the command fails to execute.
     */
    public String execute(TaskList list, ProgramState state) throws DukeException {
        if (index < 1 || index > list.size()) {
            throw new InvalidTaskIndexException(
                    "The index of a task cannot be less than 1 or greater than the number of tasks.",
                    "Sorry, but task number " + index + " does not exist. You only have " + list.size() + " tasks.");
        }
        Task task = list.deleteTask(index);
        String response = ("Deleted: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        state.setNormal();
        return response;
    }
}
