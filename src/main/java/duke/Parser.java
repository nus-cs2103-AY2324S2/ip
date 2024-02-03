package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private String order;

    public Parser (String order) {
        this.order = order;
    }

    public int parseMark(int totalnum) throws DukeException {
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

    public int parseDelete(int totalnum) throws DukeException{
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

    public int parseUnmark(int totalnum) throws DukeException {
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

    public Todo parseTodo() throws DukeException {
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

    public Deadline parseDeadline() throws DukeException {
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

    public Event parseEvent() throws DukeException {
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
