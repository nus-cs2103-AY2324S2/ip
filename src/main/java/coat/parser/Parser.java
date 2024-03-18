package coat.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import coat.command.AddCommand;
import coat.command.ByeCommand;
import coat.command.ClearCommand;
import coat.command.Command;
import coat.command.DeleteCommand;
import coat.command.FindCommand;
import coat.command.HelpCommand;
import coat.command.ListCommand;
import coat.command.MarkCommand;
import coat.command.NoopCommand;
import coat.command.UnknownCommand;
import coat.command.UnmarkCommand;
import coat.command.ViewByDateCommand;
import coat.exception.CoatException;
import coat.task.Deadline;
import coat.task.Event;
import coat.task.ToDo;
import coat.ui.Messages;
import coat.ui.Ui;

/**
 * A utility class for parsing user input and generating corresponding commands.
 *
 * <p>The {@code Parser} class provides static methods for parsing user input strings
 * and generating corresponding command objects to perform specific actions in the Coat chatbot.
 * It handles the parsing of various command types, such as adding tasks, marking tasks as done,
 * listing tasks, and more.</p>
 */
public class Parser {
    private static Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the user input string and generates the corresponding command object.
     *
     * @param userInput The user input string to parse.
     * @return A {@link Command} object representing the parsed command.
     * @throws CoatException If there is an error parsing the input or generating the command.
     */
    public static Command parseInput(String userInput) throws CoatException {
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
            case "ls":
            case "list":
                return new ListCommand();
            case "m":
            case "mark":
                return new MarkCommand(arguments);
            case "bye":
            case "exit":
            case "close":
            case "leave":
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
                String taskDescription = extractTaskDescription(arguments);
                if (taskDescription.isEmpty()) {
                    String errorMessage = Messages.PARSER_TODO_DESCRIPTION_ERROR.getMessage();
                    ui.appendResponse(errorMessage);
                    throw new CoatException(errorMessage);
                }
                return new AddCommand(new ToDo(taskDescription));
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
            case "clear":
                return new ClearCommand();
            default:
                return new UnknownCommand();
            }
        } catch (CoatException e) {
            System.out.printf("\n%s", e.getMessage());
            return new NoopCommand();
        }
    }

    private static Command parseDeadline(List<String> arguments) throws CoatException {
        String taskDescription = extractTaskDescription(arguments, "/by");
        LocalDateTime deadlineDateTime = parseDateTime(getDateTimeString(arguments, "/by"));

        if (taskDescription.isEmpty()) {
            String errorMessage = Messages.PARSER_DEADLINE_DESCRIPTION_ERROR.getMessage();
            ui.appendResponse(errorMessage);
            throw new CoatException(errorMessage);
        }
        if (deadlineDateTime == null) {
            String errorMessage = Messages.PARSER_DEADLINE_DATE_TIME_ERROR.getMessage();
            ui.appendResponse(errorMessage);
            throw new CoatException(errorMessage);
        }

        return new AddCommand(new Deadline(taskDescription, deadlineDateTime));
    }

    private static Command parseEvent(List<String> arguments) throws CoatException {
        String taskDescription = extractTaskDescription(arguments, "/from");
        String startDateTimeString = getDateTimeString(arguments, "/from");
        String endDateTimeString = getDateTimeString(arguments, "/to");

        LocalDateTime startDateTime = parseDateTime(startDateTimeString);
        LocalDateTime endDateTime = parseDateTime(endDateTimeString);

        if (taskDescription.isEmpty()) {
            String errorMessage = Messages.PARSER_EVENT_DESCRIPTION_ERROR.getMessage();
            ui.appendResponse(errorMessage);
            throw new CoatException(errorMessage);
        }
        if (startDateTime == null || endDateTime == null) {
            String errorMessage = Messages.PARSER_EVENT_DATE_TIME_ERROR.getMessage();
            ui.appendResponse(errorMessage);
            throw new CoatException(errorMessage);
        }

        return new AddCommand(new Event(taskDescription, startDateTime, endDateTime));
    }


    private static Command parseViewByDate(List<String> arguments) throws CoatException {
        if (arguments.isEmpty()) {
            String errorMessage = Messages.PARSER_VIEW_DATE_ERROR.getMessage();
            ui.appendResponse(errorMessage);
            throw new CoatException(errorMessage);
        }

        String dateString = arguments.get(0); // Extracting the date string from the first argument
        LocalDate viewDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        return new ViewByDateCommand(viewDate);
    }


    private static LocalDateTime parseDateTime(String dateTimeString) throws CoatException {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            String errorMessage = Messages.PARSER_INVALID_DATE_TIME_FORMAT.getMessage();
            ui.appendResponse(errorMessage);
            throw new CoatException(errorMessage);
        }
    }

    private static String extractTaskDescription(List<String> arguments, String... keywords) {
        int keywordIndex = findKeywordIndex(arguments, keywords);
        if (keywordIndex != -1) {
            return String.join(" ", arguments.subList(0, keywordIndex));
        }
        return String.join(" ", arguments);
    }

    private static String getDateTimeString(List<String> arguments, String keyword) throws CoatException {
        int index = arguments.indexOf(keyword);
        if (index != -1 && index < arguments.size() - 1) {
            try {
                return String.join(" ", arguments.subList(index + 1, index + 3));
            } catch (IndexOutOfBoundsException e) {
                String errorMessage = Messages.PARSER_INVALID_DATE_TIME_FORMAT.getMessage();
                ui.appendResponse(errorMessage);
                throw new CoatException(errorMessage);
            }
        } else {
            String errorMessage = String.format(Messages.PARSER_MISSING_KEYWORD_ERROR.getMessage(), keyword);
            ui.appendResponse(errorMessage);
            throw new CoatException(errorMessage);
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
