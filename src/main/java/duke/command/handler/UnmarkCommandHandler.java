package duke.command.handler;

import duke.task.TaskDisplay;
import duke.task.TaskManager;

/**
 * Handles marking a task as incomplete.
 *
 * This command handler marks a specified task as incomplete in the task manager.
 *
 * Usage: um <task number>
 */
public class UnmarkCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    /**
     * Constructs an UnmarkCommandHandler.
     *
     * @param taskManager the task manager
     * @param taskDisplay the task display
     */
    public UnmarkCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    /**
     * Handles the user's input for marking a task as incomplete.
     *
     * @param userMessage the user's input message
     * @return a message indicating the result of marking the task as incomplete
     */
    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2 || !isNumeric(userMessage[1])) {
            return "Oopsie daisy! 🌼 Looks like the magic spell was a bit off. No worries, " +
                    "we all fumble sometimes! 🤷‍♂️ " +
                    "Try casting it like this: um <task number>. You've got this! 💪";
        }

        int taskIndex = Integer.parseInt(userMessage[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskManager.getTasks().size()) {
            return "Hmm... 🤔 I peeked through my magic book 📖 and couldn't find a task with that number. " +
                    "Could you double-check? Maybe it's hiding under a cloak of invisibility! 🧙‍♂️";
        }

        taskManager.markAsIncomplete(taskIndex);
        return "Abracadabra! Your task is back on the list, ready to conquer! " +
                "Remember, completing tasks is a journey, not a sprint.\n" +
                taskDisplay.displayUnmarkTask(taskManager.getTasks(), taskIndex);
    }

    /**
     * Retrieves the description of the command handler.
     *
     * @return the description of the command handler
     */
    @Override
    public String getDescription() {
        return "Marks a task as incomplete. Usage: um <task number>";
    }
}
