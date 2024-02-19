package duke.command.handler;

import duke.command.handler.CommandHandler;
import duke.task.TaskManager;
import duke.task.TaskType;

import java.util.Arrays;

public class AddCommandHandler extends CommandHandler {
    public AddCommandHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String handle(String[] userMessage) {
        // Assume the first word is the command, and the actual task starts from the second word
        String command = userMessage[0].toLowerCase();
        TaskType type;
        String taskDescription = String.join(" ", Arrays.copyOfRange(userMessage, 0, userMessage.length));

        switch (command) {
            case "t":
            case "todo":
                type = TaskType.TODO;
                break;
            case "e":
            case "event":
                type = TaskType.EVENT;
                break;
            case "d":
            case "deadline":
                type = TaskType.DEADLINE;
                break;
            default:
                // Handle unknown command
                return "Unknown task type.";
        }

        if (taskManager.addTask(taskDescription, type)) {
            return taskManager.displayTask(taskDescription);
        } else {
            return "Task could not be added.";
        }
    }

    @Override
    public String getDescription() {
        return "Adds a new task. Usage:  [description]";
    }
}