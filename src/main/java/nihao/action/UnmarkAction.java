package nihao.action;
import nihao.action.task.Task;
import nihao.exception.IndexOutOfBoundsException;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

/**
 * Represents an unmark action.
 */
public class UnmarkAction implements Action {
    private int index;

    /**
     * Constructor that specifies the index of the Task to be mark as uncompleted.
     */
    public UnmarkAction(String index) {
        this.index = Integer.parseInt(index);
    }

    /**
     * Marks the Task at the given index as uncompleted.
     *
     * @throws IndexOutOfBoundsException When the index provided is more than the length of tasks.
     */
    @Override
    public void execute() throws IndexOutOfBoundsException {
        DataHandler.unmarkTask(index);
        Task task = DataHandler.getTask(index);
        PrintHandler.printWithDivider("Fine. I've marked this as uncompleted:\n"
                + "   " + task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnmarkAction && ((UnmarkAction) obj).index == this.index;
    }
}
