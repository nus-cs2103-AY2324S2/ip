public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String format() {
        return "todo | " + super.format();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
