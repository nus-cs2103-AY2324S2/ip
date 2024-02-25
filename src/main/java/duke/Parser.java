package duke;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import duke.exceptions.InvalidCmdException;

/**
 * Interprets a <code>String</code> input from the command-line and formats the main command
 *     and arguments.
 */
public class Parser {
    private static final HashMap<String, List<String>> CMD_LIST = new HashMap<>() {{
            put("todo", Arrays.asList("todo"));
            put("deadline", Arrays.asList("deadline", "/by"));
            put("event", Arrays.asList("event", "/from", "/to"));
            put("list", null);
            put("mark", Arrays.asList("mark"));
            put("unmark", Arrays.asList("unmark"));
            put("delete", Arrays.asList("delete"));
            put("find", Arrays.asList("find"));
            put("lend", Arrays.asList("lend", "/amount"));
            put("owe", Arrays.asList("owe", "/amount"));
            put("paid", Arrays.asList("paid"));
            put("bye", null);
        }};

    /**
     * Returns a Command class for execution after parsing the command
     * string with relevant arguments.
     *
     * @param input String to parse into <code>Command</code>.
     * @return Command class for execution.
     * @throws InvalidCmdException If command is unknown, or if parameters / arguments are
     *     missing.
     */
    protected static Command parse(String input) throws InvalidCmdException {
        String verifiedCommand = verifyMainCommand(input);
        List<String> parameters = Parser.CMD_LIST.get(verifiedCommand);
        return new Command(verifiedCommand, getArguments(parameters, input));
    }

    private static String verifyMainCommand(String input) throws InvalidCmdException {
        String[] token = input.split(" ");
        String possibleCommand = token[0];
        if (!Parser.CMD_LIST.containsKey(possibleCommand)) {
            throw new InvalidCmdException("Unknown command, please try again.");
        }
        return possibleCommand;
    }

    private static String[] getArguments(List<String> parameters, String input) throws InvalidCmdException {
        String delimiter = "";
        if (parameters != null) {
            int count = 0;
            for (String param : parameters) {
                if (!input.contains(param)) {
                    throw new InvalidCmdException("Missing parameter: " + param);
                }
                if (count == 0) {
                    delimiter = delimiter.concat(param + " ");
                } else {
                    delimiter = delimiter.concat("| \\" + param + " ");
                }
                count++;
            }
            String[] token = input.split(delimiter);
            if (token.length != parameters.size() + 1) {
                throw new InvalidCmdException("Missing arguments in parameters.");
            }
            return Arrays.copyOfRange(token, 1, token.length);
        }
        return new String[0];
    }
}
