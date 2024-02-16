package duke.command;

import duke.task.TaskManager;

public class UnmarkCommandHandler extends CommandHandler {
    public UnmarkCommandHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2 || !isNumeric(userMessage[1])) {
            return printError("unmark");
        }

        int taskIndex = Integer.parseInt(userMessage[1]) - 1;
        taskManager.markAsIncomplete(taskIndex);
        return taskManager.displayTask(String.join(" ", userMessage));
    }
}

