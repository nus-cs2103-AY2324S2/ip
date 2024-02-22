package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class parses the user commands in Duke.
 * It throws exceptions for invalid formats and inform how to fix it.
 *
 */
public class Parser {

    /**
     * Constructs an empty Parser instance.
     */
    public Parser() {
    }

    /**
     * Parses the marking command from the main class.
     *
     * @param order     The user command.
     * @param totalnum  The total number of tasks.
     * @return The task number to mark as done.
     * @throws DukeException If the command format is invalid and tell how to fix.
     */
    public int parseMark(String order, int totalnum) throws DukeException {
        assert order != null : "Order should not be null";
        try { 
            String[] actions = order.split(" "); 
            int markNum = Integer.parseInt(actions[1]);
            if (actions[1].isEmpty() || markNum > totalnum || markNum < 1) {
                throw new IndexOutOfBoundsException();
            } else {
                int number = Integer.parseInt(actions[1]);
                return number;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("write in valid/correct format: mark + [task number]");
        }
    }

    /**
     * Parses the deleting command from the user.
     *
     * @param order     The user command.
     * @param totalnum  The total number of tasks.
     * @return The task number to delete.
     * @throws DukeException If the command format is invalid and tell how to fix.
     */
    public int parseDelete(String order, int totalnum) throws DukeException{
        assert order != null : "Order should not be null";
        try {
            String[] actions = order.split(" ");
            int deleteNum = Integer.parseInt(actions[1]);
            if (actions[1].isEmpty() || deleteNum > totalnum || deleteNum < 1) {
                throw new IndexOutOfBoundsException();
            } else {
                int number = Integer.parseInt(actions[1]);
                return number;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("write in valid/correct format: delete + [task number]");
        }
    }


    /**
     * Parses the unmarking command from the user.
     *
     * @param order     The user command.
     * @param totalnum  The total number of tasks.
     * @return The task number to unmark.
     * @throws DukeException If the command format is invalid and tell how to fix.
     */
    public int parseUnmark(String order, int totalnum) throws DukeException {
        assert order != null : "Order should not be null";
        try { 
            String[] actions = order.split(" "); 
            int unmarkNum = Integer.parseInt(actions[1]);
            if (actions[1].isEmpty() || unmarkNum > totalnum || unmarkNum < 1) {
                throw new IndexOutOfBoundsException();
            } else {
                int number = Integer.parseInt(actions[1]);
                return number;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("write in valid/correct format: unmark + [task number]");
        }
    }

    public String parseFind(String order) throws DukeException {
        assert order != null : "Order should not be null";
        try { 
            String[] actions = order.split(" "); 
            String word = actions[1];
            if (actions[1].isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else {
                return word;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("write in valid/correct format: find + [keyword]");
        }
    }

    /**
     * Parses the todo command from the user.
     *
     * @param order The user command.
     * @return The created Todo task from the command.
     * @throws DukeException If the command format is invalid and tell how to fix.
     */
    public Todo parseTodo(String order) throws DukeException {
        assert order != null : "Order should not be null";
        try {
            String t = order.substring(4).trim();
            if (t.isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else {
                Todo todo = new Todo(t);
                return todo;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("write in the correct format: todo + [content]");
        }
    }

    /**
     * Parses the deadline command from the user.
     *
     * @param order The user command.
     * @return The created Deadline task from the command.
     * @throws DukeException If the command format is invalid and tell how to fix.
     */
    public Deadline parseDeadline(String order) throws DukeException {
        assert order != null : "Order should not be null";
        try {
            int byIndex = order.indexOf("/by");
            String t = order.substring(9, byIndex - 1);
            String due = order.substring(byIndex + 4);

            LocalDateTime dueFormat = LocalDateTime.parse(due, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

            if (t.isEmpty() || due.isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else {
                Deadline task = new Deadline(t, dueFormat);
                return task;
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("write in the correct format: deadline + [content] + /by + [duedate]");
        }
    }

    /**
     * Parses the event command from the user.
     *
     * @param order The user command.
     * @return The created Event task from the command.
     * @throws DukeException If the command format is invalid and tell how to fix.
     */
    public Event parseEvent(String order) throws DukeException {
        assert order != null : "Order should not be null";
        try {
            int fromIndex = order.indexOf("/from");
            int toIndex = order.indexOf("/to");
            String t = order.substring(6, fromIndex - 1);
            String from = order.substring(fromIndex + 6, toIndex - 1);
            String to = order.substring(toIndex + 4);

            LocalDateTime fromFormat = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            LocalDateTime toFormat = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

            if (t.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else {
                Event event = new Event(t, fromFormat, toFormat);
                return event;
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("\"write in the correct format: event + [content] + /from + [startingdate] + /to + [enddate]");
        }
    }
}
