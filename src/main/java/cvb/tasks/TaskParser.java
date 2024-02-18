package cvb.tasks;

import cvb.exceptions.ConvoBotException;
import cvb.utils.DateTime;

/**
 * The {@code TaskParser} class provides utility methods for parsing tasks from the user input.
 */
public class TaskParser {

    private static Task getTask(String[] parts) throws ConvoBotException {
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        switch (parts[0]) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            return new Deadline(description, isDone, DateTime.stringToDate(parts[3]));
        case "E":
            return new Event(description, isDone, DateTime.stringToDate(parts[3]),
                                                  DateTime.stringToDate(parts[4]));
        default:
            throw new ConvoBotException("Invalid task identifier");
        }
    }

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

        line = line.trim(); // remove leading and trailing spaces
        String[] parts = line.split(" \\| ");
        if (parts.length < minPartsLength || parts.length > maxPartsLength) {
            throw invalidLineException;
        }

        try {
            return getTask(parts);
        } catch (ConvoBotException e) {
            throw invalidLineException;
        }
    }
}
