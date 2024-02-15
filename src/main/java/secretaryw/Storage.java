package secretaryw;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the storage of tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage class.
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     * @return An ArrayList containing the loaded tasks.
     * @throws FileNotFoundException If the file specified by filePath does not exist.
     */
    public ArrayList<Task> loadTasksFromFile() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String taskData = fileScanner.nextLine();
                Task task = createTaskFromData(taskData);
                taskList.add(task);
            }
            fileScanner.close();
        }
        return taskList;
    }

    /**
     * Saves tasks to the file.
     * @param taskList The ArrayList of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskList) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
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
        } else if (type.equals("D")) {
            String by = parts[3].trim();
            return new Task(TaskType.DEADLINE, description, by, isDone);
        } else if (type.equals("E")) {
            String from = parts[3].trim();
            String to = parts[4].trim();
            return new Task(TaskType.EVENT, description, from, to, isDone);
        } else {
            // Handle other types if needed
            return null;
        }
    }
}
