package action;
import java.io.IOException;

import util.PrintUtil;
import util.TaskList;

/**
 * Represents an action to mark a task as completed.
 * Implements the Action interface.
 */
public class Mark implements Action {
    private final TaskList taskList;
    private final int i;

    /**
     * Constructs a Mark object with the specified task list and index.
     *
     * @param taskList the task list to mark the task in
     * @param i        the index of the task to mark
     */
    public Mark(TaskList taskList, int i) {
        this.taskList = taskList;
        this.i = i;
    }

    /**
     * Executes the action by marking the task as completed and printing a message.
     *
     * @throws IOException if there is an error in printing the message
     */
    @Override
    public void execute() throws IOException {
        PrintUtil.print("Great job! You marked that task off your list! That's the way to go! "
                + "\nKeep pushing yourself, and remember, every completed task brings you "
                + "\none step closer to your goals. Believe it! ᕕ( ᐛ )ᕗ");
        PrintUtil.print(this.taskList.mark(i));
    }
}
