package duke.command.handler;

import duke.command.handler.CommandHandler;
import duke.task.TaskManager;

public class ListCommandHandler extends CommandHandler {
    public ListCommandHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String handle(String[] userMessage) {
        return taskManager.displayTask(String.join(" ", userMessage));
    }

    @Override
    public String getDescription() {
        return "Lists all the task. Usage: lst";
    }
}
