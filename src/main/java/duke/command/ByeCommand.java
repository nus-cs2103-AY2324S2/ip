package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ByeCommand extends Command {

    /**
     * Executes the command, saves to storage and display parting message.
     *
     * @param list TaskList to be saved.
     * @param ui To send instructions on how to update the user interface.
     * @param storage To update storage with updated list.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        storage.save(list);
        ui.showBye();
    }
}
