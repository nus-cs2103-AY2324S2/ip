package duke.command.handler;

import duke.command.handler.CommandHandler;
import duke.task.Task;
import duke.task.TaskDisplay;
import duke.task.TaskManager;

import java.util.List;

public class FindCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    public FindCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2) {
            return "Can you specify a keyword after the find command so that I can help you better?";
        }

        String keyword = userMessage[1];
        List<Task> matchingTasks = taskManager.findTask(keyword);

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        return taskDisplay.printFindTaskList(matchingTasks);
    }

    @Override
    public String getDescription() {
        return "Finds a matching task. Usage: f gym";
    }
}

