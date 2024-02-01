package dune.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
