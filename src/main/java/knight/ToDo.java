package knight;

/**
 * Represents a task that needs to be done.
 */
public class ToDo extends Task {
    ToDo(String name) {
        super(name);
    }

    @Override
    String getCommand() {
        return "todo " + name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    void update(String updateMessage) {
        this.name = updateMessage;
    }
}
