package junjie;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junjie.commands.Command;
import junjie.commands.DeadlineCommand;
import junjie.commands.DefaultCommand;
import junjie.commands.DeleteCommand;
import junjie.commands.EventCommand;
import junjie.commands.ExitCommand;
import junjie.commands.FindCommand;
import junjie.commands.ListCommand;
import junjie.commands.MarkCommand;
import junjie.commands.TodoCommand;
import junjie.commands.UnmarkCommand;

/**
 * Parser class is responsible for parsing user input and returning a Command object.
 */
public class Parser {

    /**
     * Splits user input into a HashMap.
     * @param input user input
     * @return HashMap containing the command and its content
     */
    public static HashMap<String, String> splitInput(String input) {
        assert input != null : "Input is null";

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
        Pattern tagPattern = Pattern.compile(String.format(argRegex, "tag"));

        Matcher byMatcher = byPattern.matcher(input);
        Matcher fromMatcher = fromPattern.matcher(input);
        Matcher toMatcher = toPattern.matcher(input);
        Matcher tagMatcher = tagPattern.matcher(input);

        if (byMatcher.find()) {
            res.put("by", byMatcher.group());
        }

        if (fromMatcher.find()) {
            res.put("from", fromMatcher.group());
        }

        if (toMatcher.find()) {
            res.put("to", toMatcher.group());
        }

        if (tagMatcher.find()) {
            res.put("tag", tagMatcher.group());
        }

        return res;
    }

    /**
     * Splits tags into an array of strings.
     * @param input user input
     * @return array of strings containing tags
     */
    public static String[] splitTags(String input) {
        if (input == null) {
            return new String[0];
        }
        return input.split(" ");
    }

    /**
     * Parses user input and returns a Command object.
     * @param input user input
     * @param ui Ui object
     * @param taskList TaskList object
     * @return Command object
     */
    public static Command handleInput(String input, Ui ui, TaskList taskList) {
        assert input != null : "Input is null";
        assert ui != null : "Ui is null";
        assert taskList != null : "TaskList is null";

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
            return new TodoCommand(inputs.get("content"), splitTags(inputs.get("tag")));
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(inputs.get("content"), inputs.get("by"), splitTags(inputs.get("tag")));
        case EventCommand.COMMAND_WORD:
            return new EventCommand(inputs.get("content"), inputs.get("from"), inputs.get("to"), splitTags(inputs.get("tag")));
        case FindCommand.COMMAND_WORD:
            return new FindCommand(inputs.get("content"));
        default:
            return new DefaultCommand();
        }
    }
}
