package duke.action;

import duke.task.Task;

import duke.exception.WrongIndexException;

/**
 * Represents an action to delete a task from the task list.
 */
public class Mark implements Action {

    /**
     * The task that has been deleted.
     */
    private int index;
    private TaskList tasks;


    public Mark(int index, TaskList tasks) throws WrongIndexException {
        if (!(tasks.validateIndex(index))) {
            throw new WrongIndexException();
        }
        this.index = index;
        tasks.markTask(index);
        this.tasks = tasks;
    }

    /**
     * Gets the response message indicating the marking of the task.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        Task markedTask = tasks.get(index);
        return "Nice! I've marked this task as done\n" + markedTask.toString();
    }
}