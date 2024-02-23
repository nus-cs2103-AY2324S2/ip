package hal.codec;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

import hal.exceptions.ProcessingException;
import hal.tasks.Deadline;
import hal.tasks.Event;
import hal.tasks.Task;
import hal.tasks.Todo;

/**
 * The `Codec` class provides methods for encoding and decoding tasks.
 */
public class Codec {

    private static final String DELIMITER = ", ";

    /**
     * Encodes a task into a string representation.
     *
     * @param task The task to be encoded.
     * @return A string representation of the encoded task.
     */
    public String encode(Task task) {
        String[] encodedTask = task.encode();
        return buildString(encodedTask);
    }


    /**
     * Builds a concatenated string from an array of string values using a specified delimiter.
     *
     * @param values The array of values to be joined.
     * @return A concatenated string with values separated by the specified delimiter.
     */
    public String buildString(String... values) {
        StringJoiner encodedString = new StringJoiner(DELIMITER);
        for (String string : values) {
            encodedString.add(string);
        }

        return encodedString.toString();
    }

    /**
     * Decodes a string representation of a task into a Task object.
     *
     * @param string The string representation of the task to be decoded.
     * @return A Task object representing the decoded task.
     * @throws ProcessingException If there is an error while decoding the task.
     */
    public Task decode(String string) throws ProcessingException {
        String[] splitString = string.split(DELIMITER);
        switch (splitString[0]) {
        case "D":
            return decodeDeadline(splitString);
        case "T":
            return decodeTodo(splitString);
        case "E":
            return decodeEvent(splitString);
        default:
            throw new ProcessingException("Error while decoding identifier");
        }
    }

    /**
     * Decodes a string representation of a Deadline task into a Deadline object.
     *
     * @param splitString The array of split values from the encoded Deadline string.
     * @return A Deadline object representing the decoded Deadline task.
     * @throws ProcessingException If there is an error while decoding the Deadline task.
     */
    public Task decodeDeadline(String[] splitString) throws ProcessingException {
        try {
            Boolean done = Boolean.parseBoolean(splitString[1]);
            String taskName = splitString[2];
            LocalDateTime by = TimeProcessor.fromString(splitString[3]);
            return new Deadline(taskName, by, done);

        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new ProcessingException("Error while decoding Deadline", e);

        }
    }

    /**
     * Decodes a string representation of an Event task into an Event object.
     *
     * @param splitString The array of split values from the encoded Event string.
     * @return An Event object representing the decoded Event task.
     * @throws ProcessingException If there is an error while decoding the Event task.
     */
    public Task decodeEvent(String[] splitString) throws ProcessingException {
        try {
            Boolean done = Boolean.parseBoolean(splitString[1]);

            String taskName = splitString[2];
            LocalDateTime from = TimeProcessor.fromString(splitString[3]);
            LocalDateTime to = TimeProcessor.fromString(splitString[4]);

            return new Event(taskName, from, to, done);

        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new ProcessingException("Error while decoding Event", e);
        }
    }

    /**
     * Decodes a string representation of a Todo task into a Todo object.
     *
     * @param splitString The array of split values from the encoded Todo string.
     * @return A Todo object representing the decoded Todo task.
     * @throws ProcessingException If there is an error while decoding the Todo task.
     */
    public Task decodeTodo(String[] splitString) throws ProcessingException {
        try {
            Boolean done = Boolean.parseBoolean(splitString[1]);
            String taskName = splitString[2];
            return new Todo(taskName, done);

        } catch (IndexOutOfBoundsException e) {
            throw new ProcessingException("Error while decoding Todo", e);

        }
    }
}
