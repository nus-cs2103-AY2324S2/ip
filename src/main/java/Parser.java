import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Action parseAction(String input, String[] words) {
        if (input.isEmpty()) {
            return Action.NONE;
        }
        if (input.equals("list")) {
            return Action.LIST;
        }
        if (input.equals("bye")) {
            return Action.BYE;
        }
        if (words[0].equals("mark")) {
            return Action.MARK;
        }
        if (words[0].equals("unmark")) {
            return Action.UNMARK;
        }
        if (words[0].equals("delete")) {
            return Action.DELETE;
        }
        if (words[0].equals("todo")) {
            return Action.ADD_TODO;
        }
        if (words[0].equals("deadline")) {
            return Action.ADD_DEADLINE;
        }
        if (words[0].equals("event")) {
            return Action.ADD_EVENT;
        }
        return Action.INVALID;
    }

    public static String parseTodo(String input) {
        Pattern pattern = Pattern.compile("^todo ([^ ].*)$");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new InputMismatchException();
        }
        return matcher.group(1);
    }

    public static String[] parseDeadline(String input) {
        String[] ret = new String[3];
        Pattern pattern = Pattern.compile("^deadline ([^ ].*) /by ([^ ].*)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            ret[0] = matcher.group(1);
            ret[1] = matcher.group(2);
        } else {
            throw new InputMismatchException();
        }
        return ret;
    }

    public static String[] parseEvent(String input) {
        String[] ret = new String[3];
        Pattern pattern = Pattern.compile("^event ([^ ].*) /from ([^ ].*) /to ([^ ].*)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            ret[0] = matcher.group(1);
            ret[1] = matcher.group(2);
            ret[2] = matcher.group(3);
        } else {
            throw new InputMismatchException();
        }
        return ret;
    }
}
