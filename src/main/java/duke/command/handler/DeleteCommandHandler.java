package duke.command.handler;

import duke.task.TaskManager;
import duke.task.TaskDisplay; // Make sure to have TaskDisplay imported or implemented accordingly
import duke.task.Task;

public class DeleteCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    public DeleteCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }

    @Override
    public String handle(String[] userMessage) {
        if (userMessage.length < 2 || !isNumeric(userMessage[1])) {
            return "You used the wrong syntax haha. Don't worry,\nmistakes are allowed! " +
                    "Use this: del <task number>";
        }

        int taskIndex = Integer.parseInt(userMessage[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskManager.getTasks().size()) {
            return "Sorry, I think you have entered a task number \nthat does not exist. " +
                    "Can you please check?";
        }

        Task taskToDelete = taskManager.getTask(taskIndex);
        if (taskToDelete != null) {
            String taskDetails = taskDisplay.displayDeleteTask(taskManager.getTasks(), taskIndex);
            taskManager.deleteTask(taskIndex);
            return taskDetails;
        } else {
            return "hey, I think the task number you provided doesn't exist.";
        }
    }

    @Override
    public String getDescription() {
        return "Deletes a task. Usage: del <task number>";
    }
}
