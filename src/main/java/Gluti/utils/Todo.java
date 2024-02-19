package Gluti.utils;

/**
 * Simple Todo subclass that is a child of Task
 */
public class Todo extends Task {

    /**
     * Initializes a Todo instance
     * @param description name of Todo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}