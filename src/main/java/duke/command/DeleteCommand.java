package duke.command;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int zeroItem;

    public DeleteCommand(int oneItem) {
        this.zeroItem = oneItem - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = zeroItem + 1;
        if (taskNumber < 1 || taskNumber > tasks.getSize() || tasks.get(taskNumber - 1) == null) {
            throw new DukeException("Error! duke.task.Task number '" + taskNumber + "' does not exist.");
        }
        Task description = tasks.get(zeroItem);
        tasks.deleteTask(zeroItem);
        ui.deleteResponse(description, tasks);
        storage.saveList(tasks.getTasks());
    }
}
