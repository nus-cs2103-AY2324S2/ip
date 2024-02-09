package action;

import java.io.IOException;

import util.TaskList;

/**
 * Represents an action to mark a task as completed.
 * Implements the Action interface.
 */
public class Mark implements Action {
    private final TaskList taskList;
    private final int idx;

    /**
     * Constructs a Mark object with the specified task list and index.
     *
     * @param taskList the task list to mark the task in
     * @param i        the index of the task to mark
     */
    public Mark(TaskList taskList, int i) {
        this.taskList = taskList;
        this.idx = i;
    }

    /**
     * Executes the action by marking the task as completed and printing a message.
     *
     * @return the constructed output message
     * @throws IOException if there is an error in printing the message
     */
    @Override
    public String execute() throws IOException {
        StringBuilder output = new StringBuilder();
        output.append("Great job! You marked that task off your list! Believe it! ᕕ( ᐛ )ᕗ\n")
                .append("\n").append(this.taskList.mark(idx));
        return output.toString();
    }
}
