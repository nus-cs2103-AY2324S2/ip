package checkbot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import checkbot.command.AddCommand;
import checkbot.command.ByeCommand;
import checkbot.command.Command;
import checkbot.command.DeleteCommand;
import checkbot.command.FindCommand;
import checkbot.command.HelpCommand;
import checkbot.command.ListCommand;
import checkbot.command.MarkCommand;
import checkbot.command.UnmarkCommand;
import checkbot.exception.CheckbotException;
import checkbot.exception.EmptyDescriptionException;
import checkbot.exception.InvalidCommandException;
import checkbot.exception.InvalidIndexException;
import checkbot.exception.MissingDeadlineException;
import checkbot.exception.MissingFromException;
import checkbot.exception.MissingToException;

/**
 * Handles the parsing of user input.
 */
public class Parser {
    private static UnmarkCommand parseUnmarkCommand(String input) throws InvalidIndexException {
        try {
            int i = Integer.parseInt(input.split("unmark ")[1]) - 1;
            return new UnmarkCommand(i);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(input.split("unmark ")[1]);
        }
    }

    private static MarkCommand parseMarkCommand(String input) throws InvalidIndexException {
        try {
            int i = Integer.parseInt(input.split("mark ")[1]) - 1;
            return new MarkCommand(i);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(input.split("mark ")[1]);
        }
    }

    private static DeleteCommand parseDeleteCommand(String input) throws InvalidIndexException {
        try {
            int i = Integer.parseInt(input.split("delete ")[1]) - 1;
            return new DeleteCommand(i);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(input.split("delete ")[1]);
        }
    }

    private static AddCommand parseAddCommand(String input) throws
            EmptyDescriptionException, MissingDeadlineException, MissingFromException, MissingToException {
        String[] splitString = input.split("todo|deadline|event|/(by|from|to)");
        String taskName = splitString.length > 1 ? splitString[1].strip() : "";
        if (taskName.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (input.startsWith("todo")) {
            return new AddCommand(taskName);
        }
        if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input, taskName);
        }
        assert input.startsWith("event") : "Command should have started with 'event'";
        return parseEventCommand(input, taskName);
    }

    private static AddCommand parseEventCommand(String input, String taskName)
            throws MissingFromException, MissingToException, EmptyDescriptionException {
        // We allow for any order of from and to for flexibility
        Pattern pattern = Pattern.compile("event (.*) /(from|to)(.*) /(from|to)(.*)");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            if (!input.contains(" /from ")) {
                throw new MissingFromException();
            } else if (!input.contains("/to")) {
                throw new MissingFromException();
            }
            throw new EmptyDescriptionException();
        }

        String firstLabel = matcher.group(2);
        String firstValue = matcher.group(3).strip();
        String secondValue = matcher.group(5).strip();

        String from;
        String to;

        if (firstLabel.equals("from")) {
            from = firstValue;
            to = secondValue;
        } else {
            from = secondValue;
            to = firstValue;
        }

        if (from.isEmpty()) {
            throw new MissingFromException();
        }
        if (to.isEmpty()) {
            throw new MissingToException();
        }

        return new AddCommand(taskName, from, to);
    }

    private static AddCommand parseDeadlineCommand(String input, String taskName) throws MissingDeadlineException {
        Pattern pattern = Pattern.compile("deadline (.*) /by (.*)");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new MissingDeadlineException();
        }

        String byWhen = matcher.group(2).strip();
        if (byWhen.isEmpty()) {
            throw new MissingDeadlineException();
        }
        return new AddCommand(taskName, byWhen);
    }

    private static FindCommand parseFindCommand(String input) {
        String searchText = input.split("find ")[1];
        return new FindCommand(searchText);
    }

    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param input The user input.
     * @return The corresponding Command.
     * @throws CheckbotException If the user input is invalid.
     */
    public Command parse(String input) throws CheckbotException {
        if (input.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        }
        if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        }
        if (input.toLowerCase().startsWith("mark ")) {
            return parseMarkCommand(input);
        }
        if (input.toLowerCase().startsWith("unmark ")) {
            return parseUnmarkCommand(input);
        }
        if (input.toLowerCase().startsWith("delete ")) {
            return parseDeleteCommand(input);
        }
        if (input.toLowerCase().startsWith("todo ")
                || input.toLowerCase().startsWith("deadline ")
                || input.toLowerCase().startsWith("event ")) {
            return parseAddCommand(input);
        }
        if (input.toLowerCase().startsWith("find ")) {
            return parseFindCommand(input);
        }
        if (input.equalsIgnoreCase("help")) {
            return new HelpCommand();
        }
        throw new InvalidCommandException(input);
    }
}
