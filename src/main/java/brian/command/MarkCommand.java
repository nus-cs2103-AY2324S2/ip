package brian.command;

import brian.storage.Storage;
import brian.task.TaskList;
import brian.ui.TextUi;

public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert ui != null;
        assert tasks != null;
        tasks.markAsDone(taskNumber);
        ui.showMarked(tasks.getTask(taskNumber));
    }
}
