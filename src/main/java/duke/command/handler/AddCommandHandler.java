package duke.command.handler;

import duke.task.TaskManager;
import duke.task.TaskType;
import duke.task.TaskDisplay;

import java.util.Arrays;

/**
 * Handles the addition of a new task.
 *
 * This command handler adds a new task to the task manager based on the user's input.
 * It determines the type of task (todo, event, deadline) and adds it accordingly.
 *
 * Usage: <command> [description]
 */
public class AddCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    /**
     * Constructs an AddCommandHandler.
     *
     * @param taskManager the task manager
     * @param taskDisplay the task display
     */
    public AddCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    /**
     * Handles the user's input for adding a new task.
     *
     * @param userMessage the user's input message
     * @return a message indicating the result of the addition
     */
    @Override
    public String handle(String[] userMessage) {

        String taskDescription = String.join(" ", Arrays.copyOfRange(userMessage, 1, userMessage.length)).trim();
        TaskType type = determineTaskType(userMessage[0]);

        if (type == null) {
            return "Hmm, this task type seems alien to me 🚀. Could you double-check?";
        } else if (taskDescription.isEmpty()) {
            return "Whoops! Looks like the task description got lost in space 🌌. Try beaming it down again, will ya?";
        }

        int taskIndex = taskManager.addTask(taskDescription, type);
        if (taskIndex >= 0) {
            return "Yay! Your task is on the board 📋. Here's what you told me to remember:\n" +
                    taskDisplay.displayAddTask(taskManager.getTasks(), taskIndex) +
                    "Keep rocking! 🎸";
        } else {
            return "Uh-oh, encountered a bit of a hiccup trying to add that task 🤯. Let's give it another shot?";
        }
    }

    /**
     * Determines the type of task based on the user's input.
     *
     * @param command the user's input command
     * @return the type of task (todo, event, deadline)
     */
    private TaskType determineTaskType(String command) {
        switch (command.toLowerCase()) {
            case "t":
            case "todo":
                return TaskType.TODO;
            case "e":
            case "event":
                return TaskType.EVENT;
            case "dl":
            case "deadline":
                return TaskType.DEADLINE;
            default:
                return null;
        }
    }

    /**
     * Retrieves the description of the command handler.
     *
     * @return the description of the command handler
     */
    @Override
    public String getDescription() {
        return "Adds a new task. Usage: <command> [description]";
    }
}
