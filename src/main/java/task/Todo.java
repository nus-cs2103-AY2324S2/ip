package task;

/**
 * Represents a to-do task in the Uncle Bob application.
 */
public class Todo extends Task {

    public Todo(String description) {
        super("[T]", description);
    }

    @Override
    public String toString() {
        return this.symbol + super.toString();
    }
}
