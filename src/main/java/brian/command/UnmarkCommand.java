package brian.command;

import brian.storage.Storage;
import brian.task.TaskList;
import brian.ui.TextUi;

public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert ui != null;
        assert tasks != null;

        tasks.unmarkAsDone(taskNumber);
        ui.showUnmarked(tasks.getTask(taskNumber));
    }
}
