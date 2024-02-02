package duke.task;

public class Todo extends Task {
    public Todo(String taskDescription, boolean isCompleted) {
        super(taskDescription, isCompleted);
    }

    @Override
    public String getTaskIcon() {
        return "[T]";
    }

    @Override
    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    @Override
    public String getTaskDescription() {
        return trimDescription(taskDescription);
    }

    @Override
    protected String trimDescription(String taskDescription) {
        return taskDescription.replaceAll("(?i)todo", "").trim();
    }
}
