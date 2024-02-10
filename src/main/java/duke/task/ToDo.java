package duke.task;

/**
 * Represents a simple task with only name supplied
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name, "T");
    }

    /**
     * Returns String representation of current ToDo object
     * @return  current ToDo object as a String
     */

    public String toString() {
        String status = this.isComplete() ? "[x]" : "[ ]";
        return "[" + this.getType() + "] " + status + " " + this.getName();
    }
}

