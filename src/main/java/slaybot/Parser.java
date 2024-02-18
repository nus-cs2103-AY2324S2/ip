package slaybot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entity.Deadline;
import entity.Event;
import entity.ToDo;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import exception.UnknownCommandException;
/**
 * The Parser class is responsible for parsing user input and converting it
 * into meaningful objects or commands for the SlayBot application. It handles the
 * parsing of commands related to tasks (ToDo, Deadline, Event) and other user interactions.
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
        FIND,
        SORT,


    }

    /**
     * Parses the user input for creating a {@code Deadline} task.
     *
     * @param arr The array containing user input words.
     * @return The parsed {@code Deadline} task.
     * @throws InvalidDeadlineException If there is an issue with the input or parsing.
     */
    public Deadline parseDeadline(String[] arr) throws InvalidDeadlineException {
        String deadlineTitle = "";
        String dateTime = "";
        String combinedWord = "";

        for (int i = 1; i < arr.length; i++) {
            combinedWord += arr[i] + " ";
        }

        int index = combinedWord.indexOf("/by");

        if (index != -1) {
            dateTime = combinedWord.substring(index + 3).trim();
            deadlineTitle = combinedWord.substring(0, index);
        } else {
            if (deadlineTitle.isEmpty() && dateTime.isEmpty()) {
                throw new InvalidDeadlineException("OOPS!!! The description and date of a Deadline cannot be empty.");
            } else if (deadlineTitle.isEmpty()) {
                throw new InvalidDeadlineException("OOPS!!! The description of a Deadline cannot be empty.");
            } else if (dateTime.isEmpty()) {
                throw new InvalidDeadlineException("OOPS!!! The date of a Deadline cannot be empty.");
            }
        }

        LocalDateTime date = parseDateTime(dateTime);

        return new Deadline(deadlineTitle, date);
    }

    /**
     * Parses the user input for creating a {@code ToDo} task.
     *
     * @param arr The array containing user input words.
     * @return The parsed {@code ToDo} task.
     * @throws InvalidTodoException If there is an issue with the input or parsing.
     */
    public ToDo parseTodo(String[] arr) throws InvalidTodoException {
        String todoTitle = "";

        if (arr.length - 1 == 0) {
            throw new InvalidTodoException("OOPS!!! The description of a Todo cannot be empty.");
        }

        for (int i = 1; i < arr.length; i++) {
            todoTitle += arr[i];
            if (i != arr.length - 1) {
                todoTitle += " ";
            }
        }
        return new ToDo(todoTitle);
    }

    /**
     * Parses the user input for creating an {@code Event} task.
     *
     * @param splitWords The array containing user input words.
     * @return The parsed {@code Event} task.
     * @throws InvalidEventException If there is an issue with the input or parsing.
     */

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

        LocalDateTime startDate = parseDateTime(afterFrom);
        LocalDateTime endDate = parseDateTime(afterTo);

        return new Event(beforeFrom, startDate, endDate);
    }

    /**
     * Parses the user input to determine the command type.
     *
     * @param arr The array containing user input words.
     * @return The parsed command type.
     * @throws UnknownCommandException If the command is not recognized.
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
        case "find":
            return Command.FIND;
        case "sort":
            return Command.SORT;
        default:
            throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the date and time from the user input.
     *
     * @param dateTime The date and time string to be parsed.
     * @return The {@code LocalDateTime} object representing the parsed date and time.
     */
    public LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");

        return LocalDateTime.parse(dateTime, formatter);
    }
}
