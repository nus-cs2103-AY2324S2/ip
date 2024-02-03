package eggy.task;

public class Todo extends Task {
    public Todo (String name) {
        super(name);
    }

    public Todo (String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
