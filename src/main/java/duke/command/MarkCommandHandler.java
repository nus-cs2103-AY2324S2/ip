package duke.command;

import duke.task.TaskManager;

public class MarkCommandHandler extends CommandHandler {
    public MarkCommandHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2 || !isNumeric(userMessage[1])) {
            return printError("mark");
        }

        int taskIndex = Integer.parseInt(userMessage[1]) - 1;
        taskManager.markAsComplete(taskIndex);
        return taskManager.displayTask(String.join(" ", userMessage));
    }
}
