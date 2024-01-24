import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static boolean isMark(String[] words) {
        return words.length == 2 && words[0].equals("mark") && isNumeric(words[1]);
    }

    public static boolean isUnmark(String[] words) {
        return words.length == 2 && words[0].equals("unmark") && isNumeric(words[1]);
    }

    private static boolean isNumeric(String string) {
//        Pattern pattern = Pattern.compile("^-?\\d+$");
//        Matcher matcher = pattern.matcher(string);
//        return matcher.find();
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static String parseTodo(String input) {
        return input.replaceFirst("todo ", "");
    }

    public static String[] parseDeadline(String input) {
        String content = input.replaceFirst("deadline ", "");
        return content.split(" /by ");
    }

    public static String[] parseEvent(String input) {
        String[] ret = new String[3];
        Pattern pattern = Pattern.compile("^event (.+)/from (.+) /to (.+)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            ret[0] = matcher.group(1);
            ret[1] = matcher.group(2);
            ret[2] = matcher.group(3);
        }
        return ret;
    }
}
