package util;

import action.Action;
import action.Add;
import action.Delete;
import action.Find;
import action.Goodbye;
import action.HandleError;
import action.List;
import action.Mark;
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
        String description;
        int idx;
        assert restOfLine != null;
        switch (input) {
        case "bye":
            return new Goodbye();
        case "list":
            return new List(taskList);
        case "mark":
            try {
                idx = Parser.parseIdx(restOfLine, taskList);
            } catch (NarutoException e) {
                return new HandleError(e);
            }
            return new Mark(taskList, idx);
        case "unmark":
            try {
                idx = Parser.parseIdx(restOfLine, taskList);
            } catch (NarutoException e) {
                return new HandleError(e);
            }
            return new Unmark(taskList, idx);
        case "todo":
            try {
                description = Parser.parseDescription(restOfLine);
            } catch (NarutoException e) {
                return new HandleError(e);
            }
            return new Add(new ToDo(description), taskList);
        case "deadline":
            try {
                tokens = Parser.parseDeadline(restOfLine);
            } catch (NarutoException e) {
                return new HandleError(e);
            }
            return new Add(new Deadline(tokens[0], DateTimeUtil.format(tokens[1])), taskList);
        case "event":
            try {
                tokens = Parser.parseEvent(restOfLine);
            } catch (NarutoException e) {
                return new HandleError(e);
            }
            return new Add(new Event(tokens[0], DateTimeUtil.format(tokens[1]),
                DateTimeUtil.format(tokens[2])), taskList);
        case "delete":
            try {
                idx = Parser.parseIdx(restOfLine, taskList);
            } catch (NarutoException e) {
                return new HandleError(e);
            }
            return new Delete(taskList, idx);
        case "find":
            try {
                tokens = Parser.parseDescription(restOfLine).split(" ");
            } catch (NarutoException e) {
                return new HandleError(e);
            }
            return new Find(tokens);
        default:
            return new HandleError(NarutoException.createInvalidCommandException());
        }
    }
}
