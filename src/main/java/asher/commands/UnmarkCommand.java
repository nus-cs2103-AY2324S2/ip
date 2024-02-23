package asher.commands;

import asher.BotException;
import asher.tasks.TaskList;
import asher.ui.Ui;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an Unmark Command object with the given input string.
     *
     * @param input The input string for the Unmark Command.
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Executes the Unmark Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for Unmark Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            if (!input.matches("^unmark \\d+$")) {
                throw new BotException("Please specify a task number to unmark!");
            }

            int taskNumber = taskList.getTaskNumber(input);
            if (taskNumber != -1) {
                taskList.getTasks().get(taskNumber).markUndone();
                return ui.showUnmarkTaskMessage(taskList.getTasks().get(taskNumber));
            } else {
                throw new BotException("Task Number not found!");
            }
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
