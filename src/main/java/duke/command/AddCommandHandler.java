package duke.command;

import duke.task.TaskManager;
import duke.task.TaskType;

public class AddCommandHandler extends CommandHandler {
    public AddCommandHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String handle(String[] userMessage) {
        String input = String.join(" ", userMessage);
        taskManager.addTask(input, TaskType.valueOf(userMessage[0].toUpperCase()));
        return taskManager.displayTask(input);
    }
}

