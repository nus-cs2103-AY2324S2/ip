package charlie.commands;

import charlie.storage.Storage;
import charlie.storage.TaskList;
import charlie.ui.Ui;

public class ExitCommand extends Command {

    /**
     * turns isExit to true, which effectively terminates the program
     * @param taskList - task list loaded at the start of the program.
     * @param ui       - class responsible for user interface interactions
     * @param storage  - class responsible for adding and loading tasks from and into the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        isExit = true;
    }
}
