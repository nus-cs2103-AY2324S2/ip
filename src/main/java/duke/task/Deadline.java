package duke.task;

import duke.utils.DateTime;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    protected DateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = new DateTime(by);
    }

    @Override
    public String writeContent() {
        return "D |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription()
                    + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}