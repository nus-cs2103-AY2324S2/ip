import java.util.Arrays;
import java.util.HashMap;

public class Parser {
    private static final HashMap<String, Integer> CMD_LIST = new HashMap<>() {{
        put("todo", 2);
        put("deadline", 3);
        put("event", 4);
        put("list", 1);
        put("mark", 2);
        put("unmark", 2);
        put("delete", 2);
        put("bye", 1);
    }};
    protected static Command parse(String cmd) throws InvalidCmdException {
        String[] token = cmd.split(" ");
        if (!Parser.CMD_LIST.containsKey(token[0])) {
            throw new InvalidCmdException("Invalid Command, please try again.");
        }
        if (token.length < Parser.CMD_LIST.get(token[0])) {
            throw new InvalidCmdException("Command " + token[0] + " is missing arguments, please try again.");
        }
        String actualCmd = token[0];
        token = cmd.split(actualCmd + " | \\/from | \\/by | \\/to ");
        if (Parser.CMD_LIST.get(actualCmd) == null) {
            throw new InvalidCmdException("Invalid command, please try again.");
        }
        return new Command(actualCmd, Arrays.copyOfRange(token, 1, token.length));
    }
}
