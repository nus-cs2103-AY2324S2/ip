package duke.command;

import duke.task.TaskManager;

public class DeleteCommandHandler extends CommandHandler {
    public DeleteCommandHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2 || !isNumeric(userMessage[1])) {
            return printError("mark");
        }

        int taskIndex = Integer.parseInt(userMessage[1]) - 1;
        String deletedTask = taskManager.displayTask(String.join(" ", userMessage));
        taskManager.deleteTask(taskIndex);
        return deletedTask;
    }
}
