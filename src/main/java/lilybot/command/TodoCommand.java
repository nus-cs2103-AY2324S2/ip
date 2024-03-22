package lilybot.command;

import lilybot.gui.Ui;
import lilybot.parser.Parser;
import lilybot.task.Task;
import lilybot.task.TaskList;
import lilybot.task.ToDo;

/**
 * Command for adding a todo task.
 */
public class TodoCommand implements Command {

    private Ui ui;
    private String command;
    private TaskList taskList;

    /**
     * Constructs TodoCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     */
    public TodoCommand(Ui ui, String command, TaskList taskList) {
        this.ui = ui;
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Adds the ToDo task to the list.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(Ui ui, String command, TaskList taskList) {
        String[] cmd = Parser.parseCommand(command);

        try {
            Task t = new ToDo(cmd[1]);
            taskList.add(t);

            String taskString = t.toString();
            return ui.printAdded(taskString, taskList);

        } catch (Exception exc) {
            return ui.invalidDescription();

        }
    }
}
