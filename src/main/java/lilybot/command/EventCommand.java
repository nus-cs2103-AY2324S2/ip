package lilybot.command;

import lilybot.gui.Ui;
import lilybot.parser.Parser;
import lilybot.task.Event;
import lilybot.task.Task;
import lilybot.task.TaskList;

/**
 * Command for adding an event task.
 */
public class EventCommand implements Command {

    private Ui ui;
    private String command;
    private TaskList taskList;

    /**
     * Constructs EventCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     */
    public EventCommand(Ui ui, String command, TaskList taskList) {
        this.ui = ui;
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Adds the Event task to the list.
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
            String[] date = cmd[1].split("/", 3);
            try {
                Task t = new Event(date[0],
                        date[1],
                        date[2]);
                taskList.add(t);
                String taskString = t.toString();
                return ui.printAdded(taskString, taskList);

            } catch (Exception exc) {
                return ui.invalidEventFormat();

            }
        } catch (Exception exc) {
            return ui.invalidDescription();

        }
    }
}
