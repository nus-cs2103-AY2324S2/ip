package nihao.action;

import nihao.action.task.Task;
import nihao.exception.IndexOutOfBoundsException;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

public class DeleteAction implements Action{
    private int index;
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeleteAction && ((DeleteAction) obj).index == this.index;
    }
}
