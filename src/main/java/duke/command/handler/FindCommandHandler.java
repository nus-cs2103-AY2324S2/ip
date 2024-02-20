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
            return "Oopsie-daisy! 🌼 Looks like you forgot to tell me what you're looking for. No worries! " +
                    "Let's try that again with this magic formula: f <keyword> 🧙‍♂️✨";
        }

        String keyword = String.join(" ", Arrays.copyOfRange(userMessage, 1, userMessage.length));
        List<Task> matchingTasks = taskManager.findTask(keyword);

        if (matchingTasks.isEmpty()) {
            return "Hmm, my crystal ball 🔮 shows no tasks matching that description. " +
                    "Maybe try a different keyword? I'm sure we'll find something! 🕵️‍♂️";
        }

        return "Eureka! 🎉 I found some tasks that might be what you're looking for:\n" +
                taskDisplay.displayFindTaskList(matchingTasks);
    }

    @Override
    public String getDescription() {
        return "Finds tasks matching a keyword. Usage: find <keyword>";
    }
}
