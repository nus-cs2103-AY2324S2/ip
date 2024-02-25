package paimon.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import paimon.ChatException;
import paimon.command.Command;
import paimon.command.DeadlineCommand;
import paimon.command.DeleteCommand;
import paimon.command.EventCommand;
import paimon.command.ExitCommand;
import paimon.command.FindCommand;
import paimon.command.HelpCommand;
import paimon.command.ListCommand;
import paimon.command.MarkCommand;
import paimon.command.TodoCommand;
import paimon.command.UnmarkCommand;


/**
 * Parses user input into specific commands for the application to execute.
 * This class takes the user input, determines the type of command it represents,
 * and then constructs the appropriate command object to be executed.
 */
public class CommandParser {
    private final String input;
    private final String type;

    /**
     * Constructs a new CommandParser with the user's input.
     * It also determines the command type based on the input.
     *
     * @param input The full command input from the user.
     */
    public CommandParser(String input) {
        this.input = input;
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex != -1) {
            this.type = input.substring(0, firstSpaceIndex);
        } else {
            this.type = input;
        }
    }

    /**
     * Returns the type of the command determined from the user's input.
     *
     * @return The command type as a {@code String}.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Parses the user's input and constructs the corresponding command object.
     * The method uses regular expressions to match against specific command patterns
     * and extracts necessary details to construct command objects.
     *
     * @return A {@link Command} object representing the user's command.
     * @throws ChatException If the input does follow proper syntax.
     */
    public Command parseInput() throws ChatException {
        switch (this.type) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "todo":
            return parseTodoCommand();
        case "deadline":
            return parseDeadlineCommand();
        case "event":
            return parseEventCommand();
        case "mark":
            return parseMarkCommand();
        case "unmark":
            return parseUnmarkCommand();
        case "delete":
            return parseDeleteCommand();
        case "find":
            return parseFindCommand();
        default:
            throw new ChatException("Command not found, type help for a list of commands");
        }
    }

    private Command parseTodoCommand() throws ChatException {
        Matcher matcher = matchCommand("^(\\w+)(\\s)(.+)", input, "todo <task>");
        return new TodoCommand(matcher.group(3));
    }

    private Command parseDeadlineCommand() throws ChatException {
        Matcher matcher = matchCommand("^(\\w+) (.+?)\\/by (.+)$", input, "deadline <task> /by <time>");
        return new DeadlineCommand(matcher.group(2), matcher.group(3));
    }

    private Command parseEventCommand() throws ChatException {
        Matcher matcher = matchCommand("^(\\w+) (.+?) \\/from (.+?) \\/to (.+)$",
                input, "event <task> /from <time> /to <time>");
        return new EventCommand(matcher.group(2), matcher.group(3), matcher.group(4));
    }

    private Command parseMarkCommand() throws ChatException {
        Matcher matcher = matchCommand("^(\\w+) (\\d+)", input, "mark <number>");
        return new MarkCommand(matcher.group(2));
    }

    private Command parseUnmarkCommand() throws ChatException {
        Matcher matcher = matchCommand("^(\\w+) (\\d+)", input, "unmark <number>");
        return new UnmarkCommand(matcher.group(2));
    }

    private Command parseDeleteCommand() throws ChatException {
        Matcher matcher = matchCommand("^(\\w+) (\\d+)", input, "delete <number>");
        return new DeleteCommand(matcher.group(2));
    }

    private Command parseFindCommand() throws ChatException {
        Matcher matcher = matchCommand("^(\\w+)(\\s)(.+)", input, "find <keyword>");
        return new FindCommand(matcher.group(3));
    }

    private Matcher matchCommand(String regex, String input, String exceptionString) throws ChatException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new ChatException("Input does not match expected format: " + exceptionString);
        }
        return matcher;
    }

}
