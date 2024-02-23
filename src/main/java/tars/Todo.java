package tars;

/**
 * Represents Todo Task
 */
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.printWithStatus();
    }
}
