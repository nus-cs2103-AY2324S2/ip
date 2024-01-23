public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }

    public static TaskException forEmptyTaskDescription() {
        return new TaskException("A task description is required. Please try adding a task with a description.");
    }

    public static TaskException forInvalidTaskFormat(String taskType) {
        return new TaskException("The format for the " + taskType + " task is incorrect. Please check and try again.");
    }

    public static TaskException forUnknownCommand() {
        return new TaskException("Sorry, invalid command. Please try again.");
    }

    public static TaskException forInvalidTaskNumber() {
        return new TaskException("Invalid task number. Please check and try again.");
    }
}

