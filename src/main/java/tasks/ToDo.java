package tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        this(description, false);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFile() {
        return "T | " + super.toFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
