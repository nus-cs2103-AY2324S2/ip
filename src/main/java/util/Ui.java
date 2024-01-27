package util;

import action.*;
import action.List;
import exception.*;
import task.*;

import java.util.*;

import static naruto.Naruto.*;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

    private Ui() {

    }

    public static Action parseInput(TaskList taskList) {
        // Tokenise input
        String input = sc.next();
        String[] tokens;
        String description;
        int idx;
        switch (input) {
        case "bye":
            return new Goodbye();
        case "list":
            return new List(taskList);
        case "mark":
            input = sc.next();
            try {
                idx = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return new HandleError(NarutoException.createInvalidIndexException());
            }
            if (idx > taskList.getSize() || idx <= 0) {
                return new HandleError(NarutoException.createInvalidIndexException());
            }
            return new Mark(taskList, idx);
        case "unmark":
            input = sc.next();
            try {
                idx = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return new HandleError(NarutoException.createInvalidIndexException());
            }
            if (idx > taskList.getSize() || idx <= 0) {
                return new HandleError(NarutoException.createInvalidIndexException());
            }
            return new Unmark(taskList, idx);
        case "todo":
            description = sc.nextLine().trim();
            if (description.isEmpty()) {
                return new HandleError(NarutoException.createEmptyTodoException());
            }
            return new Add(new ToDo(description), taskList);
        case "deadline":
            input = sc.nextLine();
            if (input.isEmpty()) {
                return new HandleError(NarutoException.createEmptyDeadlineException());
            }
            if (!input.contains("/by")) {
                return new HandleError(NarutoException.createInvalidDeadlineException());
            }
            tokens = input.split("/by");
            description = tokens[0].trim();
            String by = tokens[1].trim();
            if (!DateTimeUtil.isValid(by)) {
                return new HandleError(NarutoException.createInvalidDeadlineException());
            }
            return new Add(new Deadline(description, DateTimeUtil.format(by)), taskList);
        case "event":
            input = sc.nextLine();
            if (input.isEmpty()) {
                return new HandleError(NarutoException.createEmptyEventException());
            }
            if (!(input.contains("/from") && input.contains("/to"))) {
                return new HandleError(NarutoException.createInvalidEventException());
            }
            tokens = input.split("/from");
            description = tokens[0].trim();
            tokens = tokens[1].split("/to");
            String from = tokens[0].trim();
            String to = tokens[1].trim();
            if (!DateTimeUtil.isValid(from) || !DateTimeUtil.isValid(to)) {
                return new HandleError(NarutoException.createInvalidDeadlineException());
            }
            return new Add(new Event(description, DateTimeUtil.format(from),
                DateTimeUtil.format(to)),
                taskList);
        case "delete":
            input = sc.next();
            try {
                idx = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return new HandleError(NarutoException.createInvalidIndexException());
            }
            if (idx > taskList.getSize() || idx <= 0) {
                return new HandleError(NarutoException.createInvalidIndexException());
            }
            return new Delete(taskList, idx);
        default:
            return new HandleError(NarutoException.createInvalidCommandException());
        }
    }
}
