package lilybot.command;

import lilybot.gui.Ui;
import lilybot.task.TaskList;

/**
 * Command for listing all tasks.
 *
 */
public class ListCommand implements Command {

    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs ListCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param taskList For tracking the list of tasks.
     */
    public ListCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Lists the tasks.
     *
     * @param command Command entered by users.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(String command) {
        return ui.listTask(taskList);
    }
}
