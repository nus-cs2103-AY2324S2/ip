package bob;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toStorageFormat() {
        return "todo | " + super.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
