package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.command.ViewByDateCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.ui.Messages;

/**
 * A utility class for parsing user input and generating corresponding commands.
 *
 * <p>The {@code Parser} class provides static methods for parsing user input strings
 * and generating corresponding command objects to perform specific actions in the Duke chatbot.
 * It handles the parsing of various command types, such as adding tasks, marking tasks as done,
 * listing tasks, and more.</p>
 */
public class Parser {

    /**
     * Parses the user input string and generates the corresponding command object.
     *
     * @param userInput The user input string to parse.
     * @return A {@link Command} object representing the parsed command.
     * @throws DukeException If there is an error parsing the input or generating the command.
     */
    public static Command parseInput(String userInput) throws DukeException {
        try {
            String[] parts = userInput.split(" ", 2);
            String command = parts[0].toLowerCase();
            List<String> arguments = parts.length > 1 ? List.of(parts[1].split("\\s+")) : List.of();

            switch (command) {
            case "f":
            case "fi":
            case "fin":
            case "find":
                return new FindCommand(String.join(" ", arguments));
            case "h":
            case "help":
                return new HelpCommand();
            case "l":
            case "list":
                return new ListCommand();
            case "m":
            case "mark":
                return new MarkCommand(arguments);
            case "b":
            case "bye":
            case "exit":
                return new ByeCommand();
            case "u":
            case "un":
            case "unmark":
                return new UnmarkCommand(arguments);
            case "d":
            case "del":
            case "delete":
            case "remove":
                return new DeleteCommand(arguments);
            case "t":
            case "todo":
                return new AddCommand(new ToDo(extractTaskDescription(arguments)));
            case "dl":
            case "deadline":
                return parseDeadline(arguments);
            case "e":
            case "event":
                return parseEvent(arguments);
            case "v":
            case "view":
            case "viewbydate":
                return parseViewByDate(arguments);
            default:
                return new UnknownCommand();
            }
        } catch (DukeException e) {
            System.out.printf("\n%s", e.getMessage());
            return new UnknownCommand();
        }
    }

    private static Command parseDeadline(List<String> arguments) throws DukeException {
        String taskDescription = extractTaskDescription(arguments, "/by");
        LocalDateTime deadlineDateTime = parseDateTime(getDateTimeString(arguments, "/by"));

        if (taskDescription.isEmpty()) {
            throw new DukeException(Messages.PARSER_DEADLINE_DESCRIPTION_ERROR.getMessage());
        }
        if (deadlineDateTime == null) {
            throw new DukeException(Messages.PARSER_DEADLINE_DATE_TIME_ERROR.getMessage());
        }

        return new AddCommand(new Deadline(taskDescription, deadlineDateTime));
    }

    private static Command parseEvent(List<String> arguments) throws DukeException {
        String taskDescription = extractTaskDescription(arguments, "/from", "/to");
        LocalDateTime startDateTime = parseDateTime(getDateTimeString(arguments, "/from"));
        LocalDateTime endDateTime = parseDateTime(getDateTimeString(arguments, "/to"));

        if (taskDescription.isEmpty()) {
            throw new DukeException(Messages.PARSER_EVENT_DESCRIPTION_ERROR.getMessage());
        }
        if (startDateTime == null || endDateTime == null) {
            throw new DukeException(Messages.PARSER_EVENT_DATE_TIME_ERROR.getMessage());
        }

        return new AddCommand(new Event(taskDescription, startDateTime, endDateTime));
    }

    private static Command parseViewByDate(List<String> arguments) throws DukeException {
        LocalDate viewDate = LocalDate.parse(extractTaskDescription(arguments), DateTimeFormatter.ISO_LOCAL_DATE);
        return new ViewByDateCommand(viewDate);
    }

    private static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.PARSER_INVALID_DATE_TIME_FORMAT.getMessage());
        }
    }

    private static String extractTaskDescription(List<String> arguments, String... keywords) {
        int keywordIndex = findKeywordIndex(arguments, keywords);
        if (keywordIndex != -1) {
            return String.join(" ", arguments.subList(0, keywordIndex));
        }
        return String.join(" ", arguments);
    }

    private static String getDateTimeString(List<String> arguments, String keyword) throws DukeException {
        int index = arguments.indexOf(keyword);
        if (index != -1 && index < arguments.size() - 1) {
            return String.join(" ", arguments.subList(index + 1, arguments.size()));
        } else {
            throw new DukeException(String.format(Messages.PARSER_MISSING_KEYWORD_ERROR.getMessage(), keyword));
        }
    }

    private static int findKeywordIndex(List<String> arguments, String... keywords) {
        for (String keyword : keywords) {
            if (arguments.contains(keyword)) {
                return arguments.indexOf(keyword);
            }
        }
        return -1;
    }
}
