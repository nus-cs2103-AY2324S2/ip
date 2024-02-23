package duke;

import command.Command;
import command.AddTaskCommand;
import command.ListCommand;
import command.ExitCommand;
import command.MarkCommand;
import command.SearchDateCommand;
import command.UnmarkCommand;
import command.DeleteCommand;
import command.FindKeywordCommand;


/**
 * Represents the process of understanding user's prompts, and convert them into certain commands
 */
abstract class Parser {
    /**
     * Converts the user's prompt into commands that can be executed.
     *
     * @param prompt The user input.
     * @return Certain meaningful commands.
     * @throws DukeException if the prompt is meaningless or of incompatible format.
     */
    static Command parse(String prompt) throws DukeException {
        String adjustedPrompt = prompt.replaceAll("\\s+", " ");
        String[] order = adjustedPrompt.split(" ");
        DukeException e = new DukeException("Sorry! The prompt has ambiguious meanings...");
        switch (order[0]) {
        case "list":
            try {
                assert order.length <= 1;
            } catch (AssertionError e1) {
                throw e;
            }
            return new ListCommand();
        case "mark":
            try {
                assert order.length <= 2;
            } catch (AssertionError e1) {
                throw e;
            }
            try {
                return new MarkCommand(Integer.parseInt(order[1]));
            } catch (NumberFormatException f) {
                throw e;
            }
        case "unmark":
            try {
                assert order.length <= 2;
            } catch (AssertionError e1) {
                throw e;
            }
            try {
                return new UnmarkCommand(Integer.parseInt(order[1]));
            } catch (NumberFormatException f) {
                throw e;
            }
        case "delete":
            try {
                assert order.length <= 2;
            } catch (AssertionError e1) {
                throw e;
            }
            try {
                return new DeleteCommand(Integer.parseInt(order[1]));
            } catch (NumberFormatException f) {
                throw e;
            }
        case "view":
            try {
                assert order.length <= 2;
            } catch (AssertionError e1) {
                throw e;
            }
            return new SearchDateCommand(order[1]);
        case "search":
        case "find":
            try {
                assert order.length <= 2;
            } catch (AssertionError e1) {
                throw e;
            }
            return new FindKeywordCommand(order[1]);
        case "bye":
        case "goodbye":
        case "quit":
            try {
                assert order.length <= 1;
            } catch (AssertionError e1) {
                throw e;
            }
            return new ExitCommand();
        default:
            return new AddTaskCommand(adjustedPrompt);
        }
    }
}
