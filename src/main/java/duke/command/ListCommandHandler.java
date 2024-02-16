package duke.command;

import duke.task.TaskManager;

public class ListCommandHandler extends CommandHandler {
    public ListCommandHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String handle(String[] userMessage) {
        return taskManager.displayTask(String.join(" ", userMessage));
    }
}
