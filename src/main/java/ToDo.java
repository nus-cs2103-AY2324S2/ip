import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("%s %s", "[T]", super.toString());
    }

    @Override
    public void writeToData(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        String writeData = String.format("%s|%s\n", "T", super.dataString());
        fileWriter.write(writeData);
        fileWriter.close();
    }
}
