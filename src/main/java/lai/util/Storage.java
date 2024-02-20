package lai.util;

import lai.task.Deadline;
import lai.task.Event;
import lai.task.Task;
import lai.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages storage operations for tasks in the Lai application.
 * This includes reading from and writing tasks to a specified file.
 */
public class Storage {
    // Default file path
    protected String filePath = "data/tasks.txt";

    /**
     * Constructs a Storage object with a default file path.
     */
    public Storage() {

    }

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath The file path used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the task file with the current list of tasks.
     * If the file or directories do not exist, they will be created.
     *
     * @param tasks The list of tasks to be written to the file.
     */
    public void updateTasksFile(TaskList tasks) {
        File file = new File(this.filePath);

        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            System.out.println("Error occurred: Failed to create directory " + parentDir.getPath());
            return;
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from the storage file and returns them as a list of Task objects.
     * If the file format is incorrect, a LaiException is thrown.
     *
     * @return A list of tasks read from the file.
     */
    public List<Task> readTasksFile() {
        File file = new File(this.filePath);
        List<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                boolean isDone = line.charAt(4) == 'X';
                String description = line.substring(7);

                if (line.startsWith("[T]")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (line.startsWith("[D]")) {
                    int byIndex = description.indexOf(" (by: ");
                    String byString = description.substring(byIndex + 6, description.length() - 1);

                    tasks.add(new Deadline(description.substring(0, byIndex), isDone, byString));

                } else if (line.startsWith("[E]")) {
                    int fromIndex = description.indexOf(" (from: ");
                    int toIndex = description.indexOf(" to: ");
                    String fromString = description.substring(fromIndex + 8, toIndex);
                    String byString = description.substring(toIndex + 5, description.length() - 1);

                    tasks.add(new Event(description.substring(0, fromIndex), isDone, fromString, byString));
                } else {
                    throw new LaiException("Your file does not seem to be in the correct format.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (LaiException e) {
            System.out.println(e);
        }

        return tasks;
    }
}


