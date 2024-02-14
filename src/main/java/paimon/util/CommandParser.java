package paimon.util;

import paimon.ChatException;
import paimon.command.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                String todoRegex = "^(\\w+)(\\s)(.+)";
                Pattern todoPattern = Pattern.compile(todoRegex);
                Matcher todoMatcher = todoPattern.matcher(input);
                if (todoMatcher.find()) {
                    String description = todoMatcher.group(3);
                    return new TodoCommand(description);
                } else {
                    throw new ChatException("Input does not match expected format: todo <task>");
                }
            case "deadline":
                String deadlineRegex = "^(\\w+) (.+?)\\/by (.+)$";
                Pattern deadlinePattern = Pattern.compile(deadlineRegex);
                Matcher deadlineMatcher = deadlinePattern.matcher(input);
                if (deadlineMatcher.find()) {
                    String description = deadlineMatcher.group(2);
                    String toTime = deadlineMatcher.group(3);
                    return new DeadlineCommand(description, toTime);
                } else {
                    throw new ChatException("Input does not match expected format: deadline <task> /by <time>");
                }
            case "event":
                String eventRegex = "^(\\w+) (.+?) \\/from (.+?) \\/to (.+)$";
                Pattern eventPattern = Pattern.compile(eventRegex);
                Matcher eventMatcher = eventPattern.matcher(input);
                if (eventMatcher.find()) {
                    String description = eventMatcher.group(2);
                    String fromTime = eventMatcher.group(3);
                    String toTime = eventMatcher.group(4);
                    return new EventCommand(description, fromTime, toTime);
                } else {
                    throw new ChatException("Input does not match expected format: event <task> /from <time> /to <time>");
                }
            case "mark":
                String markRegex = "^(\\w+) (\\d+)";
                Pattern markPattern = Pattern.compile(markRegex);
                Matcher markMatcher = markPattern.matcher(input);
                if (markMatcher.find()) {
                    String number = markMatcher.group(2);
                    return new MarkCommand(number);
                } else {
                    throw new ChatException("Input does not match expected format: mark <number>");
                }
            case "unmark":
                String unmarkRegex = "^(\\w+) (\\d+)";
                Pattern unmarkPattern = Pattern.compile(unmarkRegex);
                Matcher unmarkMatcher = unmarkPattern.matcher(input);
                if (unmarkMatcher.find()) {
                    String number = unmarkMatcher.group(2);
                    return new UnmarkCommand(number);
                } else {
                    throw new ChatException("Input does not match expected format: unmark <number>");
                }
            case "delete":
                String deleteRegex = "^(\\w+) (\\d+)";
                Pattern deletePattern = Pattern.compile(deleteRegex);
                Matcher deleteMatcher = deletePattern.matcher(input);
                if (deleteMatcher.find()) {
                    String number = deleteMatcher.group(2);
                    return new DeleteCommand(number);
                } else {
                    throw new ChatException("Input does not match expected format: delete <number>");
                }
            case "find":
                String findRegex = "^(\\w+)(\\s)(.+)";
                Pattern findPattern = Pattern.compile(findRegex);
                Matcher findMatcher = findPattern.matcher(input);
                if (findMatcher.find()) {
                    String keyword = findMatcher.group(3);
                    return new FindCommand(keyword);
                } else {
                    throw new ChatException("Input does not match expected format: find <keyword>");
                }
            default:
                throw new ChatException("Command not found, type help for a list of commands");
        }
    }
}
