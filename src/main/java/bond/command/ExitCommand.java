/**
 * The ExitCommand class is used to encapsulate a exit command, which is
 * executed upon invoking the execute() method.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.command;

import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        ui.closeScanner();
    }
}
