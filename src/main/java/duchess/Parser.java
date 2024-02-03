package duchess;

import duchess.task.Task;
import duchess.task.Event;
import duchess.task.ToDo;
import duchess.task.Deadline;

/**
 * Parser class provides methods to parse input strings into Task objects.
 * It is responsible for parsing task information from file format strings.
 */
public class Parser {

    /**
     * Parses a task from a line read from a file.
     *
     * @param line the line read from the file
     * @return the Task object parsed from the line
     * @throws DuchessException if an error occurs during parsing
     */
    public Task parseTaskFromFileString(String line) throws DuchessException {
        Task task = null;
        // Parse the line and create task objects accordingly
        // Example line format: "T | 1 | read book"
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (type) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                String by = parts[3].trim();
                task = new Deadline(description, isDone, by);
                break;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, isDone, from, to);
                break;
            default:
                System.out.println("Unknown task type: " + type);
        }
        return task;
    }
}
