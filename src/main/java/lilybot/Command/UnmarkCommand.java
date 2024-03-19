package lilybot.Command;

import lilybot.Parser.Parser;
import lilybot.Task.Task;
import lilybot.Task.TaskList;
import lilybot.Gui.Ui;

public class UnmarkCommand implements Command {

    private Ui ui;
    private String command;
    private TaskList taskList;

    /**
     * Constructs Unmark with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     */
    public UnmarkCommand(Ui ui, String command, TaskList taskList) {
        this.ui = ui;
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Marks the task as unfinished.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(Ui ui, String command, TaskList taskList) {
        try {
            int taskNum = Parser.parseInt(command);
            assert taskNum > 0 : "Task number should be at least 1.";

            Task task = taskList.get(taskNum - 1);
            task.unmark();
            String taskString = task.toString();

            return ui.markNotDone(taskString);
        } catch (Exception exc) {
            return ui.invalidInputNumber();
        }
    }
}
