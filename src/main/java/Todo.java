public class Todo extends Task {
    public Todo(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
