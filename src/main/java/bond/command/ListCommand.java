package command;

import bond.BondException;
import bond.Storage;
import bond.Ui;
import task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        ui.showList(tasks);
    }

}
