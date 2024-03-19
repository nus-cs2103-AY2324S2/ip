package lilybot.Command;

import lilybot.Parser.Parser;
import lilybot.Task.Deadline;
import lilybot.Task.Task;
import lilybot.Task.TaskList;
import lilybot.Gui.Ui;

public class DeadlineCommand implements Command {

    private Ui ui;
    private String command;
    private TaskList taskList;

    /**
     * Constructs DeadlineCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     */
    public DeadlineCommand(Ui ui, String command, TaskList taskList) {
        this.ui = ui;
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Adds the Deadline task to the list.
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
            String[] date = cmd[1].split("/by", 2);
            try {
                String d = date[1].trim();
                Task t = new Deadline(date[0], d);
                taskList.add(t);
                String taskString = t.toString();
                return ui.printAdded(taskString, taskList);
            }
            catch (Exception exc) {
                return ui.invalidDdlFormat();
            }
        } catch (Exception exc) {
            return ui.invalidDescription();

        }
    }
}
