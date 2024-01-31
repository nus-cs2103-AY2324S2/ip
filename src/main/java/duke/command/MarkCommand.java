package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException {
        tasks.markAsDone(taskNumber);
        ui.showMarked(tasks.getTask(taskNumber));
    }
}
