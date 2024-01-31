import Task.Task;
import Task.TaskList;

public class AddCommand extends Command {

    private final Task task;

    AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        tasks.addTask(task);
        ui.println("Oki! I've added this task:");
        ui.println(task.toString());
        tasks.printStatus();
        storage.write(tasks);
    }
}