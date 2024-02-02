import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int index;


    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new ZackException("Task index is out of range. Please enter a number between 1 and " + tasks.getSize() + ".");
        }
        Task removedTask = tasks.deleteTask(index);
        ui.showDeletedTask(removedTask, tasks.getSize());
        storage.save(tasks.getAllTasks());
    }

}


