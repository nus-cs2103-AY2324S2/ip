package duke.task;

import java.util.Objects;

public class Todo extends Task {
    String type = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.type + this.display + " " + this.description;
    }

    @Override
    public String toDBString() {
        String display;
        if (Objects.equals(this.display, "[ ]")) {
            display = "0";
        } else {
            display = "1";
        }
        return "T|" + display + "|" + this.description;
    }
}
