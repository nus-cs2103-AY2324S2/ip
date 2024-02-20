package duke.command.handler;

import duke.task.TaskManager;
import duke.task.TaskType;
import duke.task.TaskDisplay;

import java.util.Arrays;

public class AddCommandHandler extends CommandHandler {
    private TaskDisplay taskDisplay;

    public AddCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    @Override
    public String handle(String[] userMessage) {

        String taskDescription = String.join(" ", Arrays.copyOfRange(userMessage, 1, userMessage.length)).trim();

        TaskType type = determineTaskType(userMessage[0]);

        if (type == null) {
            return "I don't remember this task type.";
        } else if (taskDescription.isEmpty()) {
            return "Hi, I think you missed the description.\nNo worries, try again!";
        }

        int taskIndex = taskManager.addTask(taskDescription, type);
        if (taskIndex >= 0) {
            return taskDisplay.displayAddTask(taskManager.getTasks(), taskIndex);
        } else {
            return "Sorry, I think the task could not be added" +
                    "\ndue to some error.";
        }
    }

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

    @Override
    public String getDescription() {
        return "Adds a new task. Usage: <command> [description]";
    }
}
