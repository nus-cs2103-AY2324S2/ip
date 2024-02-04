/**
 * The ListCommand class is used to encapsulate a list command, which is
 * executed upon invoking the execute() method.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        ui.showList(tasks);
    }

}
