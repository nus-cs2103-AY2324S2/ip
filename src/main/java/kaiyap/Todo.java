package kaiyap;

/**
 * Represents a Todo task in the KaiYap application.
 * A Todo task is a basic task type that includes a description but does not require any date or time information.
 * This class extends the Task class, inheriting its basic properties.
 */
public class Todo extends Task {
    public Todo(String listItem, String inputItem) {
        super(listItem, inputItem);
    }

    @Override
    public String toString() {
        return "[T]"
                + (this.isCompleted ? "[X] " : "[ ] ")
                + this.listItem;
    }
}
