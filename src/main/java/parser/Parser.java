package parser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import commands.*;
import static utils.StringUtils.parseDateTime;

/**
 * This class contains utility methods for parsing user input into commands.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern List_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    /**
     * Parses user input into a command.
     * @param userInput User input to be parsed.
     * @return Command object.
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid command!");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        switch (commandWord) {

            case AddTodoCommand.COMMAND_WORD:
                return new AddTodoCommand(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case AddDeadlineCommand.COMMAND_WORD:
                return prepareDeadline(arguments);

            case AddEventCommand.COMMAND_WORD:
                return prepareEvent(arguments);

            case MarkCompletedCommand.COMMAND_WORD:
                return prepareMarkCompleted(arguments);

            case MarkUncompletedCommand.COMMAND_WORD:
                return prepareMarkUncompleted(arguments);

            case FindCommand.COMMAND_WORD:
                return new FindCommand(arguments);

            case ChangeDirCommand.COMMAND_WORD:
                return new ChangeDirCommand(arguments);

            case CreateDirCommand.COMMAND_WORD:
                return new CreateDirCommand(arguments);

            case ListDirCommand.COMMAND_WORD:
                return new ListDirCommand();


            default:
                return new InvalidCommand("Invalid command!");
        }
    }

    private Command prepareDelete(String args) {
        assert args != null : "Arguments should not be null for prepareDelete method.";
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args)-1;
            return new DeleteCommand(targetIndex);
        } catch (ParseException | NumberFormatException pe) {
            return new InvalidCommand("Could not parse command.");
        }
    }

    private Command prepareMarkCompleted(String args) {
        assert args != null : "Arguments should not be null for prepareMarkCompleted method.";
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args)-1;
            return new MarkCompletedCommand(targetIndex);
        } catch (ParseException | NumberFormatException pe) {
            return new InvalidCommand("Could not parse command.");
        }
    }
    private Command prepareMarkUncompleted(String args) {
        assert args != null : "Arguments should not be null for prepareMarkUncompleted method.";
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args)-1;
            return new MarkUncompletedCommand(targetIndex);
        } catch (ParseException | NumberFormatException pe) {
            return new InvalidCommand("Could not parse command.");
        }
    }

    private Command prepareEvent(String args) {
        assert args != null : "Arguments should not be null for prepareEvent method.";
        try {
            String regex = "(.*?)\\s*/from\\s+(.+)\\s*/to\\s+(.+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(args.trim());
            if (!matcher.find()) {
                return new InvalidCommand("Invalid input for event. Input your event as such:\nevent <name_of_event> /from <start_time> /to <end_time>");
            }
            String eventName = matcher.group(1);
            return new AddEventCommand(eventName, parseDateTime(matcher.group(2).trim()), parseDateTime(matcher.group(3).trim()));
        } catch (Exception e){
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDeadline(String args) {
        assert args != null : "Arguments should not be null for prepareDeadline method.";
        try {
            String regex = "\\s+(\\S+(?:\\s+\\S+)*)\\s*/by\\s+(.+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(args);
            if (!matcher.find()) {
                return new InvalidCommand("Invalid input for deadline. Input your deadline as such:\ndeadline <name_of_deadline> /by <due_date>");
            }
            String taskName = matcher.group(1);
            LocalDateTime deadline= parseDateTime(matcher.group(2));
            return new AddDeadlineCommand(taskName, deadline);
        } catch (Exception e){
            return new InvalidCommand("Invalid input for deadline. Input your deadline as such:\ndeadline <name_of_deadline> /by <due_date>");
        }
    }

    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = List_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }
}
