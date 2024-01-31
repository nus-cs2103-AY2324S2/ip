package duke.task;

import java.util.Objects;

/**
 * The class representing an Event task.
 * */
public class Todo extends Task {
    /* Type indicator for Todo task. */
    String type = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.type + this.display + " " + this.description;
    }

    @Override
    public String toDbString() {
        String display;
        if (Objects.equals(this.display, "[ ]")) {
            display = "0";
        } else {
            display = "1";
        }
        return "T|" + display + "|" + this.description;
    }
}
