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
    public void writeTask(String path) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, true))){
            out.write("D |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription()
                    + " | " + this.by);
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}