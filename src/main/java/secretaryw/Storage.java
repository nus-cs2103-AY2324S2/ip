package secretaryw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the storage of tasks to and from a file.
 */
public class Storage {
    private static final String FILE_PATH = "secretary.txt";

    /**
     * Constructor for Storage class.
     */
    public Storage() {
        File file = new File(Storage.FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     * @return An ArrayList containing the loaded tasks.
     * @throws FileNotFoundException If the file specified by filePath does not exist.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String taskData = fileScanner.nextLine();
                    Task task = createTaskFromData(taskData);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error reading tasks from file.");
            }
        }
        return taskList;
    }

    /**
     * Saves tasks to the file.
     * @param taskList The ArrayList of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) throws IOException {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            for (Task task : taskList) {
                fileWriter.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Creates a Task object from the given task data string.
     * @param taskData The string containing task data in a specific format.
     * @return The Task object created from the task data.
     */
    private Task createTaskFromData(String taskData) {
        String[] parts = taskData.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        if (type.equals("T")) {
            return new Task(TaskType.TODO, description, isDone);
        } else if (type.equals("D") || type.equals("E")) {
            if (parts.length < 4) {
                return null;
            }
            String by = parts[3].trim();
            if (type.equals("D")) {
                return new Task(TaskType.DEADLINE, description, by, isDone);
            } else {
                if (parts.length < 5) {
                    return null;
                }
                String to = parts[4].trim();
                return new Task(TaskType.EVENT, description, by, to, isDone);
            }
        }
        // Handle other types if needed
        return null;
    }
}
