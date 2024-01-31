package commands;

import commands.Command;
import ui.Ui;

/**
 * Encapsulates a command that list of tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND = "list";

    @Override
    public void execute() {
        Ui.printAllTask(tasks.getList());
    }

}
