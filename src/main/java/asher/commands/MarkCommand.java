package asher.commands;

import asher.BotException;
import asher.tasks.TaskList;
import asher.ui.Ui;

/**
 * Represents a command to mark a command.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a Mark Command object with the given input string.
     *
     * @param input The input string for the Mark Command.
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Executes the Mark Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for Mark Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            if (!input.matches("^mark \\d+$")) {
                throw new BotException("Please specify a task number to mark!");
            }

            int taskNumber = taskList.getTaskNumber(input);
            if (taskNumber != -1) {
                taskList.getTasks().get(taskNumber).markDone();
                return ui.showMarkTaskMessage(taskList.getTasks().get(taskNumber));
            } else {
                throw new BotException("Task Number not found!");
            }
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
