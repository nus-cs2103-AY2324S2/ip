package Dude;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Provides functionality to save and load tasks to and from a file.
 * This class handles the conversion of Task objects into a string format
 * suitable for file storage
 * and the parsing of these strings back into Task objects.
 */
class TaskStorage {
    public static final String FILE_NAME = "tasks.txt";

    /**
     * Saves a list of tasks to a file, converting each task to a string format.
     * Each task is written on a new line in the file.
     *
     * @param tasks The list of Task objects to be saved.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Task task : tasks) {
            String taskString = task.toFileString();
            if (!taskString.isEmpty()) {
                bufferedWriter.write(taskString);
                bufferedWriter.newLine();
            }
        }

    }

    /**
     * Loads a list of tasks from a file, converting each line of the file back into
     * a Task object.
     *
     * @return An ArrayList of Task objects read from the file.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public static ArrayList<Task> loadTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Task task = taskFromString(line);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * Converts a string representation of a task back into a Task object.
     * The string format should include task type, completion status, description,
     * and dates if applicable.
     *
     * @param line The string representation of the task.
     * @return A Task object corresponding to the string, or null if the string
     *         format is invalid.
     */
    private static Task taskFromString(String line) {
        String[] parts = line.split("\\|");
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            switch (parts[0]) {
                case "T":
                    ToDo todo = new ToDo(description);
                    if (isDone)
                        todo.markAsDone();
                    return todo;
                case "D":
                    Deadline deadline = new Deadline(description, parts[3]);
                    if (isDone)
                        deadline.markAsDone();
                    return deadline;
                case "E":
                    Event event = new Event(description, parts[3], parts[4]);
                    if (isDone)
                        event.markAsDone();
                    return event;
                default:
                    return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}