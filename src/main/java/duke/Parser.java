package duke;

import duke.commands.*;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static HashMap<String, String> splitInput(String input) {
        HashMap<String, String> res = new HashMap<>();
        String argRegex = "(?<=/%s\\s)(.*?)(?=\\s/|$)";

        String[] splitInput = input.split(" ", 2);

        res.put("command", input.split(" ", 2)[0]);

        if (splitInput.length == 1) {
            return res;
        }

        res.put("content", splitInput[1].split(" /")[0]);

        Pattern byPattern = Pattern.compile(String.format(argRegex, "by"));
        Pattern fromPattern = Pattern.compile(String.format(argRegex, "from"));
        Pattern toPattern = Pattern.compile(String.format(argRegex, "to"));

        Matcher byMatcher = byPattern.matcher(input);
        Matcher fromMatcher = fromPattern.matcher(input);
        Matcher toMatcher = toPattern.matcher(input);

        if (byMatcher.find()) {
            res.put("by", byMatcher.group());
        }

        if (fromMatcher.find()) {
            res.put("from", fromMatcher.group());
        }

        if (toMatcher.find()) {
            res.put("to", toMatcher.group());
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
        case FindCommand.COMMAND_WORD:
            return new FindCommand(inputs.get("content"));
        default:
            return new DefaultCommand();
        }
    }
}
