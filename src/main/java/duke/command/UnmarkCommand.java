package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;

public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        tasks.unmarkAsDone(taskNumber);
        ui.showUnmarked(tasks.getTask(taskNumber));
    }
}
