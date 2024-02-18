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
    public MarkAction(String index) {
        this.index = Integer.parseInt(index);
    }

    /**
     * Marks the Task at the given index as completed.
     *
     * @throws IndexOutOfBoundsException When the index provided is more than the length of tasks.
     */
    @Override
    public void execute() throws IndexOutOfBoundsException {
        DataHandler.markTask(index);
        Task task = DataHandler.getTask(index);
        PrintHandler.printWithDivider("Fine. I've marked this as completed:\n"
                + "   " + task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MarkAction && ((MarkAction) obj).index == this.index;
    }
}
