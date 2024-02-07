package duke;

import command.*;

abstract class Parser {
    static Command parse(String prompt) throws DukeException {
        String adjustedPrompt = prompt.replaceAll("\\s+", " ");
        String[] order = adjustedPrompt.split(" ");
        DukeException e = new DukeException("Sorry! The prompt has ambiguious meanings...");
        switch (order[0]) {
        case "list":
            if (order.length > 1) {
                throw e;
            }
            return new ListCommand();
        case "mark":
            if (order.length > 2) {
                throw e;
            }
            try {
                return new MarkCommand(Integer.parseInt(order[1]));
            } catch (NumberFormatException f) {
                throw e;
            }
        case "unmark":
            if (order.length > 2) {
                throw e;
            }
            try {
                return new UnmarkCommand(Integer.parseInt(order[1]));
            } catch (NumberFormatException f) {
                throw e;
            }
        case "delete":
            if (order.length > 2) {
                throw e;
            }
            try {
                return new DeleteCommand(Integer.parseInt(order[1]));
            } catch (NumberFormatException f) {
                throw e;
            }
        case "search":
            if (order.length > 2) {
                throw e;
            }
            return new SearchDateCommand(order[1]);
        case "exit":
        case "bye":
            if (order.length > 1) {
                throw e;
            }
            return new ExitCommand();
        default:
            return new AddTaskCommand(adjustedPrompt);
        }
    }
}
