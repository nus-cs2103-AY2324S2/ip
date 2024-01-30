package ezra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }
    public void writeToFile(TaskList taskList) throws IOException {
        // Create data directory if it does not exist
        File directory = new File("data");
        directory.mkdir();

        // Create ezra.txt in data directory if it does not exist
        File f = new File("data" + File.separator + "ezra.txt");
        f.createNewFile();

        // Write tasks to ezra.txt
        FileWriter fw = new FileWriter(f);
        StringBuilder builder = new StringBuilder();
        for (Task t : taskList.tasks) {
            builder.append(t.toString2()).append("\n");
        }
        fw.write(builder.toString());
        fw.close();
    }

    public File load() {
        return new File(filepath);
    }
}
