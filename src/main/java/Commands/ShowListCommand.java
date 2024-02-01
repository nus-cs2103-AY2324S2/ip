package Commands;

import TaskLists.TaskList;
import UiRelated.Ui;

/**
 * The showListCommand class represents a command to display the list of tasks.
 */
public class ShowListCommand extends Command {
    /**
     * Executes the showListCommand by displaying the list of tasks in the Ui.
     *
     * @param taskList The TaskList object containing the tasks.
     * @param ui       The Ui object for displaying messages.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String s = taskList.showLists();
        if (s.isEmpty()) {
            ui.display("HASSNT:\n" + "NO TASKS IN TO DO LIST 🎉");
        } else {
            ui.display(s);
        }
    }
}
