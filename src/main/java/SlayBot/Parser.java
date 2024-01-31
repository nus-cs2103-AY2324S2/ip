package SlayBot;

import entity.Deadline;
import entity.Event;
import entity.ToDo;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import exception.UnknownCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class deals with making sense of the user command
 */
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

    /**
     * Parses the user input to create a Deadline object.
     *
     * @param arr The array of strings representing the user input.
     * @return A Deadline object
     * @throws InvalidDeadlineException If the user input does not meet the requirements for a Deadline.
     */
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
                throw new InvalidDeadlineException("OOPS!!! The description and date of a Deadline cannot be empty.");
            } else if (deadline_title.isEmpty()) {
                throw new InvalidDeadlineException("OOPS!!! The description of a Deadline cannot be empty.");
            } else if (dateTime.isEmpty()) {
                throw new InvalidDeadlineException("OOPS!!! The date of a Deadline cannot be empty.");
            }
        }

        LocalDateTime date = parseDateTime(dateTime);

        return new Deadline(deadline_title, date);
    }

    /**
     * Parses the user input to create a ToDo object.
     *
     * @param arr The array of strings representing the user input.
     * @return A ToDo object parsed from the user input.
     * @throws InvalidTodoException If the user input does not meet the requirements for a ToDo.
     */
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

    /**
     * Parses the user input to create an Event object.
     *
     * @param arr The array of strings representing the user input.
     * @return An Event object parsed from the user input.
     * @throws InvalidEventException If the user input does not meet the requirements for an Event.
     */
    public Event parseEvent(String[] arr) throws InvalidEventException {
        String combinedWord = "";
        for (int i = 1; i < arr.length; i++) {
            combinedWord += arr[i] + " ";
        }
        int indexFrom = combinedWord.indexOf("/from");
        int indexTo = combinedWord.indexOf("/to");

        if (arr.length - 1 == 0) {
            throw new InvalidEventException("OOPS!!! The description of an Event cannot be empty.");
        } else if (indexFrom < 0 || indexTo < 0) {
            throw new InvalidEventException("OOPS!!! The date of an Event cannot be empty.");
        }

        String beforeFrom = combinedWord.substring(0, indexFrom).trim();
        String afterFrom = combinedWord.substring(indexFrom + "/from".length(), indexTo).trim();
        String afterTo = combinedWord.substring(indexTo + "/to".length()).trim();

        LocalDateTime startDate = parseDateTime(afterFrom);
        LocalDateTime endDate = parseDateTime(afterTo);

        return new Event(beforeFrom, startDate, endDate);
    }

    /**
     * Parses the user input to identify the command.
     *
     * @param arr The array of strings representing the user input.
     * @return The Command enum representing the parsed command.
     * @throws UnknownCommandException If the user input does not match any recognized command.
     */
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

    /**
     * Helper function to parse the string representation of date and time into a LocalDateTime object.
     *
     * @param dateTime The string representation of date and time.
     * @return A LocalDateTime object parsed from the input string.
     */
    public LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");

        return LocalDateTime.parse(dateTime, formatter);
    }
}
