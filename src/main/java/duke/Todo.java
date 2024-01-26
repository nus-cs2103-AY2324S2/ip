package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        return String.format("T | %s", fileLine.substring(4));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
