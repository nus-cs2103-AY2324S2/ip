package wis.util;

import java.time.LocalDateTime;
import java.util.InputMismatchException;

import wis.task.Todo;
import wis.task.Task;
import wis.task.Deadline;
import wis.task.Event;

/**
 * Parser for parsing stored file data.
 */
public class FileDataParser extends Parser {
    /**
     * Parses a line in a stored file to obtain the task it represents.
     *
     * @param line Line in the stored file to be parsed.
     * @return Task represented by the line.
     * @throws InputMismatchException  If parsing fails as the line has
     * unknown format.
     */
    public static Task parseLine(String line) throws InputMismatchException {
        String[] words = line.split("#!#");
        switch (words[0]) {
        case "T":
            return FileDataParser.parseTodo(words);
        case "D":
            return FileDataParser.parseDeadline(words);
        case "E":
            return FileDataParser.parseEvent(words);
        default:
            throw new InputMismatchException();
        }
    }

    private static Todo parseTodo(String[] words) {
        Todo todo = new Todo(words[2]);
        if (words[1].equals("1")) {
            todo.setDone();
        }
        return todo;
    }

    private static Deadline parseDeadline(String[] words) {
        Deadline deadline = new Deadline(words[2],
                LocalDateTime.parse(words[3]));
        if (words[1].equals("1")) {
            deadline.setDone();
        }
        return deadline;
    }

    private static Event parseEvent(String[] words) {
        Event event = new Event(words[2],
                LocalDateTime.parse(words[3]),
                LocalDateTime.parse(words[4]));
        if (words[1].equals("1")) {
            event.setDone();
        }
        return event;
    }
}
