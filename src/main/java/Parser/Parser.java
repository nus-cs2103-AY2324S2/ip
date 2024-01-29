package Parser;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Commands.*;
import Exceptions.InvalidInputException;
import TaskList.Tasks.Deadline;
import TaskList.Tasks.Event;
import static Utils.StringUtils.parseDateTime;

import static Utils.StringUtils.parseDateTime;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern List_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid command!");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

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

            default:
                return new InvalidCommand("Invalid command!");
        }
    }
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args)-1;
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            return new InvalidCommand("Could not parse command.");
        } catch (NumberFormatException nfe) {
            return new InvalidCommand("Could not parse command.");
        }
    }

    private Command prepareMarkCompleted(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args)-1;
            return new MarkCompletedCommand(targetIndex);
        } catch (ParseException pe) {
            return new InvalidCommand("Could not parse command.");
        } catch (NumberFormatException nfe) {
            return new InvalidCommand("Could not parse command.");
        }
    }
    private Command prepareMarkUncompleted(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args)-1;
            return new MarkUncompletedCommand(targetIndex);
        } catch (ParseException pe) {
            return new InvalidCommand("Could not parse command.");
        } catch (NumberFormatException nfe) {
            return new InvalidCommand("Could not parse command.");
        }
    }

    private Command prepareEvent(String args) {
        try {
            String regex = "(.*?)\\s*/from\\s+(.+)\\s*/to\\s+(.+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(args.trim());
            if (matcher.find()) {
                String eventName = matcher.group(1);
                return new AddEventCommand(eventName, parseDateTime(matcher.group(2).trim()), parseDateTime(matcher.group(3).trim()));
            } else {
                return new InvalidCommand("Invalid input for event. Input your event as such:\nevent <name_of_event> /from <start_time> /to <end_time>");
            }
        } catch (Exception e){
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command prepareDeadline(String args) {
        try {
            String regex = "\\s+(\\S+(?:\\s+\\S+)*)\\s*/by\\s+(.+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(args);
            if (matcher.find()) {
                String taskName = matcher.group(1);
                LocalDateTime deadline= parseDateTime(matcher.group(2));
                return new AddDeadlineCommand(taskName, deadline);
            } else {
                return new InvalidCommand("Invalid input for deadline. Input your deadline as such:\ndeadline <name_of_deadline> /by <due_date>");
            }
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
