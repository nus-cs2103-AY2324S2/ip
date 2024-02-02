package ezra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the storage of tasks in a file.
 */
public class Storage {

    protected String filepath;

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
     * @param taskList The TaskList containing tasks to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        // Create data directory if it does not exist
        File directory = new File("data");
        directory.mkdir();

        // Create ezra.txt in data directory if it does not exist
        File f = new File("data/ezra.txt");
        f.createNewFile();

        // Write tasks to ezra.txt
        FileWriter fw = new FileWriter(f);
        StringBuilder builder = new StringBuilder();
        for (Task t : taskList.tasks) {
            builder.append(t.toStorageString()).append("\n");
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
