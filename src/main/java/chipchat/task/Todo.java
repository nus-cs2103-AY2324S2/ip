package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String dataString() {
        return String.format("todo|%s", super.dataString());
    }
}
