package action;
import action.task.Task;
import handler.PrintHandler;
import handler.DataHandler;
public class TaskAction implements Action{
    private Task task;

    public TaskAction(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        DataHandler.handleData(task);
        int noOfTasks = DataHandler.size();
        PrintHandler.printWithDivider("Ok.. I've added this task to your list:\n" +
                "   " + task.toString() + "\n" +
                "Now you have " + noOfTasks + " tasks.");
    }
}
