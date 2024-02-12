package tyler.command;

import tyler.task.TaskList;
import tyler.ui.Ui;
import tyler.storage.Storage;

/**
 * Represent the Exit Command. This command can ensure to exit the Tyler.
 */
public class ExitCommand extends Command {

    /**
     * Exit command is Exit.
     *
     * @return True
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Execution of Exit Command store the taskList locally, so that Tyler is opened
     * next time, the taskList will appear again. At last, showing the line that
     * Tyler is exiting and end Tyler.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTask(tasks);
        ui.showBye();
    }
}
