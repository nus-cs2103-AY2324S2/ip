package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

/** Represents a command to view the task list in a schedule form. Implements the Command interface. */
public class ViewScheduleCommand implements Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return " Click on the button!"
            + "\nred - unmarked deadlines"
            + "\ngreen - marked deadlines"
            + "\nblue - events";
    }

}
