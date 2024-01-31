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
    public void writeToData(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        String writeData = String.format("%s|%s|%s\n", "D", super.dataString(), this.deadline);
        fileWriter.write(writeData);
        fileWriter.close();
    }
}
