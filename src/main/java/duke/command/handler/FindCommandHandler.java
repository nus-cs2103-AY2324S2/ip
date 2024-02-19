package duke.command.handler;

import duke.task.Task;
import duke.task.TaskDisplay;
import duke.task.TaskManager;

import java.util.Arrays;
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
            return "Hey, you missed the keyword. It's okay! " +
                    "\nTry this: f <keyword>";
        }

        String keyword = String.join(" ", Arrays.copyOfRange(userMessage, 1, userMessage.length));

        List<Task> matchingTasks = taskManager.findTask(keyword);

        if (matchingTasks.isEmpty()) {
            return "Sorry, there's no tasks that matches that keyword." +
                    "\nHow about you try something else!";
        }

        return taskDisplay.displayFindTaskList(matchingTasks);
    }

    @Override
    public String getDescription() {
        return "Finds tasks matching a keyword. Usage: find <keyword>";
    }
}
