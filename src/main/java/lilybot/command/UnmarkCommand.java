package lilybot.command;

import lilybot.gui.Ui;
import lilybot.parser.Parser;
import lilybot.task.Task;
import lilybot.task.TaskList;

/**
 * Command for unmarking a task.
 */
public class UnmarkCommand implements Command {

    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs Unmark with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param taskList For tracking the list of tasks.
     */
    public UnmarkCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Marks the task as unfinished.
     *
     * @param command Command entered by users.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(String command) {
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
