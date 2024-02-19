package duke.command.handler;

import duke.task.TaskDisplay;
import duke.task.TaskManager;

public class MarkCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    public MarkCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2 || !isNumeric(userMessage[1])) {
            return "You used the wrong syntax haha. Don't worry,\nmistakes are allowed! " +
                    "Use this: m <task number>";
        }

        int taskIndex = Integer.parseInt(userMessage[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskManager.getTasks().size()) {
            return "Sorry, I think you have entered a task number\nthat does not exist. " +
                    "Can you please check?";
        }

        taskManager.markAsComplete(taskIndex);
        return taskDisplay.displayMarkTask(taskManager.getTasks(), taskIndex);
    }

    @Override
    public String getDescription() {
        return "Marks a task as complete. Usage: m <task number>";
    }
}
