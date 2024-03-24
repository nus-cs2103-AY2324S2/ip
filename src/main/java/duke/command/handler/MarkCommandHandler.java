package duke.command.handler;

import duke.task.TaskDisplay;
import duke.task.TaskManager;

/**
 * Handles marking a task as complete.
 *
 * This command handler marks a specified task as complete in the task manager.
 *
 * Usage: m <task number>
 */
public class MarkCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    /**
     * Constructs a MarkCommandHandler.
     *
     * @param taskManager the task manager
     * @param taskDisplay the task display
     */
    public MarkCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    /**
     * Handles the user's input for marking a task as complete.
     *
     * @param userMessage the user's input message
     * @return a message indicating the result of marking the task as complete
     */
    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2 || !isNumeric(userMessage[1])) {
            return "Whoopsie-doodle! 😜 Looks like we're speaking different languages. No biggie! " +
                    "Try saying it like this: m <task number>. You've got this! 🌟";
        }

        int taskIndex = Integer.parseInt(userMessage[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskManager.getTasks().size()) {
            return "Oh noes! 🙈 It seems like that task number is playing hide and seek and it's winning. " +
                    "Could you give it another look? Pretty please with a cherry 🍒 on top?";
        }

        taskManager.markAsComplete(taskIndex);
        return "YAY! 🎉 We did it! Task " + (taskIndex + 1) + " is now marked as done! " +
                "Feels good, doesn't it? Here's a high five! ✋\n" +
                taskDisplay.displayMarkTask(taskManager.getTasks(), taskIndex);
    }

    /**
     * Retrieves the description of the command handler.
     *
     * @return the description of the command handler
     */
    @Override
    public String getDescription() {
        return "Marks a task as complete. Usage: m <task number>";
    }
}
