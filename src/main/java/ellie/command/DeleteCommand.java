package ellie.command;

import ellie.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to be deleted.
     */
    protected int index;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list.
     *
     * @param tasklist The TaskList to be operated on by the command.
     * @return A response message indicating the result of the operation.
     * @inheritDoc
     */
    public String runAndReturnResponse(TaskList tasklist) {
        String response = tasklist.deleteTaskIndex(index);
        return response;
    }



}
