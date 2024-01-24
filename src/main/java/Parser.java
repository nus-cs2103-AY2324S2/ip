import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Takes in user input
// Analyses input using regex
// Stores information on the input in the storage
public class Parser {
    private static Scanner sc = new Scanner(System.in);

    public static void parse() {
        // trim removes leading and trailing whitespaces
        Storage.input = (sc.nextLine().trim());
        // whitespace regex is //s, the + means whitespace of any length
        Storage.words = Storage.input.split("\\s+");

    }

    public static void parseToDo(String input) throws UkeCatException {
        // Regex pattern: todo + whitespaces + any chars
        Pattern pattern = Pattern.compile("^todo\\s+(.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            Storage.desc = matcher.group(1); // store first capturing group
        } else {
            throw new UkeCatException("Wrong format, use: todo <desc>");
        }
    }

    public static int parseMarkTask(String[] words) throws UkeCatException{
        try {
            if (words.length == 2) {
                if (words[0].equals("mark") || words[0].equals("unmark")) {
                    int taskNum = Integer.parseInt(words[1]) - 1;
                    return taskNum;
                }
            } else {
                throw new UkeCatException("Wrong format, use: mark / unmark <task#>");
            }
        } catch (NumberFormatException e) {
            throw new UkeCatException("Wrong format, use: mark / unmark <task#>");
        }
        return -1;
    }

    public static void parseDeadline(String input) throws UkeCatException {
        // Regex pattern: deadline + spaces + chars + spaces + /by + spaces + chars
        Pattern pattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            Storage.desc = matcher.group(1);
            Storage.by = matcher.group(2);
        } else {
            throw new UkeCatException("Wrong format, use: deadline <desc> /by <by>");
        }
    }

    public static void parseEvent(String input) throws UkeCatException {
        // Regex pattern: event + spaces + chars + spaces +
        // /from + spaces + chars + spaces + /to + spaces + chars
        Pattern pattern = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            Storage.desc = matcher.group(1);
            Storage.start = matcher.group(2);
            Storage.end = matcher.group(3);
        } else {
            throw new UkeCatException("Wrong format, use: event <desc> /from <start> /to <end>");
        }
    }

    public static void closeScanner() {
        sc.close();
    }
}
