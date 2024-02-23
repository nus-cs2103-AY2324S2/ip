package asher.commands;

import asher.ui.Ui;
import asher.tasks.TaskList;
import asher.BotException;
import asher.tasks.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a Delete Command object with the given input string.
     *
     * @param input The input string for the Delete Command.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the Delete Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The String input for Delete Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            String[] words = input.split(" ");
            assert words.length >= 2 : "Please specify a task number you want to delete!";

            if (words.length < 2) {
                throw new BotException("Please specify a task number you want to delete!");
            }
            int taskId = Integer.parseInt(words[1]);
            int taskIndex = taskList.getTaskIndexById(taskId);
            if (taskIndex != -1) {
                Task removedTask = taskList.deleteTask(taskIndex);
                if (removedTask != null) {
                    taskList.updateTaskIds();
                    String removeTaskMessage = ui.showRemoveTaskMessage(removedTask);
                    String numberOfTaskMessage = ui.showNumberOfTaskInListMessage(taskList.getTasks().size());
                    return removeTaskMessage + "\n" + numberOfTaskMessage;
                } else {
                    throw new BotException("Task Number not found!");
                }
            } else {
                throw new BotException("Task Number not found!");
            }
        } catch (Exception e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
