package action;
import action.task.Task;
import exception.IndexOutOfBoundsException;
import handler.PrintHandler;
import handler.DataHandler;
public class MarkAction implements Action{
    private final int index;
    public MarkAction(String index) {
        this.index = Integer.parseInt(index);
    }
    @Override
    public void execute() throws IndexOutOfBoundsException {
        DataHandler.markTask(index);
        Task task = DataHandler.getTask(index);
        PrintHandler.printWithDivider("Fine. I've marked this as completed:\n" +
                "   " + task);
    }
}
