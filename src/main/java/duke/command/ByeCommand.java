package duke.command;

import duke.common.TaskList;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_USAGE = "bye: it would quit the chatbot and saves your tasks.\n" +
            "Example: bye";

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
