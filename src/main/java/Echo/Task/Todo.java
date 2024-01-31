package Echo.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s", getTaskType(), isDone() ? 1 : 0, getDescription());
    }
}
