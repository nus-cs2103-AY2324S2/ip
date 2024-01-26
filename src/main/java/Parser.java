import javax.rmi.ssl.SslRMIClientSocketFactory;
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

    public static int parseDeleteTask(String[] words) throws UkeCatException {
        try{
            if (words.length == 2) {
                return Integer.parseInt(words[1]) - 1;
            } else {
                throw new UkeCatException("Wrong format, use: mark / unmark <task#>");
            }
        }  catch (NumberFormatException e) {
            throw new UkeCatException("Wrong format, use: mark / unmark <task#>");
        }
    }

    public static int parseMarkTask(String[] words) throws UkeCatException{
        try {
            if (words.length == 2) {
                return Integer.parseInt(words[1]) - 1;
            } else {
                throw new UkeCatException("Wrong format, use: mark / unmark <task#>");
            }
        } catch (NumberFormatException e) {
            throw new UkeCatException("Wrong format, use: mark / unmark <task#>");
        }
    }

    // ToDo: T, 0/1, desc
    // Deadline: D, 0/1, desc, by
    // Event: E, 0/1, desc, from, to
    public static String parseTaskToCsv(Task t) {
        if (t instanceof ToDo) {
            return String.format("T,%d,%s", t.getIntIsDone(), t.getDescription());
        } else if (t instanceof Deadline) {
            Deadline x = (Deadline) t;
            return String.format("D,%d,%s,%s", t.getIntIsDone(), t.getDescription(), x.getBy());
        } else { // instanceof Event
            Event x = (Event) t;
            return String.format("E,%d,%s,%s,%s", t.getIntIsDone(), t.getDescription(),
                    x.getStart(), x.getEnd());
        }
    }

    public static void parseCsv(String csv) {
        String[] words = csv.split(",");
        Storage.words = words;
        try {
            switch (words[0]) {
            case ("T"):
                Storage.words[0] = "todo";
                Storage.input = String.format("todo %s", words[2]);
                parseToDo(Storage.input);
                break;
            case ("D"):
                Storage.words[0] = "deadline";
                Storage.input = String.format("deadline %s /by %s", words[2], words[3]);
                parseDeadline(Storage.input);
                break;
            case ("E"):
                Storage.words[0] = "event";
                Storage.input = String.format("deadline %s /from %s to %s", words[2], words[3], words[4]);
                parseEvent(Storage.input);
                break;
            default:
                System.out.println("Can't read csv");
            }
        } catch (UkeCatException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void closeScanner() {
        sc.close();
    }
}
