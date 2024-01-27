package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveToText() {
        return String.format("T | %s | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
