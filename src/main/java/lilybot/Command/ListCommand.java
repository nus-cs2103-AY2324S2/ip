package lilybot.Command;

import lilybot.Task.TaskList;
import lilybot.Gui.Ui;

public class ListCommand implements Command{

    private Ui ui;
    private String command;
    private TaskList taskList;

    /**
     * Constructs ListCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     */
    public ListCommand(Ui ui, String command, TaskList taskList) {
        this.ui = ui;
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Lists the tasks.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(Ui ui, String command, TaskList taskList) {
        return ui.listTask(taskList);
    }
}
