package lilybot.command;

import lilybot.gui.Ui;
import lilybot.parser.Parser;
import lilybot.task.Deadline;
import lilybot.task.Task;
import lilybot.task.TaskList;

/**
 * Command for adding a deadline task.
 */
public class DeadlineCommand implements Command {

    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs DeadlineCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param taskList For tracking the list of tasks.
     */
    public DeadlineCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Adds the Deadline task to the list.
     *
     * @param command Command entered by users.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(String command) {
        String[] cmd = Parser.parseCommand(command);

        try {
            String[] date = cmd[1].split("/by", 2);
            try {
                String d = date[1].trim();
                Task t = new Deadline(date[0], d);
                taskList.add(t);
                String taskString = t.toString();
                return ui.printAdded(taskString, taskList);
            } catch (Exception exc) {
                return ui.invalidDdlFormat();
            }
        } catch (Exception exc) {
            return ui.invalidDescription();

        }
    }
}
