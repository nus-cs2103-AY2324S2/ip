package action;

import action.task.Task;
import exception.IndexOutOfBoundsException;
import handler.DataHandler;
import handler.PrintHandler;

public class DeleteAction implements Action{
    private final int index;
    private boolean isAll;
    public DeleteAction(String index) {
        if (index.equals("all")) {
            this.index = -1;
            isAll = true;
        } else {
            this.index = Integer.parseInt(index);
            isAll = false;
        }
    }

    @Override
    public void execute() throws IndexOutOfBoundsException {
        if (isAll) {
            DataHandler.deleteAll();
            PrintHandler.printWithDivider("Deleted everything for you. It was tiring :<");
        } else {
            Task task = DataHandler.getTask(index);
            DataHandler.deleteTask(index);
            int noOfTasks = DataHandler.size();
            PrintHandler.printWithDivider("Fine. I've removed this task:\n"
                    + "   " + task + "\n"
                    + "Now you have " + noOfTasks + " tasks.");
        }
    }
}
