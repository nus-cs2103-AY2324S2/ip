package blu.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import blu.exception.BluException;
import blu.exception.StorageException;
import blu.task.Task;
import blu.task.TaskList;

public class Storage implements AutoCloseable {
    /** File object used to write and store tasks */
    private File file;
    private BufferedWriter writer;

    /**
     * Creates a Storage object with its associated file based on the specified file path.
     * If the file does not exist, it attempts to create it.
     *
     * @param filePath The path of the file used for storage.
     * @throws StorageException If the file cannot be created or opened.
     */
    public Storage(String filePath) throws StorageException {
        this.file = new File(filePath);
        try {
            if (!this.file.exists()) {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            }
            this.writer = new BufferedWriter(new FileWriter(this.file, true));
        } catch (IOException e) {
            throw new StorageException("Failed to create storage file");
        }
    }

    /**
     * Saves all tasks from the given TaskList to the storage file.
     * 
     * @param taskList The list of tasks to save.
     * @throws StorageException If tasks cannot be written to the storage file.
     */
    public void saveTasks(TaskList taskList) throws StorageException {
        try {
            this.writer.close();
            this.writer = new BufferedWriter(new FileWriter(this.file, false));
            List<Task> tasks = taskList.getAllTasks();
            for (Task task : tasks) {
                this.writer.write(task.toCsv());
                this.writer.newLine();
            }
            this.writer.flush();
        } catch (IOException e) {
            throw new StorageException("Failed to save task to storage file");
        }
    }

    /**
     * Loads tasks from the storage file into a TaskList.
     *
     * @return A TaskList containing the tasks loaded from the file.
     * @throws StorageException If tasks cannot be read from the storage file.
     */
    public TaskList loadTasks() throws StorageException {
        List<Task> tasks = new ArrayList<>();
        int lineNumber = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line = reader.readLine();
            while (line != null) {
                Task task = TaskDecoder.fromCsv(line);
                tasks.add(task);
                line = reader.readLine();
                lineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            throw new StorageException("Failed to read tasks from storage file");
        } catch (BluException e) {
            throw new StorageException(e.getLocalizedMessage() + " at line " + lineNumber);
        }
        if (tasks.isEmpty()) {
            return new TaskList();
        }
        return new TaskList(tasks);
    }

    /**
     * Closes the writer associated with this Storage object.
     *
     * @throws StorageException If an I/O error occurs while closing the writer.
     */
    public void close() throws StorageException {
        try {
            this.writer.flush();
            this.writer.close();
        } catch (IOException e) {
            throw new StorageException("Could not close writer");
        }
    }
    
}
