package Jerry.command;

import Jerry.TaskList;
import Jerry.Ui;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute() {
        try {
            tasks.mark(taskIndex);
            return ui.showMark(tasks, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            return ui.showMessage("Task index is out of bounds.");
        } catch (Exception e) {
            return ui.showMessage("Task index must be a number");
        }
    }
}
