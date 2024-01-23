import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // Contains information about each command parsed
    private String commandType, name, arg1, arg2;

    public String[] getCommandInfo() {
        return new String[] {commandType, name, arg1, arg2};
    }

    public void parseCommand(String input) {
        // Simple commands
        if (input.matches("list(\\s*)")) {
            this.commandType = "LIST";
            return;
        }
        if (input.matches("bye(\\s*)")) {
            this.commandType = "BYE";
            return;
        }

        String todoRegex = "todo (.+)";
        String deadlineRegex = "deadline (.+) /by (.+)";
        String eventRegex = "event (.+) /from (.+) /to (.+)";
        String markRegex = "mark (\\d+)";
        String unmarkRegex = "unmark (\\d+)";

        Matcher todoMatcher = Pattern.compile(todoRegex).matcher(input);
        Matcher deadlineMatcher = Pattern.compile(deadlineRegex).matcher(input);
        Matcher eventMatcher = Pattern.compile(eventRegex).matcher(input);
        Matcher markMatcher = Pattern.compile(markRegex).matcher(input);
        Matcher unmarkMatcher = Pattern.compile(unmarkRegex).matcher(input);

        if (todoMatcher.find()) {
            this.commandType = "TODO";
            this.name = todoMatcher.group(1);
        } else if (deadlineMatcher.find()) {
            this.commandType = "DEADLINE";
            this.name = deadlineMatcher.group(1);
            this.arg1 = deadlineMatcher.group(2);
        } else if (eventMatcher.find()) {
            this.commandType = "EVENT";
            this.name = eventMatcher.group(1);
            this.arg1 = eventMatcher.group(2);
            this.arg2 = eventMatcher.group(3);
        } else if (unmarkMatcher.find()) {
            this.commandType = "UNMARK";
            this.arg1 = unmarkMatcher.group(1);
        } else if (markMatcher.find()){
            this.commandType = "MARK";
            this.arg1 = markMatcher.group(1);
        } else {
            this.commandType = "INVALID";
        }
    }
}