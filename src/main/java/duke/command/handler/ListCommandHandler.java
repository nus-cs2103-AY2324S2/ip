package duke.command.handler;

import duke.task.TaskDisplay;
import duke.task.TaskManager;

public class ListCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    public ListCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    @Override
    public String handle(String[] userMessage) {
        return taskDisplay.displayTaskList(taskManager.getTasks());
    }

    @Override
    public String getDescription() {
        return "Lists all tasks. Usage: lst";
    }
}
