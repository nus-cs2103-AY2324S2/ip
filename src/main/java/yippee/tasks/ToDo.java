package yippee.tasks;

/**
 * Represents tasks of type ToDo.
 */
public class ToDo extends Task {
    /**
     * Instantiates new ToDo task.
     * @param name String representation of name of the new task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("%s %s", "[T]", super.toString());
    }

    @Override
    public String dataString() {
        return String.format("%s|%s\n", "T", super.dataString());
    }
}
