package friday.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import friday.task.Task;
import friday.task.TaskList;

/**
 * The Storage class handles the reading and writing of task data to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where task data is stored.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path must not be null";
        this.filePath = filePath;
    }

    /**
     * Checks if the data file exists; if not, creates a new file.
     *
     * @throws IOException If an I/O error occurs during file creation.
     */
    public void checkFile() throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            if (f.createNewFile()) {
                System.out.println("Data file created.");
            } else {
                System.out.println("Error creating data file.");
            }
        } else {
            System.out.println("Loading data from Duke.txt...");
        }
    }

    /**
     * Writes the task list data to the file.
     *
     * @param tasks The TaskList containing tasks to be written to the file.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        assert tasks != null : "TaskList must not be null";
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < tasks.getLength(); i++) {
            fw.write(tasks.getTask(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Reads task data from the file and returns a TaskList.
     *
     * @return The TaskList containing tasks loaded from the file.
     * @throws IOException If an I/O error occurs during file reading.
     */
    public TaskList loadDataFromFile() throws IOException {
        File file = new File(filePath);
        TaskList tasks = new TaskList(filePath);

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                if (!taskData.isEmpty()) {
                    Task task = Task.parseTask(taskData);
                    if (task != null) {
                        tasks.addTaskFromData(task);
                    }
                }
            }
        }
        if (tasks.isEmpty()) {
            tasks = new TaskList(filePath);
        }
        return tasks;
    }

    /**
     * Appends text to the end of the file.
     *
     * @param textToAppend The text to append to the file.
     * @throws IOException If an I/O error occurs during file appending.
     */
    public void appendToFile(String textToAppend) throws IOException {
        assert textToAppend != null : "Text to append must not be null";
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
