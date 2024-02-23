package lindi.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import lindi.task.CreateTaskException;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents the storage unit for the application.
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private FileWriter fileWriter;
    private Scanner fileScanner;

    private final String dataDir;
    private final String dataFileName;
    /**
     * Initialises Storage to load data from and save data to a file. Creates
     * the required directories and files if necessary.
     *
     * @param dataDir directory to store the data file
     * @param dataFileName name of data file (.txt format)
     */
    public Storage(String dataDir, String dataFileName) {
        this.dataDir = dataDir;
        this.dataFileName = dataFileName;
    }

    /**
     * Saves the task list to the designated text file for storing data for the program.
     *
     * @param tasks task list to save to file
     */
    public void saveToFile(TaskList tasks) {
        String parsedTasks = getParsedTasksToSave(tasks);
        try {
            File dataFile = getSaveFile();

            this.fileWriter = new FileWriter(dataFile);
            this.fileWriter.write(parsedTasks);
            this.fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the file to save the task list to. Creates the directories and file if they do not exist yet.
     *
     * @return the file to save the task list to
     * @throws IOException if file creation fails
     */
    private File getSaveFile() throws IOException {
        // Creates the directories if they do not exist yet. No effect if it exists.
        File dir = new File(dataDir);
        dir.mkdirs();

        // Creates the file if it does not exist yet. No effect if it exists.
        File dataFile = new File(dir, dataFileName);
        dataFile.createNewFile();

        return dataFile;
    }

    /**
     * Constructs the parsed string representation of the all the tasks in the task list to save to file
     *
     * @param tasks task list to save to file
     * @return the parsed string representation of the tasks in the task list
     */
    private String getParsedTasksToSave(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        // Loops through taskList, appends them to string with the specified format
        tasks.getTasks().forEachRemaining(
                task -> {
                    String parsedTask = task.toParsedFormat();
                    sb.append(parsedTask).append('\n');
                }
        );
        return sb.toString();
    }

    /**
     * Loads the taskList from the data file for this session
     *
     * @throws StorageLoadException if data file not exist at file path
     */
    public TaskList loadFromFile() throws StorageLoadException {
        try {
            this.fileScanner = new Scanner(new File(dataDir, dataFileName));
            TaskList tasksToReturn = new TaskList();
            addTasksFromScanner(tasksToReturn);
            return tasksToReturn;
        } catch (CreateTaskException e) {
            throw new StorageLoadException("Error loading tasks from save file. Might be corrupted...\n"
                    + e.getMessage());
        } catch (FileNotFoundException e) {
            throw new StorageLoadException("Error loading tasks from save file. "
                    + "File not found at path specified...\n" + new File(dataDir, dataFileName));
        }
    }

    /**
     * Populates the task list with data file.
     *
     * @param taskList task list to add tasks to
     * @throws CreateTaskException if parsing of task from file fails
     */
    private void addTasksFromScanner(TaskList taskList) throws CreateTaskException {
        assert fileScanner != null : "File scanner should be created before calling this method";
        while (fileScanner.hasNextLine()) {
            String parsedString = fileScanner.nextLine();
            Task task = Task.createFromParsedString(parsedString);
            assert task != null : "Task created should not be null";
            taskList.add(task);
        }
    }
}
