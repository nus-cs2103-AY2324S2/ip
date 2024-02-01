package action;
import action.task.Task;
import handler.PrintHandler;
import handler.DataHandler;
public class TaskAction implements Action{
    private Task task;
    public TaskAction(String text) {
        this.task = new Task(text);
    }

    public TaskAction(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        DataHandler.instance.handleData(task);
        int noOfTasks = DataHandler.instance.length();
        PrintHandler.instance.printWithDivider("Ok.. I've added this task to your list:\n" +
                "   " + task.toString() + "\n" +
                "Now you have " + noOfTasks + " tasks.");
    }
}
