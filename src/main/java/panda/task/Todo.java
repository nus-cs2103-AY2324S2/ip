package panda.task;

public class Todo extends Task {
    /**
     * Constructs a new Todo with the given description.
     * 
     * @param desc the description of the task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return the string representation of the task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the task suitable for saving to a file.
     * 
     * @return the string representation of the task.
     */
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }
}
