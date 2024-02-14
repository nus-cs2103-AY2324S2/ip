package commands;

import java.io.IOException;

import storage.Storage;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            System.exit(0);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        return "";
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
