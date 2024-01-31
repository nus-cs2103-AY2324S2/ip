public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + this.description;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }
}
