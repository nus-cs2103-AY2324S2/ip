package cvb.tasks;

import cvb.exceptions.ConvoBotException;
import cvb.utils.DateTime;

/**
 * The {@code TaskParser} class provides utility methods for parsing tasks from the user input.
 */
public class TaskParser {

    /**
     * Parses a line of text and returns the corresponding {@code Task} object.
     *
     * @param line the line of text representing a task
     * @return the corresponding {@code Task} object
     * @throws IllegalArgumentException if the line is in an invalid format
     */
    public static Task parse(String line) throws IllegalArgumentException {
        final int minPartsLength = 3;
        final int maxPartsLength = 5;
        final IllegalArgumentException invalidLineException =
                new IllegalArgumentException("Invalid line format: " + line);

        String[] parts = line.split(" \\| ");
        if (parts.length < minPartsLength || parts.length > maxPartsLength) {
            throw invalidLineException;
        }
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;
        try {
            switch (parts[0]) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                task = new Deadline(description, isDone, DateTime.stringToDate(parts[3]));
                break;
            case "E":
                task = new Event(description, isDone, DateTime.stringToDate(parts[3]),
                                                      DateTime.stringToDate(parts[4]));
                break;
            default:
                throw invalidLineException;
            }
        } catch (ConvoBotException e) {
            throw invalidLineException;
        }
        return task;
    }
}
