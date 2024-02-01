package action;
import action.task.Task;
import exception.InvalidCommandException;
import handler.DataHandler;
import handler.PrintHandler;

public class UnmarkAction implements Action{
    private int index;
    public UnmarkAction(String index) {
        this.index = Integer.parseInt(index);
    }
    @Override
    public void execute() throws InvalidCommandException {
        DataHandler.instance.unmarkTask(index);
        Task task = DataHandler.instance.getTask(index);
        PrintHandler.instance.printWithDivider("Fine. I've marked this as uncompleted:\n" +
                "   " + task);
    }
}
