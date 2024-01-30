import entity.Deadline;
import entity.Event;
import entity.ToDo;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import exception.UnknownCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    enum Command {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,

    }
    public Deadline parseDeadline(String[] arr) throws InvalidDeadlineException {
        String deadline_title = "";
        String dateTime = "";
        String combinedWord = "";

        for (int i = 1; i < arr.length; i++) {
            combinedWord += arr[i] + " ";
        }

        int index = combinedWord.indexOf("/by");

        if (index != -1) {
            dateTime = combinedWord.substring(index + 3).trim();
            deadline_title = combinedWord.substring(0, index);
        } else {
            if (deadline_title.isEmpty() && dateTime.isEmpty()) {
                throw new InvalidDeadlineException("OOPS!!! Thedescription and date of a Deadline cannot be empty.");
            } else if (deadline_title.isEmpty()) {
                throw new InvalidDeadlineException("OOPS!!! The description of a Deadline cannot be empty.");
            } else if (dateTime.isEmpty()) {
                throw new InvalidDeadlineException("OOPS!!! The date of a Deadline cannot be empty.");
            }
        }

        LocalDateTime date = dateTimeParser(dateTime);

        return new Deadline(deadline_title, date);
    }

    public ToDo parseTodo(String[] arr) throws InvalidTodoException {
        String todo_title = "";

        if (arr.length - 1 == 0) {
            throw new InvalidTodoException("OOPS!!! The description of a Todo cannot be empty.");
        }

        for (int i = 1; i < arr.length; i++) {
            todo_title += arr[i];
            if (i != arr.length - 1) {
                todo_title += " ";
            }
        }

        return new ToDo(todo_title);
    }

    public Event parseEvent(String[] splitWords) throws InvalidEventException {
        String combinedWord = "";
        for (int i = 1; i < splitWords.length; i++) {
            combinedWord += splitWords[i] + " ";
        }
        int indexFrom = combinedWord.indexOf("/from");
        int indexTo = combinedWord.indexOf("/to");

        if (splitWords.length - 1 == 0) {
            throw new InvalidEventException("OOPS!!! The description of an Event cannot be empty.");
        } else if (indexFrom < 0 || indexTo < 0) {
            throw new InvalidEventException("OOPS!!! The date of an Event cannot be empty.");
        }

        String beforeFrom = combinedWord.substring(0, indexFrom).trim();
        String afterFrom = combinedWord.substring(indexFrom + "/from".length(), indexTo).trim();
        String afterTo = combinedWord.substring(indexTo + "/to".length()).trim();

        LocalDateTime startDate = dateTimeParser(afterFrom);
        LocalDateTime endDate = dateTimeParser(afterTo);

        return new Event(beforeFrom, startDate, endDate);
    }

    public Command parseCommand(String[] arr) throws UnknownCommandException {
        switch (arr[0]) {
            case "bye":
                return Command.BYE;

            case "list":
                return Command.LIST;

            case "todo":
                return Command.TODO;

            case "deadline":
                return Command.DEADLINE;

            case "event":
                return Command.EVENT;

            case "mark":
                return Command.MARK;

            case "unmark":
                return Command.UNMARK;

            case "delete":
                return Command.DELETE;

            default:
                throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public LocalDateTime dateTimeParser(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");

        return LocalDateTime.parse(dateTime, formatter);
    }
}
