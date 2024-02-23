package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Handles loading from and saving to the task list file.
 * This class is responsible for file operations, including reading tasks from
 * a file and writing tasks back to the file.
 */
public class Storage {

    private TaskList taskList;

    /**
     * Constructs a Storage object with a reference to a TaskList.
     *
     * @param taskList The TaskList to be managed by this Storage.
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Updates the TaskList managed by this Storage.
     *
     * @param tasks The new TaskList to be managed.
     */
    public void setupTaskList(TaskList tasks) {
        this.taskList = tasks;
    }

    /**
     * Loads tasks from the predefined file into the TaskList.
     * Reads the file line by line, creating tasks from the file format
     * and adding them to the TaskList. If the file does not exist,
     * it is created.
     */
    protected void loadTasks() {
        File file = new File(Ursa.DATA_FILE);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Parser.createTaskFromFile(line);
                this.taskList.addTask(task);
            }
        } catch (FileNotFoundException e) {
            ErrorHandler.handleFileNotFoundException(Ursa.DATA_FILE);
        }
    }

    /**
     * Saves the current tasks in the TaskList to the predefined file.
     * Writes each task to the file in a format suitable for loading back.
     */
    protected void saveTasks() {
        try (PrintWriter writer = new PrintWriter(Ursa.DATA_FILE)) {
            for (Task task : this.taskList.getTasks()) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            ErrorHandler.handleIOException();
        }
    }
}