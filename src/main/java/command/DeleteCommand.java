package command;
import duke.Ui;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task removedTask = tasks.delete(index);
        ui.showDeleteTask(removedTask, tasks.size());
    }
    
}
