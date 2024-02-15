package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    private static final String FILE_PATH = "./data/jamie.txt";
    private final String file;

    /**
     * Constructs a new Storage object.
     *
     * @param file The path of the file where tasks are stored.
     */
    public Storage(String file) {
        assert file != null && !file.isEmpty() : "File path cannot be null or empty";
        this.file = file;
    }


    /**
     * Loads tasks from the specified file into an ArrayList.
     * If the file does not exist, an empty list is returned.
     *
     * @return An ArrayList containing the loaded Task objects.
     * @throws FileNotFoundException If the specified file does not exist.
     * @throws JamieException If an error occurs while parsing the file data.
     */
    public ArrayList<Task> load() throws FileNotFoundException, JamieException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        Scanner scanner = new Scanner(new BufferedReader(new FileReader(FILE_PATH)));
        while (scanner.hasNextLine()) {
            String taskString = scanner.nextLine();
            String[] splits = taskString.split(" \\| "); // Splitting each part of the task

            switch (splits[0]) {
            case "T": {
                Task toAdd = new ToDo(splits[2], splits[1].equals("1"));
                loadedTasks.add(toAdd);
                break;
            }
            case "E": {
                Task toAdd = new Event(splits[2], splits[3], splits[4], splits[1].equals("1"));
                loadedTasks.add(toAdd);
                break;
            }
            case "D": {
                Task toAdd = new Deadline(splits[2], splits[3], splits[1].equals("1"));
                loadedTasks.add(toAdd);
                break;
            }
            default: {
                throw new JamieException("Error occurred when reading data from storage file.\n"
                        + "Therefore, creating a new task list.");
            }
            }
        }
        scanner.close();

        return loadedTasks;
    }

    /**
     * Saves the current list of tasks to the file.
     * The tasks are converted into a specific string format before being written to the file.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(TaskList tasks) {
        assert tasks != null : "Tasks cannot be null when saving";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }


}
