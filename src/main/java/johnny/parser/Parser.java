package johnny.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import johnny.commands.AddDeadlineCommand;
import johnny.commands.AddEventCommand;
import johnny.commands.AddTodoCommand;
import johnny.commands.Command;
import johnny.commands.DeleteCommand;
import johnny.commands.ExitCommand;
import johnny.commands.FindCommand;
import johnny.commands.ListCommand;
import johnny.commands.MarkCommand;
import johnny.commands.UnmarkCommand;
import johnny.exceptions.JohnnyException;

/**
 * Parses the input from user into Commands to be executed.
 */
public class Parser {

    /** Format of DateTime in Tasks entered by user. */
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    /**
     * Parses the command into various Commands depending on the type of command.
     *
     * @param fullCommand Input entered by user.
     * @return Command that corresponds to the user input.
     * @throws JohnnyException If command does not match any existing command or format.
     */
    public static Command parse(String fullCommand) throws JohnnyException {
        List<String> parsedCommand = Arrays.asList(fullCommand.split(" "));
        checkIfCommandIsEmpty(parsedCommand, "Enter a command bro.");
        String command = parsedCommand.get(0);

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            List<Integer> indices = getIndicesFromCommand(parsedCommand,
                    "Which task do you want to mark bro?");
            return new MarkCommand(indices);
        case "unmark":
            indices = getIndicesFromCommand(parsedCommand,
                    "Which task do you want to unmark bro?");
            return new UnmarkCommand(indices);
        case "delete":
            indices = getIndicesFromCommand(parsedCommand,
                    "Which task am I supposed to delete bro?");
            return new DeleteCommand(indices);
        case "todo":
            return parseTodoCommand(parsedCommand);
        case "deadline":
            return parseDeadlineCommand(parsedCommand);
        case "event":
            return parseEventCommand(parsedCommand);
        case "find":
            return parseFindCommand(parsedCommand);
        default:
            throw new JohnnyException("Your command does not make sense bro.");
        }
    }

    private static void checkIfCommandIsEmpty(List<String> command, String errorMessage) throws JohnnyException {
        if (command.isEmpty()) {
            throw new JohnnyException(errorMessage);
        }
    }

    private static void checkIfCommandIsTooShort(List<String> command, String errorMessage) throws JohnnyException {
        if (command.size() < 2) {
            throw new JohnnyException(errorMessage);
        }
    }

    private static List<Integer> getIndicesFromCommand(List<String> command, String commandTooShortError)
            throws JohnnyException {
        try {
            checkIfCommandIsTooShort(command, commandTooShortError);
            return command.subList(1, command.size())
                    .stream()
                    .map(i -> Integer.parseInt(i) - 1)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new JohnnyException("Enter a valid number bro.");
        }
    }

    private static AddTodoCommand parseTodoCommand(List<String> command) throws JohnnyException {
        checkIfCommandIsTooShort(command, "What is your todo bro?");
        String name = String.join(" ", command.subList(1, command.size()));
        assert !name.equals("") : "Name should not be empty";
        return new AddTodoCommand(name);
    }

    private static void checkIfIndexNotFound(int index, String errorMessage) throws JohnnyException {
        if (index == -1) {
            throw new JohnnyException(errorMessage);
        }
    }

    private static LocalDateTime parseToLocalDateTime(String dateTime) throws JohnnyException {
        try {
            return LocalDateTime.parse(dateTime, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new JohnnyException("Date and Time should be in the format of YYYY/MM/DD HHMM bro.");
        }
    }

    private static AddDeadlineCommand parseDeadlineCommand(List<String> command) throws JohnnyException {
        checkIfCommandIsTooShort(command, "What is your deadline bro?");
        int i = command.indexOf("/by");
        checkIfIndexNotFound(i, "When is your deadline by bro?");
        String name = String.join(" ", command.subList(1, i));
        assert !name.equals("") : "Name should not be empty";
        String by = String.join(" ", command.subList(i + 1, command.size()));
        return new AddDeadlineCommand(name, parseToLocalDateTime(by));
    }

    private static AddEventCommand parseEventCommand(List<String> command) throws JohnnyException {
        checkIfCommandIsTooShort(command, "What is your deadline bro?");
        int i = command.indexOf("/from");
        checkIfIndexNotFound(i, "When does your event start from bro?");
        int j = command.indexOf("/to");
        checkIfIndexNotFound(j, "When does your event last to bro?");

        String name = String.join(" ", command.subList(1, i));
        assert !name.equals("") : "Name should not be empty";
        String from = String.join(" ", command.subList(i + 1, j));
        String to = String.join(" ", command.subList(j + 1, command.size()));
        return new AddEventCommand(name, parseToLocalDateTime(from), parseToLocalDateTime(to));
    }

    private static FindCommand parseFindCommand(List<String> command) throws JohnnyException {
        checkIfCommandIsTooShort(command, "What task do you want to find bro?");
        String keyword = String.join(" ", command.subList(1, command.size()));
        return new FindCommand(keyword);
    }

}
