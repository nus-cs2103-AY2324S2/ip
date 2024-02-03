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
    public TaskList execute() {
        return tasks;
    }

    @Override
    public String getReply(Ui ui) {
        return ui.goodBye();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
