package duke;

import duke.exceptions.InvalidCmdException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        put("bye", null);
    }};
    protected static Command parse(String cmd) throws InvalidCmdException {
        String[] token = cmd.split(" ");
        if (!Parser.CMD_LIST.containsKey(token[0])) {
            throw new InvalidCmdException("Unknown command, please try again.");
        }
        String actualCmd = token[0];
        List<String> parameters = Parser.CMD_LIST.get(actualCmd);
        String delimiter = "";
        if (parameters != null) {
            int count = 0;
            for (String param : parameters) {
                if (!cmd.contains(param)) {
                    throw new InvalidCmdException("Missing parameter: " + param);
                }
                if (count == 0) {
                    delimiter = delimiter.concat(param + " ");
                } else {
                    delimiter = delimiter.concat("| \\" + param + " ");
                }
                count++;
            }
            token = cmd.split(delimiter);
            if (token.length != parameters.size() + 1) {
                throw new InvalidCmdException("Missing arguments in parameters.");
            }
        }
        return new Command(actualCmd, Arrays.copyOfRange(token, 1, token.length));
    }
}
