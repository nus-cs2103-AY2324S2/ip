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
            if (words.length < 2) {
                throw new BotException("Invalid format of input!");
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
                    return ui.showErrorMessage("Task not found!");
                }
            } else {
                return ui.showErrorMessage("Task not found!");
            }
        } catch (Exception e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
