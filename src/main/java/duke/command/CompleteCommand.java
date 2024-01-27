package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class CompleteCommand extends Command {
    private final int index;
    private final boolean isComplete;

    public CompleteCommand(int index, boolean isComplete) {
        this.index = index;
        this.isComplete = isComplete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            if (isComplete) {
                task.setComplete();
            } else {
                task.setIncomplete();
            }
            ui.showNote("Set task to " + task.status() + ":\n  " + task + "\n");
        } catch (TaskList.TaskNotFound e) {
            ui.showError(e);
        }
    }
}
