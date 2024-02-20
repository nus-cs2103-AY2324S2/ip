package duke.command.handler;

import duke.task.TaskDisplay;
import duke.task.TaskManager;

public class UnmarkCommandHandler extends CommandHandler {
    private final TaskDisplay taskDisplay;

    public UnmarkCommandHandler(TaskManager taskManager, TaskDisplay taskDisplay) {
        super(taskManager);
        this.taskDisplay = taskDisplay;
    }
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

    @Override
    public String getDescription() {
        return "Marks a task as incomplete. Usage: um <task number>";
    }
}
