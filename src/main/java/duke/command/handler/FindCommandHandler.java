package duke.command.handler;

import duke.task.Task;
import duke.task.TaskDisplay;
import duke.task.TaskManager;

import java.util.Arrays;
import java.util.List;

/**
 * Handles the finding of tasks matching a keyword.
 *
 * This command handler searches for tasks in the task manager that match a specified keyword.
 * It displays the list of matching tasks to the user.
 *
 * Usage: find <keyword>
 */
public class FindCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    /**
     * Constructs a FindCommandHandler.
     *
     * @param taskManager the task manager
     * @param taskDisplay the task display
     */
    public FindCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    /**
     * Handles the user's input for finding tasks matching a keyword.
     *
     * @param userMessage the user's input message
     * @return a message indicating the result of the search
     */
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

    /**
     * Retrieves the description of the command handler.
     *
     * @return the description of the command handler
     */
    @Override
    public String getDescription() {
        return "Finds tasks matching a keyword. Usage: find <keyword>";
    }
}
