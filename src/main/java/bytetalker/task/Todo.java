package bytetalker.task;

public class Todo extends Task {
    public Todo(String task) {
        super(TaskType.TODO, task);
    }

    public Todo(String task, boolean isDone) {
        super(TaskType.TODO, task, isDone);
    }

    @Override
    public String toString() {
        return "[" + getTaskType().getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask();
    }
}
