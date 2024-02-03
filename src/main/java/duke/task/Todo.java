
package duke.task;

/**
 * Represents the todo type of Task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String type() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + (this.checkDone() ? "[X] " : "[ ] ") + this.getDescription();
    }
}