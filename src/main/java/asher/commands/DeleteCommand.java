package asher.commands;

import asher.ui.Ui;
import asher.tasks.TaskList;
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
            assert words.length >= 2 : "Invalid format of input!";

            int taskId = Integer.parseInt(words[1]);
            assert taskId >= 0 : "Invalid task ID!";

            int taskIndex = taskList.getTaskIndexById(taskId);
            assert taskIndex != -1 : "Task not found";

            Task removedTask = taskList.deleteTask(taskIndex);
            assert removedTask != null : "Task not found";

            taskList.updateTaskIds();
            String removeTaskMessage = ui.showRemoveTaskMessage(removedTask);
            String numberOfTaskMessage = ui.showNumberOfTaskInListMessage(taskList.getTasks().size());
            return removeTaskMessage + "\n" + numberOfTaskMessage;
        } catch (Exception e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
