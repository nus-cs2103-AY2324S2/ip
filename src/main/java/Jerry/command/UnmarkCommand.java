package Jerry.command;

import Jerry.TaskList;
import Jerry.Ui;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        try {
            tasks.unmark(taskIndex);
            ui.showUnmark(tasks, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Task index is out of bounds.");
        } catch (Exception e) {
            ui.showError("Task index must be a number");
        }
    }
}
