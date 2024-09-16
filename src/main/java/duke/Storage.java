package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.StorageException;
import duke.parser.FileParser;
import duke.tasks.Task;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the path of the file to be loaded/saved
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file at the specified path if it does not exist.
     *
     * @param f the file to be created
     * @throws StorageException if an error occurs while creating the file
     */
    private void createFile(File f) throws StorageException {
        try {
            f.getParentFile().mkdirs();
            boolean created = f.createNewFile();
            assert created : "Failed to create file: " + f.getAbsolutePath();
        } catch (IOException e) {
            throw new StorageException("Error creating file: " + f.getAbsolutePath());
        }
    }

    /**
     * Loads tasks from the file specified by filePath.
     *
     * @return an ArrayList containing the loaded tasks
     * @throws StorageException if an error occurs while reading the file
     * @throws FileNotFoundException if the file does not exist
     */
    public ArrayList<Task> loadFile() throws StorageException, FileNotFoundException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks;

        if (!f.exists()) {
            this.createFile(f);
            throw new FileNotFoundException();
        }

        assert f.exists() : "File does not exist: " + f.getAbsolutePath();
        tasks = FileParser.readFile(f);
        return tasks;
    }

    /**
     * Saves tasks to the file specified by filePath.
     *
     * @param tasks the list of tasks to be saved
     * @throws IOException if an error occurs while writing to the file
     */
    public void saveData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(tasks.toString());
        fw.close();
    }

}
