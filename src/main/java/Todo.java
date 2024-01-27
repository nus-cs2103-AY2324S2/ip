public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone, "", "", TaskType.TODO);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
