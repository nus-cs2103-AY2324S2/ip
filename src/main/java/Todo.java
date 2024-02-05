public class Todo extends Task {
    public Todo(String task) {
        super(TaskType.TODO, task);
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask();
    }
}
