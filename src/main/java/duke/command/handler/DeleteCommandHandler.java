package duke.command.handler;

import duke.task.TaskManager;
import duke.task.TaskDisplay;
import duke.task.Task;

/**
 * Handles the deletion of a task.
 *
 * This command handler deletes a task from the task manager based on the user's input.
 * It validates the user's input to ensure a valid task number is provided for deletion.
 *
 * Usage: del <task number>
 */
public class DeleteCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    /**
     * Constructs a DeleteCommandHandler.
     *
     * @param taskManager the task manager
     * @param taskDisplay the task display
     */
    public DeleteCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    /**
     * Handles the user's input for deleting a task.
     *
     * @param userMessage the user's input message
     * @return a message indicating the result of the deletion
     */
    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2 || !isNumeric(userMessage[1])) {
            return "Oops! Looks like we're missing something. 🤷‍♂️ No problemo, everyone has those days! " +
                    "Try this magic spell: del <task number> 🪄";
        }

        int taskIndex = Integer.parseInt(userMessage[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskManager.getTasks().size()) {
            return "Hmmm, I peeked everywhere 🕵️‍♂️ but couldn't find a task with that number... " +
                    "Could you double-check? Maybe it's playing hide and seek! 🙈";
        }

        Task taskToDelete = taskManager.getTask(taskIndex);
        if (taskToDelete != null) {
            String taskDetails = taskDisplay.displayDeleteTask(taskManager.getTasks(), taskIndex);
            taskManager.deleteTask(taskIndex);
            return "Poof! ✨ Your task vanished into thin air! Here's what we said goodbye to:\n" + taskDetails;
        } else {
            return "This is awkward... 😅 Seems like that task number is a bit of a mystery. Mind trying again?";
        }
    }

    /**
     * Retrieves the description of the command handler.
     *
     * @return the description of the command handler
     */
    @Override
    public String getDescription() {
        return "Deletes a task. Usage: del <task number>";
    }
}
