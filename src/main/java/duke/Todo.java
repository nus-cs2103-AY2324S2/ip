package duke;

/**
 * Todo class represents a task that needs to be done.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean isClashingWith(Task otherTask) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serializeToCommand(int taskIndex) {
        return "todo " + description + "\n" + serializeDoneMark(taskIndex);
    }
}
