public class EmptyTaskException extends ChatbotException {
    public EmptyTaskException(String taskType) {
        super("Dave did not detect a task to be recorded.\n" +
        "Please try again by adding the name of the task as follows:\n" +
        taskType + " <task name>");
    }
}
