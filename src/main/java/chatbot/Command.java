package chatbot;

import chatbot.exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;

public enum Command {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo");

    private final String rep;


    Command(String cmd) {
        this.rep = cmd.toLowerCase();
    }

    public String getStringRep() {
        return rep;
    }
    private static final Map<String, Command> cmdStrMap = new HashMap<>();

    static {
        for (Command cmd : Command.values()) {
            cmdStrMap.put(cmd.getStringRep(), cmd);
        }
    }

    public static Command toCommand(String rep) throws InvalidCommandException {
        Command cmd = cmdStrMap.get(rep.split("\\s+")[0]);
        if (cmd == null) {
            return TODO;
        }
        return cmd;
    }
}
