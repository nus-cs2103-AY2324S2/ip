package tasks;

/**
 * Represents a Todo, inherits from Task.
 */
public class ToDo extends Task {
    /**
     * ToDo constructor
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileString() {
        return "T " + super.fileString();
    }
}
