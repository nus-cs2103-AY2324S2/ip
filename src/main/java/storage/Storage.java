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
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path and TaskList.
     * @param filePath the file path of the file to be used for storing tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the tasks to the file and adds them to the TaskList representation.
     * @throws IOException if an I/O error occurs while reading the file
     */
    public void writeToFile(TaskList taskList) throws IOException {
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

    /**
     * Loads the tasks from the file and adds them to the TaskList representation.
     * @return the TaskList representation of the tasks in the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public TaskList loadTaskList() throws IOException {
        TaskList taskList = new TaskList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String type = line.substring(1, 2);
                boolean isDone = line.substring(4, 5).equals("X");
                String description;
                String by = null;
                String from = null;
                String to = null;
                if (type.equals("T")) {
                    description = line.substring(7);
                } else if (type.equals("D")) {
                    description = line.substring(7, line.indexOf("(") - 1);
                    by = line.substring(line.indexOf("(") + 5, line.indexOf(")"));
                } else { // type.equals("E")
                    description = line.substring(7, line.indexOf("(") - 1);
                    from = line.substring(line.indexOf("(") + 6, line.indexOf(" to "));
                    to = line.substring(line.indexOf(" to ") + 4, line.indexOf(")"));
                }
                if (type.equals("T")) {
                    taskList.addTask(new Task(description, isDone));
                } else if (type.equals("D")) {
                    taskList.addTask(new Task(description, isDone, by));
                } else if (type.equals("E")) {
                    taskList.addTask(new Task(description, isDone, from, to));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading list from file.");
        } catch (NullPointerException e) {
            return new TaskList();
        }
        return taskList;
    }
}
