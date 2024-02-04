package ben.commands;

import ben.exceptions.BenException;
import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {

        // check if task list is empty
        if (tasks.isEmpty()) {
            throw new BenException("   There are no pending tasks now... Add some tasks here!");
        }

        // check if input is within bounds
        if (tasks.isWithinBounds(index)) {
            throw new BenException("   Please input a valid number between 1 and " + tasks.size());
        }

        tasks.unmarkTask(index);

        ui.showUnmarkedTaskMessage();
        ui.showTask(tasks, index);
    }
}
