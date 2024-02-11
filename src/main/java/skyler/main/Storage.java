package skyler.main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import skyler.task.Deadline;
import skyler.task.Event;
import skyler.task.Task;
import skyler.task.ToDo;
import skyler.exception.SkylerException;

public class Storage {
    private static final String FILE_PATH = "./data/Skyler.txt";
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Loads tasks from the specified file path and adds them to the common tasks
     * list.
     * Assumes the file format is compatible with the task parsing logic in
     * parseTaskFromFile method.
     */
    public static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Task task = parseTaskFromFile(data);
                tasks.add(task); // Add the task to the common list
            }
            scanner.close();
        } catch (IOException | SkylerException e) {
            System.out.println("An error occurred while loading tasks.");
            e.printStackTrace();
        }
    }

    /**
     * Parses a task from the given data string, determining the task type (ToDo,
     * Deadline, Event)
     * and creating the corresponding Task object.
     *
     * @param data The string containing the task data in a specific format.
     * @return The Task object parsed from the data string.
     * @throws SkylerException If there is an error parsing the task or if the task
     *                         type is unknown.
     */
    public static Task parseTaskFromFile(String data) throws SkylerException {

        assert data != null : "The 'data' parameter should not be null.";
        String taskType = data.substring(1, 2); // Extracting task type (T, D, E)
        boolean isDone = data.charAt(4) == 'X'; // Assuming 'x' represents a completed task
        String details = data.substring(7).trim(); // Extracting task details

        switch (taskType) {
            case "T":
                return new ToDo(details, isDone);
            case "D":
                int byIndex = details.indexOf("(by:");
                String descriptionD = details.substring(0, byIndex).trim();
                String byString = details.substring(byIndex + 4, details.length() - 1).trim();
                LocalDate byDate = LocalDate.parse(byString, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                return new Deadline(descriptionD, byDate, isDone);
            case "E":
                int fromIndex = details.indexOf("(from:");
                int toIndex = details.indexOf("to:");
                String descriptionE = details.substring(0, fromIndex).trim();
                String fromString = details.substring(fromIndex + 6, toIndex).trim();
                String toString = details.substring(toIndex + 3, details.length() - 1).trim();
                LocalDate fromDate = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                LocalDate toDate = LocalDate.parse(toString, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                return new Event(descriptionE, fromDate, toDate, isDone);
            default:
                throw new SkylerException("Unknown task type in the file: " + data);
        }
    }

    /**
     * Saves the tasks from the common tasks list to the specified file path.
     * Assumes the tasks have a valid toString method for serialization.
     */
    public static void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the common tasks list.
     *
     * @return The list of tasks stored in the common tasks list.
     */
    // Add this method to get the common tasks list for TaskList.java
    public static List<Task> getTasks() {
        return tasks;
    }
}
