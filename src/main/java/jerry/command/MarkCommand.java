package jerry.command;

import jerry.TaskList;
import jerry.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
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
