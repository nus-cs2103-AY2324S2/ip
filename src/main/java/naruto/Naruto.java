package naruto;
import java.util.LinkedList;
import java.util.Scanner;
import action.*;
import exception.NarutoException;
import task.*;
import util.Store;

public class Naruto {
    private static LinkedList<Action> actions = new LinkedList<>();
    private static Store store = new Store();
    private static Scanner sc = new Scanner(System.in);
    public Naruto() {
        actions.add(new Greet());
    }

    public static void listen() throws NarutoException {
        // Tokenise input
        String input = sc.next();
        String[] tokens;
        String description;
        int idx;
        switch (input) {
            case "bye":
                actions.addLast(new Goodbye());
                break;
            case "list":
                actions.addLast(new List(store));
                break;
            case "mark":
                idx = sc.nextInt();
                if (idx > store.getSize() || idx <= 0) {
                    throw NarutoException.createInvalidIndexException();
                }
                actions.addLast(new Mark(store, idx));
                break;
            case "unmark":
                idx = sc.nextInt();
                if (idx > store.getSize() || idx <= 0) {
                    throw NarutoException.createInvalidIndexException();
                }
                actions.addLast(new Unmark(store, idx));
                break;
            case "todo":
                description = sc.nextLine().trim();
                if (description.isEmpty()) {
                    throw NarutoException.createEmptyTodoException();
                }
                actions.addLast(new Add(new ToDo(description), store));
                break;
            case "deadline":
                input = sc.nextLine();
                if (input.isEmpty()) {
                    throw NarutoException.createEmptyDeadlineException();
                }
                if (!input.contains("/by")) {
                    throw NarutoException.createInvalidDeadlineException();
                }
                tokens = input.split("/by");
                description = tokens[0].trim();
                String by = tokens[1].trim();
                actions.addLast(new Add(new Deadline(description, by), store));
                break;
            case "event":
                input = sc.nextLine();
                if (input.isEmpty()) {
                    throw NarutoException.createEmptyEventException();
                }
                if (!(input.contains("/from") && input.contains("/to"))) {
                    throw NarutoException.createInvalidEventException();
                }
                tokens = input.split("/from");
                description = tokens[0].trim();
                tokens = tokens[1].split("/to");
                String from = tokens[0].trim();
                String to = tokens[1].trim();
                actions.addLast(new Add(new Event(description, from, to), store));
                break;
            case "delete":
                idx = sc.nextInt();
                if (idx > store.getSize() || idx <= 0) {
                    throw NarutoException.createInvalidIndexException();
                }
                actions.addLast(new Delete(store, idx));
                break;
            default:
                throw NarutoException.createInvalidCommandException();

        }

    }

    public static void act() {
        actions.pollFirst().execute();
    }

    public static boolean hasNextAction() {
        return !actions.isEmpty();
    }

    public static void handleException(NarutoException err) {
        actions.addLast(new HandleError(err));

    }

}
