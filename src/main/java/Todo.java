public class Todo extends Task {
    public Todo(String description) {
        super(description, "", "", Type.TODO);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
