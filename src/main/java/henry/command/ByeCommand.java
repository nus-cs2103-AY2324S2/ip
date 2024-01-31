package henry.command;

import henry.Storage;
import henry.TaskList;
import henry.Ui;
import henry.HenryException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        System.out.println("See you again bro!");
    }

    public boolean isExit() {
        return true;
    }
}
