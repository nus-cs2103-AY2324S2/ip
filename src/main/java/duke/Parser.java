package duke;

import duke.command.*;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Parser} class is responsible for parsing user input and converting it into a {@code Command} object.
 */
public class Parser {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HHmm");

<<<<<<< HEAD
    /**
     * Parses a user input string into a {@code Command} object.
     *
     * @param str The user input string.
     * @return A {@code Command} object representing the user's command.
     * @throws DukeException If the user input is invalid.
     */
=======
>>>>>>> branch-A-CodingStandard
    public static Command parse(String str) throws DukeException {
        String[] arr = str.split(" ");
        if (arr[0].equals("bye")) {
            return new ExitCommand();
        } else if (arr[0].equals("list")) {
            return new ListCommand();
        } else {
            switch (arr[0]) {
            case "delete":
                return new DeleteCommand(getIndex(arr));
            case "unmark":
                return new UnmarkCommand(getIndex(arr));
            case "mark":
                return new MarkCommand(getIndex(arr));
            default:
                String desc = getDescripition(arr);
                String[] newArr = desc.split("/");
                switch (arr[0]) {
                    case "todo":
                        return new AddCommand(newArr[0]);
                    case "deadline":
                        if (newArr.length < 2) {
                            throw new DukeException("Incomplete deadline information");
                        }
                        LocalDateTime deadline = null;
                        try {
                            deadline = LocalDateTime.parse(newArr[1].split("by")[1].trim(), formatter);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Invalid date/time");
                        }
                        return new AddCommand(newArr[0], deadline);
                    case "event":
                        if (newArr.length < 3) {
                            throw new DukeException("Incomplete event information");
                        }
                        LocalDateTime from = null;
                        LocalDateTime to = null;
                        try {
                            from = LocalDateTime.parse(newArr[1].split("from")[1].trim(), formatter);
                            to = LocalDateTime.parse(newArr[2].split("to")[1].trim(), formatter);
                case "todo":
                    return new AddCommand(newArr[0]);
                case "deadline":
                    if (newArr.length < 2) {
                        throw new DukeException("Incomplete deadline information");
                    }
                    LocalDateTime deadline = null;
                    try {
                        deadline = LocalDateTime.parse(newArr[1].split("by")[1].trim(), formatter);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid date/time");
                    }
                    return new AddCommand(newArr[0], deadline);
                case "event":
                    if (newArr.length < 3) {
                        throw new DukeException("Incomplete event information");
                    }
                    LocalDateTime from = null;
                    LocalDateTime to = null;
                    try {
                        from = LocalDateTime.parse(newArr[1].split("from")[1].trim(), formatter);
                        to = LocalDateTime.parse(newArr[2].split("to")[1].trim(), formatter);

                        } catch (DateTimeParseException e) {
                            throw new DukeException("Invalid date/time");
                        }
                        return new AddCommand(newArr[0], from, to);
                    default:
                        throw new DukeException("Invalid task type");
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid date/time");
                    }
                    return new AddCommand(newArr[0], from, to);
                default:
                    throw new DukeException("Invalid task type");
                }
            }
        }
    }

    /**
     * Extracts the index of a task from a user input string.
     *
     * @param arr The user input string, split into an array of strings.
     * @return The index of the task, as an integer.
     */
    private static int getIndex(String[] arr) {
        int index = Integer.parseInt(arr[1]);
        return index - 1;
    }

    /**
     * Extracts the description of a task from a user input string.
     *
     * @param arr The user input string, split into an array of strings.
     * @return The description of the task, as a string.
     */
    private static String getDescripition(String[] arr) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            s.append(arr[i]).append(" ");
        }
        return s.toString();
    }
}
