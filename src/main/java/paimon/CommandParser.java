package paimon;

import paimon.command.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private String input;
    private String type;

    public CommandParser(String input) {
        this.input = input;
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex != -1) {
            this.type = input.substring(0, firstSpaceIndex);
        } else {
            this.type = input;
        }
    }

    public String getType() {
        return this.type;
    }

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
            default:
                throw new ChatException("Command not found, type help for a list of commands");
        }
    }
}
