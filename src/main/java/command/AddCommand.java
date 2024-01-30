package command;
import utilities.Ui;
import utilities.Storage;
import task.TaskList;
import task.Task;

public class AddCommand extends Command{
    private Task taskToAdd;
    public AddCommand(Task newTask) {
        super(false);
        this.taskToAdd = newTask;
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(this.taskToAdd);
        storage.save(taskList);
        ui.showTaskListLength(taskList);
    }
}
