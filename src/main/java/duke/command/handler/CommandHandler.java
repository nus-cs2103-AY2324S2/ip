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

    protected String printError(String command) {
        return "Sorry, the task number is missing after " + command.toUpperCase() +
                ". Can you please specify a valid task number from the list?";
    }

    public abstract String handle(String[] userMessage);

    public abstract String getDescription();
}