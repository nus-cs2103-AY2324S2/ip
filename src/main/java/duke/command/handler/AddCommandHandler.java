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
            return "Hmm, this task type seems alien to me 🚀. Could you double-check?";
        } else if (taskDescription.isEmpty()) {
            return "Whoops! Looks like the task description got lost in space 🌌. Try beaming it down again, will ya?";
        }

        int taskIndex = taskManager.addTask(taskDescription, type);
        if (taskIndex >= 0) {
            return "Yay! Your task is on the board 📋. Here's what you told me to remember:\n" +
                    taskDisplay.displayAddTask(taskManager.getTasks(), taskIndex) +
                    "\nKeep rocking! 🎸";
        } else {
            return "Uh-oh, encountered a bit of a hiccup trying to add that task 🤯. Let's give it another shot?";
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
