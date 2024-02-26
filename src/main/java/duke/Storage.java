package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class which handles loading / saving of task list from hard disk
 *
 */
public class Storage {
    private File file;
    private String filepath;

    /**
     * Creates an instance of Storage.
     * @param filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    /**
     * Loads data file from hard disk.
     * @return The data file containing task list.
     * @throws DukeException If something went wrong while creating file
     */
    public File load() throws DukeException {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                writeToFile("");
                throw new DukeException();
            } catch (IOException e) {
                throw new DukeException("Something went wrong: " + e);
            }
        } else {
            return file;
        }
    }


    /**
     * Writes text to the data file
     * @param text Text to be written
     * @throws IOException When FileWriter throws an exception
     */
    public void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(text);
        fw.close();
    }

}
