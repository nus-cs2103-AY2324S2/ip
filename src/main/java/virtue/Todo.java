package virtue;

/** Represents a todo task. */
public class Todo extends VirtueTask {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileFormat() {
        return "T | " + super.fileFormat();
    }
}
