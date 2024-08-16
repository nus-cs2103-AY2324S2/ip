package sylvia.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a parser that parses a task list from a file.
 */
public class TaskListParser {
    /**
     * Parses a task list from a file.
     *
     * @param file The file to parse from.
     * @return The parsed task list.
     * @throws IOException                  If an I/O error occurs.
     * @throws InvalidDataFormatException   If the data file is not in the correct
     *                                      format.
     * @throws SylviaDateTimeParseException If the date and time is not in the
     *                                      correct format.
     */
    public static TaskList parse(File file)
            throws IOException, InvalidDataFormatException, SylviaDateTimeParseException {
        TaskList list = new TaskList();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line;
        while ((line = reader.readLine()) != null) {
            list.addTask(parseTask(line));
        }
        fr.close();
        reader.close();
        return list;
    }

    /**
     * Parses a task from a line in the data file.
     *
     * @param line The line to parse from.
     * @return The parsed task.
     * @throws InvalidDataFormatException   If the line is not in the correct
     *                                      format.
     * @throws SylviaDateTimeParseException If the date and time is not in the
     *                                      correct format.
     */
    private static Task parseTask(String line) throws InvalidDataFormatException, SylviaDateTimeParseException {
        String[] words = line.split(" \\| ");
        if (words.length < 3) {
            throw new InvalidDataFormatException("Invalid task format: " + line, "Invalid task format: " + line);
        }
        boolean isDone = words[1].equals("1");
        switch (words[0]) {
        case "T":
            return new Todo(words[2], isDone);
        case "D":
            if (words.length < 4) {
                throw new InvalidDataFormatException("Invalid task format: " + line, "Invalid task format: " + line);
            }
            return new Deadline(words[2], words[3], isDone);
        case "E":
            if (words.length < 5) {
                throw new InvalidDataFormatException("Invalid task format: " + line, "Invalid task format: " + line);
            }
            return new Event(words[2], words[3], words[4], isDone);
        default:
            throw new InvalidDataFormatException("Unknown task type: " + words[0], "Unknown task type: " + words[0]);
        }
    }

    /**
     * Serializes a task list into a string. The serialized string will be stored in
     * the data file.
     *
     * @param list The task list to serialize.
     * @return The serialized string.
     */
    public static String serialize(TaskList list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(serializeTask(list.get(i)) + "\n");
        }
        return sb.toString();
    }

    private static String serializeTask(Task task) {
        return task.serialize();
    }
}
