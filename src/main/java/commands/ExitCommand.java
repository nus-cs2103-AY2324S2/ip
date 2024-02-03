package commands;

import excceptions.WeiException;
import taskList.TaskList;
import ui.Ui;

/**
 * Indicates the end of the conversation.
 */
public class ExitCommand extends Command {
    /**
     * Does nothing.
     *
     * @param tasks All the tasks of the user.
     * @param ui Gives reply to the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
    }

    /**
     * {@inheritDoc}
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }


}
