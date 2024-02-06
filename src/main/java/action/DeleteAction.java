package action;

import action.task.Task;
import exception.IndexOutOfBoundsException;
import handler.DataHandler;
import handler.PrintHandler;

public class DeleteAction implements Action{
    private final int index;
    public DeleteAction(String index) {
        this.index = Integer.parseInt(index);
    }
    @Override
    public void execute() throws IndexOutOfBoundsException {
        Task task = DataHandler.getTask(index);
        DataHandler.deleteTask(index);
        int noOfTasks = DataHandler.size();
        PrintHandler.printWithDivider("Fine. I've removed this task:\n"
                + "   " + task + "\n"
                + "Now you have " + noOfTasks + " tasks.");
    }
}
