package bob.task;

public class Todo extends Task {
    public static final String STORAGE_INDICATOR = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStorageFormat() {
        return STORAGE_INDICATOR + " | " + super.getStorageFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
