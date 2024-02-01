package duke.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String writeContent() {
        return "T |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}