package Jerry.command;

import Jerry.Task;
import Jerry.TaskList;
import Jerry.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute() {
        try {
            Task removedTask = tasks.deleteTask(taskIndex);
            return ui.showDelete(tasks, removedTask);
        } catch (IndexOutOfBoundsException e) {
            return ui.showMessage("Task index is out of bounds.");
        } catch (Exception e) {
            return ui.showMessage("Task index must be a number");
        }
    }
}
