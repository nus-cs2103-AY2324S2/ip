package checkbot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import checkbot.command.AddCommand;
import checkbot.command.ByeCommand;
import checkbot.command.Command;
import checkbot.command.DeleteCommand;
import checkbot.command.FindCommand;
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
    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param input The user input.
     * @return The corresponding Command.
     * @throws CheckbotException If the user input is invalid.
     */
    public Command parse(String input) throws CheckbotException {
        if (input.equals("bye")) {
            return new ByeCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (input.startsWith("mark ")) {
            try {
                int i = Integer.parseInt(input.split("mark ")[1]) - 1;
                return new MarkCommand(i);
            } catch (NumberFormatException e) {
                throw new InvalidIndexException(input.split("mark ")[1]);
            }
        }
        if (input.startsWith("unmark ")) {
            try {
                int i = Integer.parseInt(input.split("unmark ")[1]) - 1;
                return new UnmarkCommand(i);
            } catch (NumberFormatException e) {
                throw new InvalidIndexException(input.split("unmark ")[1]);
            }
        }
        if (input.startsWith("delete ")) {
            try {
                int i = Integer.parseInt(input.split("delete ")[1]) - 1;
                return new DeleteCommand(i);
            } catch (NumberFormatException e) {
                throw new InvalidIndexException(input.split("delete ")[1]);
            }
        }
        if (input.startsWith("todo")
                || input.startsWith("deadline")
                || input.startsWith("event")) {
            String[] splitString = input.split("todo|deadline|event|\\/(by|from|to)");
            String taskName = splitString.length > 1 ? splitString[1].strip() : "";
            if (taskName.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            if (input.startsWith("todo")) {
                return new AddCommand(taskName);
            }
            if (input.startsWith("deadline")) {
                Pattern pattern = Pattern.compile("deadline (.*) /by (.*)");
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    String byWhen = matcher.group(2).strip();
                    if (byWhen.isEmpty()) {
                        throw new MissingDeadlineException();
                    }
                    return new AddCommand(taskName, byWhen);
                } else {
                    throw new MissingDeadlineException();
                }
            }
            assert input.startsWith("event") : "Command should have started with 'event'";
            Pattern pattern = Pattern.compile("event (.*) /(from|to)(.*) /(from|to)(.*)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String firstLabel = matcher.group(2);
                String firstValue = matcher.group(3).strip();
                String secondValue = matcher.group(5).strip();

                String from, to;
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
            } else {
                if (input.contains(" /from ")) {
                    throw new MissingToException();
                }
                throw new MissingFromException();
            }
        }
        if (Pattern.compile("find (.*)").matcher(input).find()) {
            String searchText = input.split("find ")[1];
            return new FindCommand(searchText);
        }
        throw new InvalidCommandException(input);
    }
}
