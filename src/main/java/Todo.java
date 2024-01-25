public class Todo extends Task {
    private static final String id = "[T]";
    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return id + super.toString();
    }
}
