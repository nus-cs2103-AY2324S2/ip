import Task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.println("Here are your tasks!");
        ui.println(tasks.toString());
    }
}
