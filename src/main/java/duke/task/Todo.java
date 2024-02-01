package duke.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void writeTask(String path) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, true))){
            out.write("T |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription());
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}