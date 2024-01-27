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
    private static TaskList taskList;

    static {
        try {
            taskList = new TaskList();
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
            actions.addLast(new List(taskList));
            break;
        case "mark":
            input = sc.next();
            try {
                idx = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            if (idx > taskList.getSize() || idx <= 0) {
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            actions.addLast(new Mark(taskList, idx));
            break;
        case "unmark":
            input = sc.next();
            try {
                idx = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            if (idx > taskList.getSize() || idx <= 0) {
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            actions.addLast(new Unmark(taskList, idx));
            break;
        case "todo":
            description = sc.nextLine().trim();
            if (description.isEmpty()) {
                handleException(NarutoException.createEmptyTodoException());
                return;
            }
            actions.addLast(new Add(new ToDo(description), taskList));
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
            if (!DateTimeUtil.isValid(by)) {
                handleException(NarutoException.createInvalidDeadlineException());
                return;
            }
            actions.addLast(new Add(new Deadline(description, DateTimeUtil.format(by)), taskList));
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
            if (!DateTimeUtil.isValid(from) || !DateTimeUtil.isValid(to)) {
                handleException(NarutoException.createInvalidDeadlineException());
                return;
            }
            actions.addLast(new Add(new Event(description, DateTimeUtil.format(from),
                DateTimeUtil.format(to)),
                taskList));
            break;
        case "delete":
            input = sc.next();
            try {
                idx = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            if (idx > taskList.getSize() || idx <= 0) {
                handleException(NarutoException.createInvalidIndexException());
                return;
            }
            actions.addLast(new Delete(taskList, idx));
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
