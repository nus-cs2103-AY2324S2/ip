package nihao.action;
import nihao.action.task.Task;
import nihao.exception.IndexOutOfBoundsException;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

/**
 * Represents a mark action.
 */
public class MarkAction implements Action {
    private int index;

    /**
     * Constructor that specifies the index of the task to be marked as completed.
     *
     * @param index The index of the task to be marked as completed.
     */
    public MarkAction(String index) {
        this.index = Integer.parseInt(index);
    }

    /**
     * Marks the Task at the given index as completed.
     *
     * @throws IndexOutOfBoundsException When the index provided is more than the length of tasks.
     */
    @Override
    public String execute() throws IndexOutOfBoundsException {
        assert index > 0 : "index should be more than 0";
        assert index <= DataHandler.getData().size() : "index should be less than or equal to the size of tasks";
        DataHandler.markTask(index);
        Task task = DataHandler.getTask(index);
        return PrintHandler.printWithDivider("Fine. I've marked this as completed:\n"
                + "   " + task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MarkAction && ((MarkAction) obj).index == this.index;
    }
}
