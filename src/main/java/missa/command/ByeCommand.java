package missa.command;

import missa.TaskList;
import missa.Ui;

/**
 * Represents Bye commands.
 */
public class ByeCommand extends Command {
    private TaskList tasks;

    public ByeCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public TaskList execute(Ui ui) {
        return tasks;
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
