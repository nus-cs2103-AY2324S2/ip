package xilef;

import xilef.command.AddCommand;
import xilef.command.Command;
import xilef.command.DeleteCommand;
import xilef.command.ExitCommand;
import xilef.command.ListCommand;
import xilef.command.MarkCommand;
import xilef.command.UnmarkCommand;
import xilef.command.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Parser} class is responsible for parsing user input and converting it into a {@code Command} object.
 */
public class Parser {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HHmm");
    public static Command parse(String str) throws XilefException {
        String[] arr = str.split(" ");
        assert !str.isEmpty() : "Input cannot be empty";
        assert arr.length > 0 : "Error in parsing input";
        if (isSingleWordCommand(arr[0])) {
            return handleSingleWordCommand(arr);
        } else {
            return handleMultiWordCommand(arr);
        }
    }
    private static boolean isSingleWordCommand(String command) {
        return command.equals("bye") || command.equals("list") || command.equals("undo");
    }

    private static Command handleSingleWordCommand(String[] arr) throws XilefException {
        switch (arr[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "undo":
            return new UndoCommand();
        default:
            throw new XilefException("Invalid command");
        }
    }

    private static Command handleMultiWordCommand(String[] arr) throws XilefException {
        String commandType = arr[0];
        switch (commandType) {
        case "delete":
            return new DeleteCommand(getIndex(arr));
        case "unmark":
            return new UnmarkCommand(getIndex(arr));
        case "mark":
            return new MarkCommand(getIndex(arr));
        case "find":
            String keyword = arr[1];
            return new FindCommand(keyword);
        default:
            return handleAddCommand(arr);
        }
    }

    private static Command handleAddCommand(String[] arr) throws XilefException {
        String desc = getDescription(arr);
        String[] newArr = desc.split("/");
        switch (arr[0]) {
        case "todo":
            return new AddCommand(newArr[0]);
        case "deadline":
            return handleDeadlineCommand(newArr);
        case "event":
            return handleEventCommand(newArr);
        default:
            throw new XilefException("Invalid task type");
        }
    }

    private static Command handleDeadlineCommand(String[] newArr) throws XilefException {
        if (newArr.length < 2) {
            throw new XilefException("Incomplete deadline information");
        }
        LocalDateTime deadline = null;
        try {
            deadline = parseDateTime(newArr[1].split("by")[1].trim());
        } catch (DateTimeParseException e) {
            throw new XilefException("Invalid date/time");
        }
        return new AddCommand(newArr[0], deadline);
    }

    private static Command handleEventCommand(String[] newArr) throws XilefException {
        if (newArr.length < 3) {
            throw new XilefException("Incomplete event information");
        }
        LocalDateTime from = null;
        LocalDateTime to = null;
        try {
            from = parseDateTime(newArr[1].split("from")[1].trim());
            to = parseDateTime(newArr[2].split("to")[1].trim());
        } catch (DateTimeParseException e) {
            throw new XilefException("Invalid date/time");
        }
        return new AddCommand(newArr[0], from, to);
    }
    private static int getIndex(String[] arr) {
        int index = Integer.parseInt(arr[1]);
        return index - 1;
    }

    private static String getDescription(String[] arr) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            s.append(arr[i]).append(" ");
        }
        return s.toString();
    }

    private static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }
}
