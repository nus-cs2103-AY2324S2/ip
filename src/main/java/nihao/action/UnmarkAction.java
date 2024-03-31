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
    public String execute() throws IndexOutOfBoundsException {
        assert index > 0 : "index should be more than 0";
        assert index <= DataHandler.getData().size() : "index should be less than or equal to the size of tasks";
        DataHandler.unmarkTask(index);
        Task task = DataHandler.getTask(index);
        return PrintHandler.printWithDivider("Fine. I've marked this as uncompleted:\n"
                + "   " + task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnmarkAction && ((UnmarkAction) obj).index == this.index;
    }
}
