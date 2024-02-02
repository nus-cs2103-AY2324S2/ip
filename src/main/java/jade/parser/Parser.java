package jade.parser;

import jade.data.Deadline;
import jade.data.Event;
import jade.data.Todo;
import jade.commands.*;
import jade.exception.JadeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

public class Parser {

    public static boolean parseBooleanNum(String str) {
        return str.equals("1") ? true : false;
    }

    public static String concatDescription(String[] commands, String start, String end) throws JadeException {
        try {
            int startIndex = start.isEmpty() ? 1 : Arrays.asList(commands).indexOf(start) + 1;
            int endIndex = end.isEmpty() ? commands.length : Arrays.asList(commands).indexOf(end);
            return String.join(" ", Arrays.copyOfRange(commands, startIndex, endIndex));
        } catch (IllegalArgumentException e) {
            throw new JadeException("Illegal Argument");
        }
    }

    public static void checkEmptyDescription(String[] commands, int length) throws JadeException {
        if (commands.length < length) {
            throw new JadeException("\tYour task description cannot be empty!");
        }
    }

    public static LocalDate parseDate(String date) throws JadeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new JadeException("\tYour date format is invalid!");
        }
    }

    public static int parseInt(String intStr) throws JadeException {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            throw new JadeException("\tPlease input a valid number!");
        }
    }

    public static Command parse(String str) {
        try {
            String[] commands = str.split(" ");
            switch (commands[0]) {
                case "todo":
                    checkEmptyDescription(commands, 2);
                    return new AddCommand(new Todo(concatDescription(commands, "", "")));
                case "deadline":
                    checkEmptyDescription(commands, 3);
                    return new AddCommand(new Deadline(concatDescription(commands, "", "/by"), parseDate(concatDescription(commands, "/by", ""))));
                case "event":
                    checkEmptyDescription(commands, 4);
                    return new AddCommand(new Event(concatDescription(commands, "", "/from"), parseDate(concatDescription(commands, "/from", "/to")), parseDate(concatDescription(commands, "/to", ""))));
                case "list":
                    if (commands.length != 1) {
                        return new ListCommand(parseDate(concatDescription(commands, "", "")));
                    }
                    return new ListCommand();
                case "mark":
                    checkEmptyDescription(commands, 2);
                    return new MarkCommand(parseInt(commands[1]));
                case "unmark":
                    checkEmptyDescription(commands, 2);
                    return new UnmarkCommand(parseInt(commands[1]));
                case "delete":
                    checkEmptyDescription(commands, 2);
                    return new DeleteCommand(parseInt(commands[1]));
                case "bye":
                    checkEmptyDescription(commands, 1);
                    return new ExitCommand();
                case "find":
                    checkEmptyDescription(commands, 2);
                    return new FindCommand(concatDescription(commands, "", ""));
                default:
                    return new InvalidCommand(new JadeException("\tInput is invalid, please retry.\n"));
            }
        } catch (JadeException e) {
            return new InvalidCommand(e);
        }
    }
}
