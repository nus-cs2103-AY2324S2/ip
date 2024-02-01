package action;
import action.task.Task;
import exception.InvalidCommandException;
import handler.PrintHandler;
import handler.DataHandler;
public class MarkAction implements Action{
    private final int index;
    public MarkAction(String index) {
        this.index = Integer.parseInt(index);
    }
    @Override
    public void execute() throws InvalidCommandException {
        DataHandler.instance.markTask(index);
        Task task = DataHandler.instance.getTask(index);
        PrintHandler.instance.printWithDivider("Fine. I've marked this as completed:\n" +
                "   " + task);
    }
}
