package nihao.action;

import nihao.action.task.Task;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

/**
 * Represents an action to add a Task to the tasks ArrayList.
 */
public class TaskAction implements Action {
    private Task task;

    /**
     * Constructor the specifies the Task to be added.
     */
    public TaskAction(Task task) {
        this.task = task;
    }

    /**
     * Adds the Task to the tasks ArrayList.
     */
    @Override
    public String execute() {
        DataHandler.addTask(task);
        int noOfTasks = DataHandler.size();
        return PrintHandler.printWithDivider("Ok.. I've added this task to your list:\n"
                + "   " + task.toString() + "\n"
                + "Now you have " + noOfTasks + " tasks.");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TaskAction && ((TaskAction) obj).task.equals(task);
    }
}
