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

    @Override
    public String getDescription() {
        return "Deletes a task. Usage: del <task number>";
    }
}
