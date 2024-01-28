package action;
import java.io.IOException;

import util.PrintUtil;
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
     * Executes the unmark action by printing a message and unmarking the task in the task list.
     *
     * @throws IOException if there is an error executing the action
     */
    @Override
    public void execute() throws IOException {
        PrintUtil.print("You know, sometimes things don't go as planned, but that's okay! "
                + "\nThe important part is to keep moving forward. "
                + "\nUnmarking a task is just a step in the journey. Believe it! "
                + "\nWe'll get there, one task at a time! ᕙ(⇀‸↼‶)ᕗ");
        PrintUtil.print(this.taskList.unmark(i));
    }
}
