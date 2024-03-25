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
    private TaskList taskList;

    /**
     * Constructs EventCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param taskList For tracking the list of tasks.
     */
    public EventCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Adds the Event task to the list.
     *
     * @param command Command entered by users.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(String command) {
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
