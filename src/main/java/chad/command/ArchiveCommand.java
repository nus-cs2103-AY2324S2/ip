package chad.command;

import java.io.IOException;

import chad.utility.Storage;
import chad.utility.TaskList;
import chad.utility.Ui;

/**
 * Represents a command to archive the current task list into a file.
 */
public class ArchiveCommand extends Command {
    /**
     * Archives the current task list.
     *
     * @param list the task list
     * @param ui Chad UI
     * @param storage Chad Storage
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showArchive();

        try { //archive file
            storage.archiveTaskList(list);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }

        list.clear();

        try { //update current stored file
            storage.storeTaskList(list.getList());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
