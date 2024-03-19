package lilybot.Command;

import lilybot.Storage.Storage;
import lilybot.Task.TaskList;
import lilybot.Gui.Ui;

import java.io.IOException;

public class ByeCommand {

    private Ui ui;
    private String command;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs ByeCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     */
    public ByeCommand(Storage storage, Ui ui, String command, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Says bye to the user.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     * @return The messages to be displayed after execution.
     */

    public String exceute(Storage storage, Ui ui, String command, TaskList taskList) throws IOException {
        //Update the file
        Storage.saveFile(storage.getFile(), taskList);
        return ui.sayBye();
    }
}
