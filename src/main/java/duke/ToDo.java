package duke;

/**
 * Represents a ToDo task, with a description and is a subclass of
 * {@link Task}, inheriting its properties and methods.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
