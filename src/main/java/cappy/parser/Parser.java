package cappy.parser;

import static cappy.constant.Message.INVALID_STORAGE_FORMAT;

import cappy.command.CommandType;
import cappy.error.CappyException;
import cappy.task.Deadline;
import cappy.task.Event;
import cappy.task.Task;
import cappy.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Handles parsing user input and converting data formats.
 *
 * <p>The {@code Parser} class provides static methods for parsing user input, converting date-time
 * strings to LocalDateTime objects, and parsing CSV lines into corresponding task objects.
 */
public class Parser {
    /** The DateTimeFormatter for parsing and formatting date-time strings. */
    public static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Parses a input String into a ParsedInput object.
     *
     * @param input The String to be parsed.
     * @return The parsed input as a ParsedInput object.
     * @throws CappyException If the input is not valid.
     */
    public static ParsedInput parse(String input) throws CappyException {
        StringTokenizer st = new StringTokenizer(input, " ");
        int numTokens = st.countTokens();
        if (numTokens == 0) {
            return new ParsedInput(CommandType.EMPTY, new HashMap<>(), new ArrayList<>());
        }
        String commandString = st.nextToken();
        CommandType command = CommandType.fromString(commandString);
        if (command == CommandType.INVALID || command == CommandType.EMPTY) {
            return new ParsedInput(command, new HashMap<>(), new ArrayList<>());
        }
        HashMap<String, String> namedArguments = new HashMap<>();
        ArrayList<String> positionalArguments = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.startsWith("/")) {
                if (!st.hasMoreTokens()) {
                    throw new CappyException("Missing value for argument " + token);
                }
                namedArguments.put(token.substring(1), st.nextToken());
            } else {
                positionalArguments.add(token);
            }
        }
        return new ParsedInput(command, namedArguments, positionalArguments);
    }

    /**
     * Converts a String into a LocalDateTime object. The expected format is yyyy-MM-dd'T'HH:mm. For
     * example, 2023-01-29T14:00.
     *
     * @param input The string to be converted to a LocalDateTime object.
     * @return The resulting LocalDateTime object from the conversion.
     * @throws DateTimeParseException If the String is not in the expected format.
     */
    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DATE_TIME_FORMAT);
    }

    /**
     * Converts a LocalDateTime object into a String. The String format is yyyy-MM-dd'T'HH:mm. For
     * example, 2023-01-29T14:00.
     *
     * @param dateTime The LocalDateTime object to be converted to a String.
     * @return The resulting String from the conversion.
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }

    /**
     * Parses a csv line into a Task object.
     *
     * @param csvLine The csv line to be parsed.
     * @return The Task object created from the data in the csv line.
     * @throws CappyException If the csv line is not in the excpected format.
     */
    public static Task parseCsvLine(String csvLine) throws CappyException {
        String[] data = csvLine.split(",");
        try {
            String type = data[0];
            boolean isDone = data[1].equals("1");
            String description = data[2];
            if (type.equals(Todo.TYPE_SYMBOL)) {
                return new Todo(description, isDone);
            } else if (type.equals(Deadline.TYPE_SYMBOL)) {
                LocalDateTime due = parseDateTime(data[3]);
                return new Deadline(description, isDone, due);
            } else if (type.equals(Event.TYPE_SYMBOL)) {
                LocalDateTime from = parseDateTime(data[3]);
                LocalDateTime to = parseDateTime(data[4]);
                return new Event(description, isDone, from, to);
            } else {
                throw new CappyException("Invalid Task Type detected in storage!");
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new CappyException(INVALID_STORAGE_FORMAT);
        }
    }
}
