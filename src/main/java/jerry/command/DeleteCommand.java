package jerry.command;

import jerry.Task;
import jerry.TaskList;
import jerry.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
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
