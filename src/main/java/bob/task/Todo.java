package bob.task;

public class Todo extends Task {
    public static final String STORAGE_INDICATOR = "T";

    public Todo(String description) {
        super(description);
    }

    public String toStorageFormat() {
        return STORAGE_INDICATOR + " | " + super.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
