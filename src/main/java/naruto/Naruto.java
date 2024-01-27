package naruto;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import action.Action;
import action.Goodbye;
import action.Add;
import action.Delete;
import action.Greet;
import action.HandleError;
import action.List;
import action.Mark;
import action.Unmark;
import exception.NarutoException;
import task.Deadline;
import task.Event;
import task.ToDo;
import util.*;

public class Naruto {
    private static LinkedList<Action> actions = new LinkedList<>();
    private static Store store;

    static {
        try {
            store = new Store();
        } catch (IOException e) {
            PrintUtil.print(NarutoException.createFileCorruptedException().getMessage());
        }
    }

    private static Scanner sc = new Scanner(System.in);

    public Naruto() {
        actions.add(new Greet());
    }

    public static void listen() {
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
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            actions.addLast(new Mark(store, idx));
            break;
        case "unmark":
            idx = sc.nextInt();
            if (idx > store.getSize() || idx <= 0) {
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            actions.addLast(new Unmark(store, idx));
            break;
        case "todo":
            description = sc.nextLine().trim();
            if (description.isEmpty()) {
                handleException(NarutoException.createEmptyTodoException());
                return;
            }
            actions.addLast(new Add(new ToDo(description), store));
            break;
        case "deadline":
            input = sc.nextLine();
            if (input.isEmpty()) {
                handleException(NarutoException.createEmptyDeadlineException());
                return;
            }
            if (!input.contains("/by")) {
                handleException(NarutoException.createInvalidDeadlineException());
                return;
            }
            tokens = input.split("/by");
            description = tokens[0].trim();
            String by = tokens[1].trim();
            actions.addLast(new Add(new Deadline(description, by), store));
            break;
        case "event":
            input = sc.nextLine();
            if (input.isEmpty()) {
                handleException(NarutoException.createEmptyEventException());
                return;
            }
            if (!(input.contains("/from") && input.contains("/to"))) {
                handleException(NarutoException.createInvalidEventException());
                return;
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
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            actions.addLast(new Delete(store, idx));
            break;
        default:
            handleException(NarutoException.createInvalidCommandException());
        }

    }

    public static void act() {
        try {
            actions.pollFirst().execute();
        } catch (IOException e) {
            handleException(NarutoException.createInvalidActionException());
        }
    }

    public static boolean hasNextAction() {
        return !actions.isEmpty();
    }

    public static void handleException(NarutoException err) {
        actions.addLast(new HandleError(err));
    }

}
