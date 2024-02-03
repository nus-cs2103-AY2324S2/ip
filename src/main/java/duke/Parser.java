package duke;

import duke.commands.*;

import java.util.HashMap;

public class Parser {

    public static HashMap<String, String> splitInput(String input) {
        HashMap<String, String> res = new HashMap<>();

        String[] splitInput = input.split(" ");

        res.put("command", input.split(" ", 2)[0]);
        res.put("content", splitInput.length > 1 ? splitInput[1] : "");
        for (String s : splitInput) {
            if (s.startsWith("/by")) {
                res.put("by", s.substring(4));
            } else if (s.startsWith("/from")) {
                res.put("from", s.substring(6));
            } else if (s.startsWith("/to")) {
                res.put("to", s.substring(4));
            }
        }

        return res;
    }

    /**
     * Parses user input and returns a Command object.
     * @param input user input
     * @param ui Ui object
     * @param taskList TaskList object
     * @return Command object
     */
    public static Command handleInput(String input, Ui ui, TaskList taskList) {
        HashMap<String, String> inputs = splitInput(input);

        switch (inputs.get("command")) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(inputs.get("content"));
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(inputs.get("content"));
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(inputs.get("content"));
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(inputs.get("content"));
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(inputs.get("content"), inputs.get("by"));
        case EventCommand.COMMAND_WORD:
            return new EventCommand(inputs.get("content"), inputs.get("from"), inputs.get("to"));
        default:
            return new DefaultCommand();
        }
    }
}
