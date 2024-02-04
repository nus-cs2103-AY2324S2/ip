package Jerry.command;

import Jerry.Task;
import Jerry.TaskList;
import Jerry.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        try {
            Task removedTask = tasks.deleteTask(taskIndex);
            ui.showDelete(tasks, removedTask);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Task index is out of bounds.");
        } catch (Exception e) {
            ui.showError("Task index must be a number");
        }
    }
}
