package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.exceptions.StorageException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Helper class to manage all storage related methods of duke.
 * This class creates a new directory in current working directory.
 * It will add a file which stores information on the current tasks.
 */
public class Storage {
    private File file;

    /** The filepath to the data file stored as a string */
    private String filePath;

    /**
     * Constructs a Storage object with initialization of directory/file if needed.
     * Verifies if data file exists, and if not, will create new file/directory.
     *
     * @param filePath String specifying desired filepath of data file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;

        try {
            // Check if the parent directory exists; if not, create it
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                if (parentDir.mkdirs()) {
                    System.out.println("Created parent directory at: " + parentDir.getAbsolutePath());
                } else {
                    throw new RuntimeException("Error creating parent directory at: " + parentDir.getAbsolutePath());
                }
            }

            // Check if file exist, if not create
            if (file.createNewFile()) {
                System.out.println("Data not found, created new file at: " + filePath);
            } else {
                System.out.println("Data found at: " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating file: " + e.getMessage(), e);
        }
    }

    /**
     * Saves contents of taskList to memory.
     * Tasks objects are stored as a string, obtained from calling task.getTokens().
     *
     * @param taskList TaskList instance to save.
     */
    public void save(TaskList taskList) {
        assert taskList != null : "Task list cannot be null";
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : taskList) {
                fw.write(task.getTokens() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads taskList from datafile and returns as a TaskList object.
     *
     * @return TaskList object.
     * @throws StorageException If file not found.
     */
    public TaskList load() throws StorageException {
        assert this.file != null : "File cannot be null";

        TaskList taskList = new TaskList();

        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                Task nexTask = parseLineFromStorage(s.nextLine());
                taskList.add(nexTask);
            }
        } catch (FileNotFoundException e) {
            throw new StorageException("File / Directory does not exist.");
        }

        return taskList;
    }

    /**
     * Parses string input and returns the Task which string represents.
     * This method rebuilds task objects from a string value.
     *
     * @param tokens String containing tokens of the task.
     * @return Task object.
     * @throws StorageException If data is corrupted and unable to be parsed.
     */
    private Task parseLineFromStorage(String tokens) throws StorageException {
        try {
            assert tokens != null : "Tokens cannot be null";
            String[] data = tokens.split(",");
            switch (data[0]) {
            case "TODO":
                return Task.createTask(TaskType.TODO, data[1], Boolean.parseBoolean(data[2]));
            case "EVENT":
                return Task.createTask(TaskType.EVENT, data[1], Boolean.parseBoolean(data[2]),
                        LocalDate.parse(data[3], Task.getDateFormat()),
                        LocalDate.parse(data[4], Task.getDateFormat()));

            case "DEADLINE":
                return Task.createTask(TaskType.DEADLINE, data[1], Boolean.parseBoolean(data[2]),
                        LocalDate.parse(data[3], Task.getDateFormat()));

            default:
                throw new StorageException("Data file is corrupted, task type does not exist");
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            throw new StorageException("Data file is corrupted, error parsing data: " + e.getMessage());
        }
    }
}
