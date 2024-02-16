package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Handles the storage of task data to and from a file.
 * This includes loading tasks from a file when the program starts
 * and saving tasks to the file when they are added, deleted, or modified.
 */
public class Storage {
    private final String file;

    /**
     * Constructs a new Storage object with the specified file path for storing tasks.
     *
     * @param file The path of the file where tasks are stored.
     */
    public Storage(String file) {
        assert file != null && !file.isEmpty() : "File path cannot be null or empty";
        this.file = file;
    }

    /**
     * Loads tasks from the specified file into a list.
     * If the file does not exist, an empty list is returned.
     *
     * @return A list containing the loaded Task objects.
     * @throws JamieException If an error occurs while parsing the file data.
     */
    public List<Task> load() throws JamieException {
        List<Task> loadedTasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(this.file)))) {
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                String[] splits = taskString.split(" \\| "); // Splitting each part of the task
                switch (splits[0]) {
                case "T":
                    Task toDo = new ToDo(splits[2], "1".equals(splits[1]));
                    loadedTasks.add(toDo);
                    break;
                case "E":
                    Task event = new Event(splits[2], splits[3], splits[4], "1".equals(splits[1]));
                    loadedTasks.add(event);
                    break;
                case "D":
                    Task deadline = new Deadline(splits[2], splits[3], "1".equals(splits[1]));
                    loadedTasks.add(deadline);
                    break;
                default:
                    throw new JamieException("Error occurred when reading data from storage file: "
                            + "Unrecognized task type.");
                }
            }
        } catch (FileNotFoundException e) {
            // If file not found, consider it as starting fresh with no tasks
            // Alternatively, could throw new JamieException to inform the user or take other actions
            System.out.println("No existing task file found. Starting with an empty task list.");
        }
        return loadedTasks;
    }

    /**
     * Saves the current list of tasks in the TaskList to the file.
     * The tasks are converted into a specific string format before being written to the file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(TaskList taskList) throws IOException {
        assert taskList != null : "TaskList cannot be null when saving";
        List<Task> tasks = taskList.getTasks(); // Retrieve the list of tasks from TaskList
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        }
        // Note: IOException is thrown to the caller to handle as this could be a user-critical error.
    }
}
