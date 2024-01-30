package ezra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the storage of tasks in a file.
 */
public class Storage {

    String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The file path for storing tasks.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Writes the tasks from a TaskList to the storage file.
     *
     * @param tasks The TaskList containing tasks to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        File directory = new File("data");
        directory.mkdir();
        File f = new File("data" + File.separator + "ezra.txt");
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        StringBuilder builder = new StringBuilder();
        for (Task t : tasks.arrayList) {
            builder.append(t.toString2()).append("\n");
        }
        fw.write(builder.toString());
        fw.close();
    }

    /**
     * Loads the storage file.
     *
     * @return The File object representing the storage file.
     */
    public File load() {
        return new File(filepath);
    }
}
