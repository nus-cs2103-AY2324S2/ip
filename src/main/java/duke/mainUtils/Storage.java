package duke.mainUtils;

import duke.exceptions.InvalidDateException;
import duke.exceptions.StorageException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;


/**
 * The Storage class manages the loading and saving of tasks to and from a file.
 * It handles file I/O operations and interacts with the TaskList to manage tasks.
 * <p>
 * The class provides methods to load tasks from a specified file, save tasks to the same file,
 * and create the file if it does not exist.
 * </p>
 * <p>
 * The file path for storing tasks is provided during object instantiation.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.tasks.TaskList
 * @see duke.tasks.Task
 * @see duke.exceptions.StorageException
 * @see duke.exceptions.InvalidDateException
 */
public class Storage {
    private final String filePath;
    private final TaskList taskList;

    /**
     * Constructs a Storage object with the specified file path and task list.
     *
     * @param filePath the file path to store tasks.
     * @param taskList the task list to manage tasks.
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    /**
     * Loads tasks from the file specified in the file path.
     *
     * @throws StorageException if there is an error loading tasks from the file.
     */
    public void load() throws StorageException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    taskList.addTask(Parser.parseSaveFile(line));
                }
            }
        } catch (IOException e) {
            createSaveFile();
        } catch (InvalidDateException ex) {
            throw new StorageException();
        }
    }

    /**
     * Saves tasks to the file specified in the file path.
     *
     * @throws StorageException if there is an error saving tasks to the file.
     */
    public void save() throws StorageException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : this.taskList) {
                writer.println(task.toString());
            }
        } catch (IOException e) {
            throw new StorageException();
        }
    }

    /**
     * Creates a new save file if it does not exist.
     *
     * @throws StorageException if there is an error creating the save file.
     */
    private void createSaveFile() throws StorageException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            }
            if (file.createNewFile()) {
                System.out.println("File created: " + filePath);
            }
        } catch (IOException e) {
            throw new StorageException();
        }
    }
}
