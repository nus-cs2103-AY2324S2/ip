package nihao.action;
import nihao.action.task.Task;
import nihao.handler.PrintHandler;
import nihao.handler.DataHandler;
public class TaskAction extends Action{
    public Task task;

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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TaskAction && ((TaskAction) obj).task.equals(task);
    }
}
