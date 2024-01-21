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
        String s = sc.nextLine();
        switch (s) {
            case "bye":
                actions.addLast(new Goodbye());
                break;
            case "list":
                actions.addLast(new List(store));
                break;
            default:
                actions.addLast(new Add(s, store));
        }

    }

    public static void act() {
        actions.pollFirst().execute();
    }

    public static boolean hasNextAction() {
        return !actions.isEmpty();
    }

}
