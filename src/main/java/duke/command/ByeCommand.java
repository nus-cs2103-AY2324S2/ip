package duke.command;

import duke.common.TaskList;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a bye command initiated by the user. <code>ByeCommand</code> would indicate the command is a command
 * to exit the program and save the tasks
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_USAGE = "bye: it would quit the chatbot and saves your tasks.\n"
            + "Example: bye";

    /**
     * Sets the variable representing whether the command wants to exit to true and save all tasks to the file
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            super.isExit = true;
            storage.save(taskList);
        } catch (StorageOperationException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showGoodBye();
        }
    }
}
