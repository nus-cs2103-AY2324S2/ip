package blu.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import blu.exception.BluException;
import blu.task.Deadline;
import blu.task.Event;
import blu.task.Task;
import blu.task.ToDo;

/**
 * Decodes CSV strings into their associated Task objects.
 */
public class TaskDecoder {
    private static final String CSV_SEP = ",";
    private static final String MARKED_TOKEN = "T";

    /**
     * Parses CSV string to a Task object by determining its type
     *
     * @param csv The CSV string to be parsed into a Task object.
     * @return The associated Task object.
     * @throws BluException If the task type is unrecognized or if the CSV format is invalid.
     */
    public static Task fromCsv(String csv) throws BluException {
        String[] tokens = csv.split(CSV_SEP);
        String type = tokens[0];
        switch (type) {
        case "T":
            return parseToDo(tokens);
        case "D":
            return parseDeadline(tokens);
        case "E":
            return parseEvent(tokens);
        default:
            throw new BluException("Unrecognised task type");
        }
    }

    private static ToDo parseToDo(String[] tokens) throws BluException {
        try {
            ToDo todo = new ToDo(tokens[2]);
            if (tokens[1].equals(MARKED_TOKEN)) {
                todo.setMarked();
            }
            return todo;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new BluException("Invalid todo format in storage file");
        }
    }

    private static Deadline parseDeadline(String[] tokens) throws BluException {
        try {
            Deadline deadline = new Deadline(tokens[2], LocalDateTime.parse(tokens[3]));
            if (tokens[1].equals(MARKED_TOKEN)) {
                deadline.setMarked();
            }
            return deadline;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new BluException("Invalid deadline format in storage file");
        }
    }

    private static Event parseEvent(String[] tokens) throws BluException {
        try {
            Event event = new Event(tokens[2], LocalDateTime.parse(tokens[3]),
                    LocalDateTime.parse(tokens[4]));
            if (tokens[1].equals(MARKED_TOKEN)) {
                event.setMarked();
            }
            return event;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new BluException("Invalid event format in storage file");
        }
    }
}
