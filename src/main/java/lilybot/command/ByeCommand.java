package lilybot.command;

import java.io.IOException;

import lilybot.gui.Ui;
import lilybot.storage.Storage;
import lilybot.task.TaskList;

/**
 * Command for close the app and save the tasks.
 */
public class ByeCommand {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs ByeCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param taskList For tracking the list of tasks.
     */
    public ByeCommand(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Says bye to the user.
     *
     * @param command Command entered by users.
     * @return The messages to be displayed after execution.
     */

    public String exceute(String command) throws IOException {
        //Update the file
        Storage.saveFile(storage.getFile(), taskList);
        return ui.sayBye();
    }
}
