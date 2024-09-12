package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser class used to parse user input
 */
public class Parser {
    /**
     * Static method to parse user input and return the recognised commands.
     * @param input User input to be parsed.
     * @return Command instance to be executed.
     * @throws DukeException If command has empty arguments, or user input is unrecognisable
     */
    public static Command parse(String input) throws DukeException {
        // Simple commands
        if (input.matches("list(\\s*)")) {
            return new ListCommand();
        }
        if (input.matches("bye(\\s*)")) {
            return new ExitCommand();
        }

        String todoRegex = "todo (\\S.*)";
        String deadlineRegex = "deadline (\\S.*) /by (\\S.*)";
        String eventRegex = "event (\\S.*) /from (\\S.*) /to (\\S.*)";
        String markRegex = "mark (\\d+)";
        String unmarkRegex = "unmark (\\d+)";
        String deleteRegex = "delete (\\d+)";
        String findRegex = "find (\\S.*)";
        String tagRegex = "tag (\\d+) (\\S.*)";

        // Regex for simple error of empty description
        String todoErrorRegex = "todo\\s*";
        String deadlineErrorRegex = "deadline\\s*";
        String eventErrorRegex = "event\\s*";
        String markErrorRegex = "mark\\s*";
        String unmarkErrorRegex = "unmark\\s*";
        String deleteErrorRegex = "delete\\s*";
        String findErrorRegex = "find\\s*";

        Matcher todoMatcher = Pattern.compile(todoRegex).matcher(input);
        Matcher deadlineMatcher = Pattern.compile(deadlineRegex).matcher(input);
        Matcher eventMatcher = Pattern.compile(eventRegex).matcher(input);
        Matcher markMatcher = Pattern.compile(markRegex).matcher(input);
        Matcher unmarkMatcher = Pattern.compile(unmarkRegex).matcher(input);
        Matcher deleteMatcher = Pattern.compile(deleteRegex).matcher(input);
        Matcher findMatcher = Pattern.compile(findRegex).matcher(input);
        Matcher tagMatcher = Pattern.compile(tagRegex).matcher(input);

        Matcher todoErrorMatcher = Pattern.compile(todoErrorRegex).matcher(input);
        Matcher deadlineErrorMatcher = Pattern.compile(deadlineErrorRegex).matcher(input);
        Matcher eventErrorMatcher = Pattern.compile(eventErrorRegex).matcher(input);
        Matcher markErrorMatcher = Pattern.compile(markErrorRegex).matcher(input);
        Matcher unmarkErrorMatcher = Pattern.compile(unmarkErrorRegex).matcher(input);
        Matcher deleteErrorMatcher = Pattern.compile(deleteErrorRegex).matcher(input);
        Matcher findErrorMatcher = Pattern.compile(findErrorRegex).matcher(input);

        if (todoMatcher.matches()) {
            return new AddCommand(new Todo(todoMatcher.group(1)));
        } else if (deadlineMatcher.matches()) {
            return new AddCommand(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
        } else if (eventMatcher.matches()) {
            return new AddCommand(new Event(eventMatcher.group(1), eventMatcher.group(2), eventMatcher.group(3)));
        } else if (unmarkMatcher.matches()) {
            return new MarkCommand(Integer.parseInt(unmarkMatcher.group(1)), false);
        } else if (markMatcher.matches()){
            return new MarkCommand(Integer.parseInt(markMatcher.group(1)), true);
        } else if (deleteMatcher.matches()) {
            return new DeleteCommand(Integer.parseInt(deleteMatcher.group(1)));
        } else if (findMatcher.matches()) {
            return new FindCommand(findMatcher.group(1));
        } else if (tagMatcher.matches()) {
            return new TagCommand(Integer.parseInt(tagMatcher.group(1)), tagMatcher.group(2));
        } else if (todoErrorMatcher.matches() || deadlineErrorMatcher.matches() || eventErrorMatcher.matches()
            || markErrorMatcher.matches() || unmarkErrorMatcher.matches() || deleteErrorMatcher.matches() || findErrorMatcher.matches()) {
            throw new DukeException("Empty command description");
        } else {
            throw new DukeException("sry idk what that means");
        }
    }
}