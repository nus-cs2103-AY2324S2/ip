import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class Deadline extends Task {
    String deadline;
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s %s (by: %s)", "[D]", super.toString(), this.deadline);
    }

    @Override
    public void writeToData() throws IOException {

    }
}
