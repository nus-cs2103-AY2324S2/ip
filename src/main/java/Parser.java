import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // Contains information about each command parsed
    private String commandType = "", name, arg1, arg2;

    public String[] getCommandInfo() {
        return new String[] {commandType, name, arg1, arg2};
    }

    public void parseCommand(String input) throws IllegalArgumentException, EmptyCommandDescription {
        // Simple commands
        if (input.matches("list(\\s*)")) {
            this.commandType = "LIST";
            return;
        }
        if (input.matches("bye(\\s*)")) {
            this.commandType = "BYE";
            return;
        }

        String todoRegex = "todo (\\S.*)";
        String deadlineRegex = "deadline (\\S.*) /by (\\S.*)";
        String eventRegex = "event (\\S.*) /from (\\S.*) /to (\\S.*)";
        String markRegex = "mark (\\d+)";
        String unmarkRegex = "unmark (\\d+)";
        String deleteRegex = "delete (\\d+)";

        // Regex for simple error of empty description
        String todoErrorRegex = "todo\\s*";
        String deadlineErrorRegex = "deadline\\s*";
        String eventErrorRegex = "event\\s*";
        String markErrorRegex = "mark\\s*";
        String unmarkErrorRegex = "unmark\\s*";
        String deleteErrorRegex = "delete\\s*";

        Matcher todoMatcher = Pattern.compile(todoRegex).matcher(input);
        Matcher deadlineMatcher = Pattern.compile(deadlineRegex).matcher(input);
        Matcher eventMatcher = Pattern.compile(eventRegex).matcher(input);
        Matcher markMatcher = Pattern.compile(markRegex).matcher(input);
        Matcher unmarkMatcher = Pattern.compile(unmarkRegex).matcher(input);
        Matcher deleteMatcher = Pattern.compile(deleteRegex).matcher(input);

        Matcher todoErrorMatcher = Pattern.compile(todoErrorRegex).matcher(input);
        Matcher deadlineErrorMatcher = Pattern.compile(deadlineErrorRegex).matcher(input);
        Matcher eventErrorMatcher = Pattern.compile(eventErrorRegex).matcher(input);
        Matcher markErrorMatcher = Pattern.compile(markErrorRegex).matcher(input);
        Matcher unmarkErrorMatcher = Pattern.compile(unmarkErrorRegex).matcher(input);
        Matcher deleteErrorMatcher = Pattern.compile(deleteErrorRegex).matcher(input);

        if (todoMatcher.matches()) {
            this.commandType = "TODO";
            this.name = todoMatcher.group(1);
        } else if (deadlineMatcher.matches()) {
            this.commandType = "DEADLINE";
            this.name = deadlineMatcher.group(1);
            this.arg1 = deadlineMatcher.group(2);
        } else if (eventMatcher.matches()) {
            this.commandType = "EVENT";
            this.name = eventMatcher.group(1);
            this.arg1 = eventMatcher.group(2);
            this.arg2 = eventMatcher.group(3);
        } else if (unmarkMatcher.matches()) {
            this.commandType = "UNMARK";
            this.arg1 = unmarkMatcher.group(1);
        } else if (markMatcher.matches()){
            this.commandType = "MARK";
            this.arg1 = markMatcher.group(1);
        } else if (deleteMatcher.matches()) {
            this.commandType = "DELETE";
            this.arg1 = deleteMatcher.group(1);
        } else if (todoErrorMatcher.matches() || deadlineErrorMatcher.matches() || eventErrorMatcher.matches()
            || markErrorMatcher.matches() || unmarkErrorMatcher.matches() || deleteErrorMatcher.matches()) {
            throw new EmptyCommandDescription();
        } else {
            throw new IllegalArgumentException();
        }
    }
}