package commands;

import ui.Ui;

/**
 * Encapsulates a list command.
 */
public class ListCommand extends Command {

    public static final String COMMAND = "list";

    /**
     * Prints out the list of tasks in ListTask.
     */
    @Override
    public void execute() {
        Ui.printAllTask(tasks.getList());
    }

}
