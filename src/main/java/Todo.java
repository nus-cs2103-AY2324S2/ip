public class Todo extends Task {
    public Todo(String description) {
        super(description, "", "", "todo");
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
