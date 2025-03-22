package util;

import action.Action;
import action.Add;
import action.Delete;
import action.Find;
import action.Goodbye;
import action.HandleError;
import action.Help;
import action.List;
import action.Mark;
import action.Sort;
import action.Unmark;
import exception.NarutoException;
import task.Deadline;
import task.Event;
import task.ToDo;

/**
 * The Ui class handles user interface related operations.
 */
public class Ui {

    private Ui() {

    }

    /**
     * Parses the user input and returns the corresponding Action object.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @return The Action object based on the user input.
     */
    public static Action parseInput(TaskList taskList, String s) {
        String[] tokens = s.split(" ", 2);
        String input = tokens[0];
        String restOfLine = tokens.length == 1 ? "" : tokens[1];
        assert restOfLine != null;
        switch (input) {
        case "bye":
        case "bb":
            return new Goodbye();
        case "list":
        case "ls":
            return parseList(taskList);
        case "mark":
        case "mk":
            return parseMark(taskList, restOfLine);
        case "unmark":
        case "um":
            return parseUnmark(taskList, restOfLine);
        case "todo":
        case "td":
            return parseTodo(taskList, restOfLine);
        case "deadline":
        case "dl":
            return parseDeadline(taskList, restOfLine);
        case "event":
        case "e":
            return parseEvent(taskList, restOfLine);
        case "delete":
        case "rm":
            return parseDelete(taskList, restOfLine);
        case "find":
        case "f":
            return parseFind(restOfLine);
        case "sort":
        case "s":
            return parseSort(taskList);
        case "help":
            return new Help();
        default:
            return new HandleError(NarutoException.createInvalidCommandException());
        }
    }

    private static Action parseList(TaskList taskList) {
        if (taskList.isEmpty()) {
            return new HandleError(NarutoException.createEmptyListException());
        }
        return new List(taskList);
    }

    private static Action parseSort(TaskList taskList) {
        if (taskList.isEmpty()) {
            return new HandleError(NarutoException.createEmptyListException());
        }
        return new Sort(taskList);
    }

    private static Action parseMark(TaskList taskList, String restOfLine) {
        try {
            int idx = Parser.parseIdx(restOfLine, taskList);
            return new Mark(taskList, idx);
        } catch (NarutoException e) {
            return new HandleError(e);
        }
    }
    private static Action parseUnmark(TaskList taskList, String restOfLine) {
        try {
            int idx = Parser.parseIdx(restOfLine, taskList);
            return new Unmark(taskList, idx);
        } catch (NarutoException e) {
            return new HandleError(e);
        }
    }
    private static Action parseTodo(TaskList taskList, String restOfLine) {
        try {
            String description = Parser.parseDescription(restOfLine);
            if (taskList.contains(new ToDo(description))) {
                return new HandleError(NarutoException.createDuplicateTaskException());
            }
            return new Add(new ToDo(description), taskList);
        } catch (NarutoException e) {
            return new HandleError(e);
        }
    }
    private static Action parseDeadline(TaskList taskList, String restOfLine) {
        try {
            String[] tokens = Parser.parseDeadline(restOfLine);
            if (taskList.contains(new Deadline(tokens[0], DateTimeUtil.format(tokens[1])))) {
                return new HandleError(NarutoException.createDuplicateTaskException());
            }
            return new Add(new Deadline(tokens[0], DateTimeUtil.format(tokens[1])), taskList);
        } catch (NarutoException e) {
            return new HandleError(e);
        }
    }
    private static Action parseEvent(TaskList taskList, String restOfLine) {
        try {
            String[] tokens = Parser.parseEvent(restOfLine);
            if (taskList.contains(new Event(tokens[0], DateTimeUtil.format(tokens[1]),
                DateTimeUtil.format(tokens[2])))) {
                return new HandleError(NarutoException.createDuplicateTaskException());
            }
            return new Add(new Event(tokens[0], DateTimeUtil.format(tokens[1]),
                DateTimeUtil.format(tokens[2])), taskList);
        } catch (NarutoException e) {
            return new HandleError(e);
        }
    }
    private static Action parseDelete(TaskList taskList, String restOfLine) {
        try {
            int idx = Parser.parseIdx(restOfLine, taskList);
            return new Delete(taskList, idx);
        } catch (NarutoException e) {
            return new HandleError(e);
        }
    }
    private static Action parseFind(String restOfLine) {
        try {
            String[] tokens = Parser.parseDescription(restOfLine).split(" ");
            return new Find(tokens);
        } catch (NarutoException e) {
            return new HandleError(e);
        }
    }
}
