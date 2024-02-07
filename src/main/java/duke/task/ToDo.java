package duke.task;

/**
 * Represents a simple task with only name supplied
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name, "T");
    }
    public String toString() {
        String status = this.isComplete ? "[x]" : "[ ]";
        return "[T] " + status + " " + this.name;
    }
}

