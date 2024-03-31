package nihao.action;

import nihao.action.task.Task;
import nihao.exception.IndexOutOfBoundsException;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

/**
 * Represents the delete action.
 */
public class DeleteAction implements Action {
    private int index;
    private boolean isAll;

    /**
     * Constructor that specifies the index of the Task to be deleted.
     */
    public DeleteAction(String index) {
        if (index.equals("all")) {
            this.index = -1;
            isAll = true;
        } else {
            this.index = Integer.parseInt(index);
            isAll = false;
        }
    }

    /**
     * Deletes the Task at the given index and prints a notification.
     *
     * @throws IndexOutOfBoundsException When the index provided is more than the length of tasks.
     */
    @Override
    public String execute() throws IndexOutOfBoundsException {
        String message;
        if (isAll) {
            DataHandler.deleteAll();
            message = "Deleted everything for you. It was tiring :<";
        } else {
            Task task = DataHandler.getTask(index);
            DataHandler.deleteTask(index);
            int noOfTasks = DataHandler.size();
            message = "Fine. I've removed this task:\n"
                    + "   " + task + "\n"
                    + "Now you have " + noOfTasks + " tasks.";
        }
        return PrintHandler.printWithDivider(message);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeleteAction && ((DeleteAction) obj).index == this.index;
    }
}
