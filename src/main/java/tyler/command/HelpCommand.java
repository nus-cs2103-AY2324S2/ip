package tyler.command;

import tyler.storage.Storage;
import tyler.task.TaskList;
import tyler.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {

    }

    /**
     * Execute this command can let user use the in-apps guidance to more familiar
     * with the command and the command format.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.help();
    }
}
