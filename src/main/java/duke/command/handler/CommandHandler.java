package duke.command.handler;

import duke.task.TaskManager;

public abstract class CommandHandler {
    protected final TaskManager taskManager;

    public CommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    protected boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public abstract String handle(String[] userMessage);

    public abstract String getDescription();
}