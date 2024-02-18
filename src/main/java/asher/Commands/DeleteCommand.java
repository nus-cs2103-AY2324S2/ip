package asher.Commands;

import asher.Ui.Ui;
import asher.Tasks.TaskList;
import asher.BotException;
import asher.Tasks.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

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
