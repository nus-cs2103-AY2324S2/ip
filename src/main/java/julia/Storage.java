package julia;

import julia.task.Deadline;
import julia.task.Event;
import julia.task.Task;
import julia.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




/**
 * Storage class handles the loading and saving of tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param file The file path to save and load tasks.
     */
    public Storage(String file) {
        assert file != null : "File path cannot be null";


        this.filePath = file;
    }

    /**
     * Saves a list of tasks to the file specified in the constructor.
     *
     * @param store The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> store) {
        try {
            File file = new File(filePath);
            assert file.exists() : "File does not exist";
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile(); // Create the file if it doesn't exist
            }

            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : store) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from a saved file and constructs a Task object.
     *
     * @param line The line from the file representing a task.
     * @return The Task object constructed from the line.
     * @throws IOException If an error occurs during the parsing of the line.
     */
    public Task parseTaskFromLine(String line) throws IOException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IOException("Invalid task format. Skipping line.");
        }

        String type = parts[0];
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new IOException("Invalid deadline format. Skipping line.");
            }
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            if (parts.length < 4) {
                throw new IOException("Invalid event format. Skipping line.");
            }
            String[] eventParts = parts[3].split(" from ");
            if (eventParts.length < 2) {
                throw new IOException("Invalid event format. Skipping line.");
            }
            String start = eventParts[0];
            String end = eventParts[1];
            task = new Event(description, start, end);
            break;
        default:
            throw new IOException("Invalid task type in file. Skipping line.");
        }

        if (isDone) {
            task.setStatus();
        }

        return task;
    }


    /**
     * Loads tasks from the file specified in the constructor using the Parser class.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws DukeException If an error occurs during the loading of tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            Scanner fileScanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();

                Task task = parseTaskFromLine(line);
                tasks.add(task);

            }

            fileScanner.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
    }
}
