package action;

import java.io.IOException;

import util.TaskList;

/**
 * Represents an action to unmark a task in the task list.
 */
public class Unmark implements Action {
    private final TaskList taskList;
    private int i;

    /**
     * Constructs an Unmark object with the specified task list and task index.
     *
     * @param taskList the task list
     * @param i        the index of the task to unmark
     */
    public Unmark(TaskList taskList, int i) {
        this.taskList = taskList;
        this.i = i;
    }

    /**
     * Executes the unmark action by constructing and returning a message and unmarking the task in the task list.
     *
     * @return the constructed message
     * @throws IOException if there is an error executing the action
     */
    @Override
    public String execute() throws IOException {
        assert taskList != null : "Task list cannot be null";
        assert i >= 0 && i < taskList.size() : "Invalid task index";

        StringBuilder message = new StringBuilder();
        message.append("You know, sometimes things don't go as planned, but that's okay! ")
                .append("\nWe'll get there, one task at a time!\n")
                .append(this.taskList.unmark(i));
        return message.toString();
    }
}
