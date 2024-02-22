package commands;

import ui.Ui;

/**
 * Encapsulates a list command.
 */
public class ListCommand extends Command {

    public static final String COMMAND = "list";

    /**
     * Prints out the list of tasks in ListTask.
     *
     * @return String representation of the full task list.
     */
    @Override
    public String executeCommand() {
        return  Ui.printAllTask(taskList.getList());
    }

}
