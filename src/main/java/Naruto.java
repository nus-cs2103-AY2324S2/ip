import java.util.LinkedList;
import java.util.Scanner;
public class Naruto {
    private static LinkedList<Action> actions = new LinkedList<>();
    private static Store store = new Store();
    private static Scanner sc = new Scanner(System.in);
    Naruto() {
        actions.add(new Greet());
    }

    public static void listen() {
        // Tokenise input
        String cmd = sc.next();
        String[] tokens;
        switch (cmd) {
            case "bye":
                actions.addLast(new Goodbye());
                break;
            case "list":
                actions.addLast(new List(store));
                break;
            case "mark":
                actions.addLast(new Mark(store, sc.nextInt()));
                break;
            case "unmark":
                actions.addLast(new Unmark(store, sc.nextInt()));
                break;
            case "todo":
                actions.addLast(new Add(new ToDo(sc.nextLine().trim()), store));
                break;
            case "deadline":
                tokens = sc.nextLine().split("/by");
                String description = tokens[0].trim();
                String by = tokens[1].trim();
                actions.addLast(new Add(new Deadline(description, by), store));
                break;
            case "event":
                tokens = sc.nextLine().split("/from");
                description = tokens[0].trim();
                tokens = tokens[1].split("/to");
                String from = tokens[0].trim();
                String to = tokens[1].trim();
                actions.addLast(new Add(new Event(description, from, to), store));
                break;
            default:

        }

    }

    public static void act() {
        actions.pollFirst().execute();
    }

    public static boolean hasNextAction() {
        return !actions.isEmpty();
    }

}
