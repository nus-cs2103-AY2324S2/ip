package Command;
import TaskList.TaskList;
import UiRelated.Ui;
import Tasks.Task;
public class addToListCommand extends Command {
    Task task;
    public addToListCommand(Task t){
        task = t;
    }
    @Override
    public void execute(TaskList taskList, Ui ui){
        taskList.addToList(task);
        ui.display("HASSNT:\n" + "Got it. I've added this task:  " + task);
        ui.display("\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
