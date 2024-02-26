package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tasks.Task;
import tasks.TaskList;

/**
 * The Storage class is responsible for reading and writing tasks to the file.
 */
public class Storage {
    private final String filePath;
    private TaskList taskList;

    /**
     * Constructs a Storage object with the specified file path and TaskList.
     * @param filePath the file path of the file to be used for storing tasks
     * @param taskList the TaskList object to be used for storing tasks
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    /**
     * Writes the tasks to the file and adds them to the TaskList representation.
     * @throws IOException if an I/O error occurs while reading the file
     */
    public void writeToFile() throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList.getTaskList()) {
                writer.write(task.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {

            System.out.println("Error writing list to file.");
            System.out.println("Error writing to file.");

        }
    }

    /**
     * Generates a String representation of the tasks in the TaskList.
     * @return a String representation of the tasks in the TaskList
     * @throws IOException if an I/O error occurs while generating the String representation
     */
    public String generateTasks() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder tasks = new StringBuilder();
        int i = 1;
        while ((line = reader.readLine()) != null) {
            tasks.append(i++ + ". ").append(line).append("\n");
        }
        reader.close();
        return tasks.toString();
    }
}
