package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.exception.AtlasException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        super(tasks, ui, storage);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws AtlasException {
        tasks.unmarkTask(taskIndex);
        ui.showunMark(tasks, taskIndex);
    }
}
