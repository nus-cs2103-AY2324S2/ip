import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String parseTodo(String input) {
        Pattern pattern = Pattern.compile("^todo ([^ ].*)$");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new InputMismatchException("     Input format unsupported.\n"
                    + "     Use this format: todo <task>");
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
            throw new InputMismatchException("     Input format unsupported.\n"
                    + "     Use this format: deadline <task> /by <time>");
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
            throw new InputMismatchException("     Input format unsupported.\n"
                    + "     Use this format: event <task> /from <time> /to <time>");
        }
        return ret;
    }
}
