package util;

import action.*;
import action.List;
import exception.*;
import task.*;

import java.util.*;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

    private Ui() {

    }

    public static Action parseInput(TaskList taskList) {
        String input = sc.next();

        // Pass the rest of the line to the Parser
        String restOfLine = sc.nextLine();
        // Tokenise input
        String[] tokens;
        String description;
        int idx;
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
        default:
            return new HandleError(NarutoException.createInvalidCommandException());
        }
    }
}
